package com.asi.demo;

import java.util.Scanner;

public class ConsoleMenu {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
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
                    boolean exitUserMenu = false;
                    do {
                        displayUserMenu();
                        int selectedUserOperation = SCANNER.nextInt();
                        switch (selectedUserOperation) {
                            case 0: {
                                exitUserMenu = true;
                                System.out.println("#0 MAIN MENU exit");
                            }
                            break;
                            case 1: {
                                System.out.println("#1 USER MENU create");
                            }
                            break;
                            case 2: {
                                System.out.println("#2 USER MENU read");
                            }
                            break;
                            case 3: {
                                System.out.println("#3 USER MENU update");
                            }
                            break;
                            case 4: {
                                System.out.println("#4 USER MENU delete");
                            }
                            break;
                            default: {
                                System.out.println("Please enter a valid operation!");
                            }
                            break;
                        }
                    } while (!exitUserMenu);
                }
                break;
                case 2: {
                    boolean exitBookMenu = false;
                    do {
                        displayBookMenu();
                        int selectedBookOperation = SCANNER.nextInt();
                        switch (selectedBookOperation) {
                            case 0: {
                                exitBookMenu = true;
                                System.out.println("#0 MAIN MENU exit");
                            }
                            break;
                            case 1: {
                                System.out.println("#1 BOOK MENU create");
                            }
                            break;
                            case 2: {
                                System.out.println("#2 BOOK MENU read");
                            }
                            break;
                            case 3: {
                                System.out.println("#3 BOOK MENU update");
                            }
                            break;
                            case 4: {
                                System.out.println("#4 BOOK MENU delete");
                            }
                            break;
                            default: {
                                System.out.println("Please enter a valid operation!");
                            }
                            break;
                        }
                    } while (!exitBookMenu);
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

    private static void displayBookMenu() {
        System.out.println("BOOK MENU");
        System.out.println("----------");
        System.out.println("0. Main menu");
        System.out.println("1. Save book");
        System.out.println("2. Find book");
        System.out.println("3. Update book");
        System.out.println("4. Delete book");
        System.out.println("Please select an operation:");
    }

    private static void displayUserMenu() {
        System.out.println("USER MENU");
        System.out.println("---------");
        System.out.println("0. Main menu");
        System.out.println("1. Save user");
        System.out.println("2. Find user");
        System.out.println("3. Update user");
        System.out.println("4. Delete user");
        System.out.println("Please select an operation:");
    }

    private static void displayMainMenu() {
        System.out.println("MAIN MENU");
        System.out.println("---------");
        System.out.println("0. Exit");
        System.out.println("1. Users");
        System.out.println("2. Books");
        System.out.println("Please make your choice:");
    }
}
