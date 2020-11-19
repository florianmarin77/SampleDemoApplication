package com.asi.sda;

import com.asi.sda.sample.Sample;
import com.asi.sda.sample.SampleMapper;
import com.asi.sda.sample.controller.SampleController;
import com.asi.sda.sample.database.SampleDatabase;
import com.asi.sda.sample.faker.SampleFaker;
import com.asi.sda.sample.loader.SampleLoader;
import com.asi.sda.sample.repository.SampleDao;
import com.asi.sda.sample.service.SampleServiceImpl;

import java.util.List;
import java.util.Scanner;

public class ConsoleMenu {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final SampleDatabase database = SampleDatabase.getInstance();

    private static final SampleDao dao = new SampleDao();
    private static final SampleServiceImpl service = new SampleServiceImpl(dao);
    private static final SampleController controller = new SampleController(service);

    public static int lastInsertId;

    public static void main(String[] args) {
        boolean joker = false; // populate scenario

        if (joker) {
            System.out.println("Welcome to Sample Demo Application with database populated by loader!");
            database.setDatabase(SampleDatabase.populateByList(SampleMapper.toEntities(SampleLoader.generateItemList())));
            SampleDatabase.displayDataTable(database.getDatabase());
        } else {
            System.out.println("Welcome to Sample Demo Application with database populated by faker!");
            database.setDatabase(SampleDatabase.populateByList(SampleMapper.toEntities(SampleFaker.createDummyList())));
            SampleDatabase.displayDataTable(database.getDatabase());
        }

        lastInsertId = database.getDatabase().size();

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

                                lastInsertId++;
                                controller.save(SampleMapper.toRequestDto(sample));
                                SampleDatabase.displayDataTable(database.getDatabase());
                            }
                            break;
                            case 2: {
                                Scanner scanner = new Scanner(System.in);
                                List<Sample> entities = database.getDatabase();
                                System.out.println("READ => Please enter ID (integer number): ");
                                int id = scanner.nextInt();

                                controller.getById(id);
                                SampleDatabase.displayDataTable(entities);
                            }
                            break;
                            case 3: {
                                Scanner scanner = new Scanner(System.in);
                                List<Sample> entities = database.getDatabase();
                                System.out.println("UPDATE => Please enter ID (integer number): ");

                                int id = scanner.nextInt();
                                boolean isValid = false;
                                for (Sample entity : entities) {
                                    if (entity.getId() == id) {
                                        System.out.println("Sample found: " + entity);
                                        System.out.println("UPDATE => Please enter DATA (text string): ");
                                        Scanner scanner1 = new Scanner(System.in);
                                        String data = scanner1.nextLine();
                                        entity.setText(data);
                                        database.setDatabase(entities);
                                        isValid = true;
                                        System.out.println("Sample updated! " + entity);
                                    }
                                }
                                if (!isValid) {
                                    System.out.println("Sample not found and not updated!");
                                }
                                SampleDatabase.displayDataTable(entities);
                            }
                            break;
                            case 4: {
                                Scanner scanner = new Scanner(System.in);
                                List<Sample> entities = database.getDatabase();
                                System.out.println("DELETE => Please enter ID (integer number): ");

                                int id = scanner.nextInt();
                                boolean isValid = false;
                                int index = 0;
                                for (Sample entity : entities) {
                                    if (entity.getId() == id) {
                                        System.out.println("Sample found: " + entity);
                                        index = entities.indexOf(entity);
                                        isValid = true;
                                    }
                                }
                                if (!isValid) {
                                    System.out.println("Sample not found and not deleted!");
                                } else {
                                    entities.remove(index);
                                    database.setDatabase(entities);
                                    System.out.println("Sample deleted!");
                                }
                                SampleDatabase.displayDataTable(entities);
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
