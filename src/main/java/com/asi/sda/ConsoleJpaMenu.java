package com.asi.sda;

import com.asi.sda.sample.Sample;
import com.asi.sda.sample.SampleMapper;
import com.asi.sda.sample.database.SampleSimDatabase;
import com.asi.sda.sample.loader.SampleLineLoader;
import com.asi.sda.sample.loader.SampleLoader;
import com.asi.sda.sample.loader.SampleSplitLoader;
import com.asi.sda.sample.repository.SampleJpaDao;
import com.asi.sda.sample.repository.SampleRepository;
import com.asi.sda.sample.service.SampleJpaService;
import com.asi.sda.sample.service.SampleService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class ConsoleJpaMenu {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final SampleSimDatabase database = SampleSimDatabase.getInstance();
    private static final boolean JOKER = true; // loader scenario

    public static void main(String[] args) throws URISyntaxException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MacroMedia");
        EntityManager em = emf.createEntityManager();

        SampleRepository dao = new SampleJpaDao(em);
        SampleService service = new SampleJpaService(dao);

        if (JOKER) {
            System.out.println("Welcome to Sample Demo Application with database populated by split loader!");
            SampleLoader loader = new SampleSplitLoader(); // database resources => sampleList.csv
            Path path = Paths.get(ClassLoader.getSystemResource("data/sample/sampleList.csv").toURI());
            service.createAll(SampleMapper.toRequestDtos(loader.loadData(Paths.get(String.valueOf(path)))));
            SampleSimDatabase.displayDataTable(database.getSampleList());
        } else {
            System.out.println("Welcome to Sample Demo Application with database populated by line loader!");
            SampleLoader loader = new SampleLineLoader(); // database resources => sampleList.txt
            Path path = Paths.get(ClassLoader.getSystemResource("data/sample/sampleList.txt").toURI());
            service.createAll(SampleMapper.toRequestDtos(loader.loadData(Paths.get(String.valueOf(path)))));
            SampleSimDatabase.displayDataTable(database.getSampleList());
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
                                SampleSimDatabase.displayDataTable(database.getSampleList());
                            }
                            break;
                            case 2: {
                                Scanner scanner = new Scanner(System.in);
                                System.out.println("READ => Please enter ID (integer number): ");
                                int id = scanner.nextInt();

                                service.find(id);
                                SampleSimDatabase.displayDataTable(database.getSampleList());
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
                                SampleSimDatabase.displayDataTable(database.getSampleList());
                            }
                            break;
                            case 4: {
                                Scanner scanner = new Scanner(System.in);
                                System.out.println("DELETE => Please enter ID (integer number): ");
                                int id = scanner.nextInt();

                                service.delete(id);
                                SampleSimDatabase.displayDataTable(database.getSampleList());
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