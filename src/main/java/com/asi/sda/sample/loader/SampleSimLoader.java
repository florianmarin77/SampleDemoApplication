package com.asi.sda.sample.loader;

import com.asi.sda.sample.model.SampleRequestDto;

import java.util.ArrayList;
import java.util.List;

public class SampleSimLoader {
    public static List<SampleRequestDto> generateItemList() {
        List<SampleRequestDto> items = new ArrayList<>();
        String numericText = "0123456789";

        for (char letterCounter = 'A'; letterCounter <= 'Z'; letterCounter++) {
            SampleRequestDto item = new SampleRequestDto();
            item.setText(letterCounter + numericText);
            items.add(item);
        }
        return items;
    }
}
