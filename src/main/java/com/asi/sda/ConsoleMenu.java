package com.asi.sda;

import com.asi.sda.sample.Sample;
import com.asi.sda.sample.SampleMapper;
import com.asi.sda.sample.database.SampleDatabase;
import com.asi.sda.sample.faker.SampleFaker;
import com.asi.sda.sample.loader.SampleLoader;

import java.util.List;
import java.util.Scanner;

public class ConsoleMenu {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static int lastInsertId;

    public static void main(String[] args) {
        boolean joker = false; // populate scenario

        List<Sample> database; // database resource

        if (joker) {
            System.out.println("Welcome to Sample Demo Activator with database populated by loader!");
            database = SampleDatabase.populateByList(SampleMapper.toEntities(SampleLoader.generateItemList()));
        } else {
            System.out.println("Welcome to Sample Demo Activator with database populated by faker!");
            database = SampleDatabase.populateByList(SampleMapper.toEntities(SampleFaker.createDummyList()));
        }

        lastInsertId = database.size();

        SampleDatabase.displayDataTable(database);

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
                                String data = scanner.nextLine();
                                lastInsertId++;
                                Sample sample = new Sample(lastInsertId, data);
                                database.add(sample);
                                SampleDatabase.displayDataTable(database);
                            }
                            break;
                            case 2: {
                                Scanner scanner = new Scanner(System.in);
                                System.out.println("READ => Please enter ID (integer number): ");
                                int id = scanner.nextInt();
                                boolean isValid = false;
                                for (Sample item : database){
                                    if (item.getId() == id) {
                                        System.out.println("Sample found: " + item);
                                        isValid = true;
                                    }
                                }
                                if (!isValid){
                                    System.out.println("Sample not found!");
                                }
                                SampleDatabase.displayDataTable(database);
                            }
                            break;
                            case 3: {
                                Scanner scanner = new Scanner(System.in);
                                System.out.println("UPDATE => Please enter ID (integer number): ");
                                int id = scanner.nextInt();
                                boolean isValid = false;
                                for (Sample item : database) {
                                    if (item.getId() == id) {
                                        System.out.println("Sample found: " + item);
                                        System.out.println("UPDATE => Please enter DATA (text string): ");
                                        Scanner scanner1 = new Scanner(System.in);
                                        String data = scanner1.nextLine();
                                        item.setText(data);
                                        isValid = true;
                                        System.out.println("Sample updated! " + item);
                                    }
                                }
                                if (!isValid){
                                    System.out.println("Sample not found and not updated!");
                                }
                                SampleDatabase.displayDataTable(database);
                            }
                            break;
                            case 4: {
                                Scanner scanner = new Scanner(System.in);
                                System.out.println("DELETE => Please enter ID (integer number): ");
                                int id = scanner.nextInt();
                                boolean isValid = false;
                                int index = 0;
                                for (Sample item : database) {
                                    if (item.getId() == id) {
                                        System.out.println("Sample found: " + item);
                                        index = database.indexOf(item);
                                        isValid = true;
                                    }
                                }
                                if (!isValid){
                                    System.out.println("Sample not found and not deleted!");
                                } else {
                                    database.remove(index);
                                    System.out.println("Sample deleted!");
                                }
                                SampleDatabase.displayDataTable(database);
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
