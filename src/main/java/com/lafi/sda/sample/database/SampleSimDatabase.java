package com.lafi.sda.sample.database;

import com.lafi.sda.sample.model.Sample;

import java.util.ArrayList;
import java.util.List;

public class SampleSimDatabase {
    // Singleton pattern
    private static SampleSimDatabase sampleSimDatabase = null;

    private SampleSimDatabase() {
        // prevent instantiation
    }

    // Thread Safe Lazy Singleton
    public static synchronized SampleSimDatabase getInstance() {
        if (sampleSimDatabase == null) {
            sampleSimDatabase = new SampleSimDatabase();
        }
        return sampleSimDatabase;
    }

    // database resource
    private List<Sample> sampleList = new ArrayList<>();

    public List<Sample> getSampleList() {
        return sampleList;
    }

    public void setSampleList(List<Sample> sampleList) {
        this.sampleList = sampleList;
    }

    // generate multiple ids
    public static List<Sample> generateIdAll(List<Sample> samples, int lastInsertId) {
        List<Sample> entities = new ArrayList<>();

        for (Sample item : samples) {
            lastInsertId++;

            Sample entity = new Sample();
            entity.setId(lastInsertId);
            entity.setText(item.getText());
            entities.add(entity);
        }

        return entities;
    }

    // generate single id
    public static Sample generateIdOne(Sample sample, int lastInsertId) {
        lastInsertId++;

        Sample entity = new Sample();
        entity.setId(lastInsertId);
        entity.setText(sample.getText());

        return entity;
    }

    public void displayTable(List<Sample> entities) {
        int maxLength;
        if (entities.isEmpty()) {
            maxLength = 4;
        } else {
            maxLength = entities.get(0).getText().length();
            for (Sample item : entities) {
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
