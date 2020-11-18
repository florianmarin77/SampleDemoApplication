package com.asi.sda.sample.database;

import com.asi.sda.sample.Sample;

import java.util.ArrayList;
import java.util.List;

public class SampleDatabase {
    public static List<Sample> displayDataTable(List<Sample> samples) {
        List<Sample> entities = new ArrayList<>();
        int textLength = samples.get(0).getText().length();
        int k = 0; // fake id
        System.out.println();
        displayOutsideLine(textLength);
        for (Sample item : samples) {
            k++;
            Sample entity = new Sample();
            entity.setId(k);
            entity.setText(item.getText());
            entities.add(entity);
            int id = entity.getId();
            String text = entity.getText();
            displayDataLine(id, text);
            if (entities.indexOf(item) < entities.size() - 1) {
                displayInsideLine(textLength);
            }
        }
        displayOutsideLine(textLength);
        System.out.println();
        return entities;
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
