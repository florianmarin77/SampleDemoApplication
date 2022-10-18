package com.lafi.sda.sample.demo.database;


import com.lafi.sda.sample.demo.model.Single;

import java.util.ArrayList;
import java.util.List;

public class Database {
    // Singleton pattern
    private static Database database = null;

    private Database() {
        // prevent instantiation
    }

    // Thread Safe Lazy Singleton
    public static synchronized Database getInstance() {
        if (database == null) {
            database = new Database();
        }
        return database;
    }

    // database resource
    private List<Single> singleList = new ArrayList<>();

    public List<Single> getSingleList() {
        return singleList;
    }

    public void setSingleList(List<Single> singleList) {
        this.singleList = singleList;
    }

    // generate multiple ids
    public List<Single> generateIdAll(List<Single> singles, int lastInsertId) {
        List<Single> entities = new ArrayList<>();

        for (Single item : singles) {
            lastInsertId++;

            Single entity = new Single();
            entity.setId(lastInsertId);
            entity.setText(item.getText());
            entities.add(entity);
        }

        return entities;
    }

    // generate single id
    public Single generateIdOne(Single single, int lastInsertId) {
        lastInsertId++;

        Single entity = new Single();
        entity.setId(lastInsertId);
        entity.setText(single.getText());

        return entity;
    }

    public void displayTable(List<Single> entities) {
        int maxLength;
        if (entities.isEmpty()) {
            maxLength = 4;
        } else {
            maxLength = entities.get(0).getText().length();
            for (Single item : entities) {
                int textLength = item.getText().length();
                if (textLength > maxLength) {
                    maxLength = textLength;
                }
            }
        }
        System.out.println();
        displayOutsideLine(maxLength);
        displayMetaData(maxLength, "TEXT");
        if (!entities.isEmpty()) {
            displayInsideLine(maxLength);
        }
        for (int q = 0; q < entities.size(); q++) {
            int id = entities.get(q).getId();
            String text = entities.get(q).getText();
            displayDataLine(id, maxLength, text);
            if (q < entities.size() - 1) {
                displayInsideLine(maxLength);
            }
        }
        displayOutsideLine(maxLength);
        System.out.println();
    }

    private void displayOutsideLine(int maxLength) {
        System.out.print("+------");
        for (int k = 0; k < maxLength; k++) {
            System.out.print("-");
        }
        System.out.print("-+");
        System.out.println();
    }

    private void displayInsideLine(int maxLength) {
        System.out.print("|------");
        for (int k = 0; k < maxLength; k++) {
            System.out.print("-");
        }
        System.out.print("-|");
        System.out.println();
    }

    private void displayDataLine(int id, int maxLength, String text) {
        StringBuilder blank = new StringBuilder();
        if (text.length() < maxLength) {
            int difLength = maxLength - text.length();
            for (int k = 0; k < difLength; k++) {
                blank.append(" ");
            }
        }
        System.out.print("| ");
        if (id < 10) {
            System.out.print("0");
        }
        System.out.print(id + " | " + text + blank + " |");
        System.out.println();
    }

    private void displayMetaData(int maxLength, String text) {
        StringBuilder blank = new StringBuilder();
        if (text.length() < maxLength) {
            int difLength = maxLength - text.length();
            for (int k = 0; k < difLength; k++) {
                blank.append(" ");
            }
        }
        System.out.print("| ID | " + text + blank + " |");
        System.out.println();
    }
}
