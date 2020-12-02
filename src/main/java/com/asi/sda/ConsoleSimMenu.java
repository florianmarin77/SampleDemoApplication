package com.asi.sda;

import com.asi.sda.sample.Sample;
import com.asi.sda.sample.SampleMapper;
import com.asi.sda.sample.controller.SampleSimController;
import com.asi.sda.sample.database.SampleSimDatabase;
import com.asi.sda.sample.repository.SampleSimDao;
import com.asi.sda.sample.service.SampleService;
import com.asi.sda.sample.service.SampleSimService;

import java.util.Scanner;

public class ConsoleSimMenu {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final SampleSimDatabase database = SampleSimDatabase.getInstance();

    private static final SampleSimDao dao = new SampleSimDao();
    private static final SampleService service = new SampleSimService(dao);
    private static final SampleSimController controller = new SampleSimController(service);

    public static void main(String[] args) {
        boolean joker = false; // populate scenario

        if (joker) {
            System.out.println("Welcome to Sample Demo Application with database populated by loader!");
            controller.saveAllByLoader();
            database.displayTable(database.getSampleList());
        } else {
            System.out.println("Welcome to Sample Demo Application with database populated by faker!");
            controller.saveAllByFaker();
            database.displayTable(database.getSampleList());
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

                                controller.save(SampleMapper.toRequestDto(sample));
                                database.displayTable(database.getSampleList());
                            }
                            break;
                            case 2: {
                                Scanner scanner = new Scanner(System.in);
                                System.out.println("READ => Please enter ID (integer number): ");
                                int id = scanner.nextInt();

                                controller.getById(id);
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

                                controller.updateById(id, new Sample(data));
                                database.displayTable(database.getSampleList());
                            }
                            break;
                            case 4: {
                                Scanner scanner = new Scanner(System.in);
                                System.out.println("DELETE => Please enter ID (integer number): ");
                                int id = scanner.nextInt();

                                controller.deleteById(id);
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
