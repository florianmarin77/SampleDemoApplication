package com.asi.sda.sample.database;

import com.asi.sda.sample.Sample;

import java.util.ArrayList;
import java.util.List;

public class SampleDatabase {
    public static List<Sample> populateByList(List<Sample> samples){
        List<Sample> entities = new ArrayList<>();
        int k = 0; // fake id
        for (Sample item : samples) {
            k++;
            Sample entity = new Sample();
            entity.setId(k);
            entity.setText(item.getText());
            entities.add(entity);
        }
        return entities;
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
            displayDataLine(id, text);
            if (entities.indexOf(item) < entities.size() - 1) {
                displayInsideLine(maxLength);
            }
        }
        displayOutsideLine(maxLength);
        System.out.println();
    }

    private static void displayOutsideLine(int textLength) {
        System.out.print("+------");
        for (int k = 0; k < textLength; k++) {
            System.out.print("-");
        }
        System.out.print("-+");
        System.out.println();
    }

    private static void displayInsideLine(int textLength) {
        System.out.print("-------");
        for (int k = 0; k < textLength; k++) {
            System.out.print("-");
        }
        System.out.print("--");
        System.out.println();
    }

    private static void displayDataLine(int id, String text) {
        System.out.print("| ");
        if (id < 10) {
            System.out.print("0");
        }
        System.out.print(id + " | " + text + " |");
        System.out.println();
    }
}
