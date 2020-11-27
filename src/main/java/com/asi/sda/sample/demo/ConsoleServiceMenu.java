package com.asi.sda.sample.demo;

import com.asi.sda.sample.demo.database.Database;
import com.asi.sda.sample.demo.model.Single;
import com.asi.sda.sample.demo.model.SingleMapper;
import com.asi.sda.sample.demo.repository.Repository;
import com.asi.sda.sample.demo.repository.SingleDao;
import com.asi.sda.sample.demo.service.Service;
import com.asi.sda.sample.demo.service.SingleService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleServiceMenu {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Database database = Database.getInstance();
    private static final boolean JOKER = false; // resource scenario

    public static void main(String[] args) {
        Repository dao = new SingleDao();
        Service service = new SingleService(dao);

        if (JOKER) {
            System.out.println("Welcome to Demo Module with numerical resource!");

            List<Single> singles = new ArrayList<>();
            Single single1 = new Single("0123456789");
            Single single2 = new Single("9876543210");
            singles.add(single1);
            singles.add(single2);

            // populate database
            service.createAll(SingleMapper.toRequests(singles));
            // display empty table (Singleton needed)
            database.displayTable(database.getSingleList());
        } else {
            System.out.println("Welcome to Demo Module with alphabetical resource!");
            List<Single> singles = new ArrayList<>();
            Single single1 = new Single("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
            Single single2 = new Single("abcdefghijklmnopqrstuvwxyz");
            singles.add(single1);
            singles.add(single2);

            service.createAll(SingleMapper.toRequests(singles)); // populate database
            database.displayTable(database.getSingleList()); // display empty table (Singleton needed)
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
                    boolean exitSingleMenu = false;
                    do {
                        displaySingleMenu();
                        int selectedSingleOperation = SCANNER.nextInt();
                        switch (selectedSingleOperation) {
                            case 0: {
                                exitSingleMenu = true;
                                System.out.println("#0 MAIN MENU exit");
                            }
                            break;
                            case 1: {
                                Scanner scanner = new Scanner(System.in);
                                System.out.println("CREATE => Please enter DATA (text string): ");

                                service.create(SingleMapper.toRequest(new Single(scanner.nextLine())));
                                database.displayTable(database.getSingleList());
                            }
                            break;
                            case 2: {
                                Scanner scanner = new Scanner(System.in);
                                System.out.println("READ => Please enter ID (integer number): ");

                                service.find(scanner.nextInt());
                                database.displayTable(database.getSingleList());
                            }
                            break;
                            case 3: {
                                Scanner scanner = new Scanner(System.in);
                                System.out.println("UPDATE => Please enter ID (integer number): ");
                                int id = scanner.nextInt();

                                Scanner scanner1 = new Scanner(System.in);
                                System.out.println("UPDATE => Please enter DATA (text string): ");
                                String data = scanner1.nextLine();

                                service.update(id, new Single(data));
                                database.displayTable(database.getSingleList());
                            }
                            break;
                            case 4: {
                                Scanner scanner = new Scanner(System.in);
                                System.out.println("DELETE => Please enter ID (integer number): ");

                                service.delete(scanner.nextInt());
                                database.displayTable(database.getSingleList());
                            }
                            break;
                            default: {
                                System.out.println("Please enter a valid operation!");
                            }
                            break;
                        }
                    } while (!exitSingleMenu);
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

    private static void displaySingleMenu() {
        System.out.println("SAMPLE MENU");
        System.out.println("---------");
        System.out.println("0. Main menu");
        System.out.println("1. Save single");
        System.out.println("2. Find single");
        System.out.println("3. Update single");
        System.out.println("4. Delete single");
        System.out.println("Select an option:");
    }

    private static void displayMainMenu() {
        System.out.println("MAIN MENU");
        System.out.println("---------");
        System.out.println("0. Exit");
        System.out.println("1. Singles");
        System.out.println("Make your choice:");
    }
}
