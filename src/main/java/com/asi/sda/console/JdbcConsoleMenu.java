package com.asi.sda.console;

import com.asi.sda.sample.model.Sample;
import com.asi.sda.sample.model.SampleMapper;
import com.asi.sda.sample.database.SampleSimDatabase;
import com.asi.sda.sample.loader.SampleLineLoader;
import com.asi.sda.sample.loader.SampleLoader;
import com.asi.sda.sample.loader.SampleSplitLoader;
import com.asi.sda.sample.repository.SampleJdbcDao;
import com.asi.sda.sample.repository.SampleRepository;
import com.asi.sda.sample.service.SampleJdbcService;
import com.asi.sda.sample.service.SampleService;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class JdbcConsoleMenu {
    private static final SampleSimDatabase database = SampleSimDatabase.getInstance();
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final boolean JOKER = true; // loader scenario

    public static void main(String[] args) throws URISyntaxException {
        SampleRepository dao = new SampleJdbcDao();
        SampleService service = new SampleJdbcService(dao);

        SampleJdbcDao jdbcDao = new SampleJdbcDao(); // create-drop table

        System.out.println("Table created: " + jdbcDao.createTable());
        database.displayTable(database.getSampleList());

        if (JOKER) {
            System.out.println("Welcome to Sample Demo Application with database populated by split loader!");
            SampleLoader loader = new SampleSplitLoader(); // database resources => sampleList.csv
            Path path = Paths.get(ClassLoader.getSystemResource("data/sample/sampleList.csv").toURI());
            service.createAll(SampleMapper.toRequestDtos(loader.loadData(Paths.get(String.valueOf(path)))));
        } else {
            System.out.println("Welcome to Sample Demo Application with database populated by line loader!");
            SampleLoader loader = new SampleLineLoader(); // database resources => sampleList.txt
            Path path = Paths.get(ClassLoader.getSystemResource("data/sample/sampleList.txt").toURI());
            service.createAll(SampleMapper.toRequestDtos(loader.loadData(Paths.get(String.valueOf(path)))));
        }

        boolean exitMainMenu = false;
        do {
            displayMainMenu();
            int selectedChoice = SCANNER.nextInt();
            switch (selectedChoice) {
                case 0: {
                    exitMainMenu = true;
                }
                break;
                case 1: {
                    boolean exitSampleMenu = false;
                    do {
                        displaySampleMenu();
                        int selectedSampleOperation = SCANNER.nextInt();
                        switch (selectedSampleOperation) {
                            case 0: {
                                exitSampleMenu = true;
                                System.out.println("#0 MAIN MENU exit");
                            }
                            break;
                            case 1: {
                                Scanner scanner = new Scanner(System.in);
                                System.out.println("CREATE => Please enter DATA (text string): ");
                                Sample sample = new Sample(scanner.nextLine());

                                service.create(SampleMapper.toRequestDto(sample));
                                database.displayTable(database.getSampleList());
                            }
                            break;
                            case 2: {
                                Scanner scanner = new Scanner(System.in);
                                System.out.println("READ => Please enter ID (integer number): ");
                                int id = scanner.nextInt();

                                service.find(id);
                                database.displayTable(database.getSampleList());
                            }
                            break;
                            case 3: {
                                Scanner scanner = new Scanner(System.in);
                                System.out.println("UPDATE => Please enter ID (integer number): ");
                                int id = scanner.nextInt();
                                Scanner scanner1 = new Scanner(System.in);
                                System.out.println("UPDATE => Please enter DATA (text string): ");
                                String data = scanner1.nextLine();

                                service.update(id, new Sample(data));
                                database.displayTable(database.getSampleList());
                            }
                            break;
                            case 4: {
                                Scanner scanner = new Scanner(System.in);
                                System.out.println("DELETE => Please enter ID (integer number): ");
                                int id = scanner.nextInt();

                                service.delete(id);
                                database.displayTable(database.getSampleList());
                            }
                            break;
                            default: {
                                System.out.println("Please enter a valid operation!");
                            }
                            break;
                        }
                    } while (!exitSampleMenu);
                }
                break;
                default: {
                    System.out.println("Please make a valid choice!");
                }
                break;
            }
        } while (!exitMainMenu);
        System.out.println("Thank you!");
        System.out.println("Table deleted: " + jdbcDao.deleteTable());
    }

    private static void displaySampleMenu() {
        System.out.println("SAMPLE MENU");
        System.out.println("---------");
        System.out.println("0. Main menu");
        System.out.println("1. Save sample");
        System.out.println("2. Find sample");
        System.out.println("3. Update sample");
        System.out.println("4. Delete sample");
        System.out.println("Select an option:");
    }

    private static void displayMainMenu() {
        System.out.println("MAIN MENU");
        System.out.println("---------");
        System.out.println("0. Exit");
        System.out.println("1. Samples");
        System.out.println("Make your choice:");
    }
}

