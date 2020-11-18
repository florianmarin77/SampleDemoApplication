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

    public static void main(String[] args) {
        boolean joker = false; // populate scenario

        List<Sample> database; // database resource

        if (joker) {
            System.out.println("Welcome to Sample Demo Activator with database populated by loader!");
            database = SampleDatabase.displayDataTable(SampleMapper.toEntities(SampleLoader.generateItemList()));
        } else {
            System.out.println("Welcome to Sample Demo Activator with database populated by faker!");
            database = SampleDatabase.displayDataTable(SampleMapper.toEntities(SampleFaker.createDummyList()));
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
                                System.out.println("#1 SAMPLE MENU create");
                            }
                            break;
                            case 2: {
                                System.out.println("#2 SAMPLE MENU read");
                            }
                            break;
                            case 3: {
                                System.out.println("#3 SAMPLE MENU update");
                            }
                            break;
                            case 4: {
                                System.out.println("#4 SAMPLE MENU delete");
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
        System.out.println("Please select an operation:");
    }

    private static void displayMainMenu() {
        System.out.println("MAIN MENU");
        System.out.println("---------");
        System.out.println("0. Exit");
        System.out.println("1. Samples");
        System.out.println("Please make your choice:");
    }
}
