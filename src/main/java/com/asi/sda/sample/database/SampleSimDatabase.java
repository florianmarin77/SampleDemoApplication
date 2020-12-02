package com.asi.sda.sample.database;

import com.asi.sda.sample.Sample;

import java.util.ArrayList;
import java.util.List;

public class SampleSimDatabase {
    // Singleton pattern
    private static SampleSimDatabase sampleSimDatabase = null;

    private SampleSimDatabase() {
        // prevent instantiation
    }

    public static SampleSimDatabase getInstance() {
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

    public static Sample generateIdOne(Sample sample, int lastInsertId) {
        lastInsertId++;

        Sample entity = new Sample();
        entity.setId(lastInsertId);
        entity.setText(sample.getText());

        return entity;
    }

    public static void displayDataTable(List<Sample> entities) {
        int maxLength = entities.get(0).getText().length();
        for (Sample item : entities) {
            int textLength = item.getText().length();
            if (textLength > maxLength) {
                maxLength = textLength;
            }
        }
        System.out.println();
        displayOutsideLine(maxLength);
        for (Sample item : entities) {
            int id = item.getId();
            String text = item.getText();
            displayDataLine(id, maxLength, text);
            if (entities.indexOf(item) < entities.size() - 1) {
                displayInsideLine(maxLength);
            }
        }
        displayOutsideLine(maxLength);
        System.out.println();
    }

    private static void displayOutsideLine(int maxLength) {
        System.out.print("+------");
        for (int k = 0; k < maxLength; k++) {
            System.out.print("-");
        }
        System.out.print("-+");
        System.out.println();
    }

    private static void displayInsideLine(int maxLength) {
        System.out.print("-------");
        for (int k = 0; k < maxLength; k++) {
            System.out.print("-");
        }
        System.out.print("--");
        System.out.println();
    }

    private static void displayDataLine(int id, int maxLength, String text) {
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
}
