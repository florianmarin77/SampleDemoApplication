package com.asi.sda.sample.loader;

import com.asi.sda.sample.model.SampleRequestDto;

import java.util.ArrayList;
import java.util.List;

public class SampleSimLoader {
    public static List<SampleRequestDto> generateItemList() {
        List<SampleRequestDto> items = new ArrayList<>();
        String serialNumber = "0123456789";

        for (char prefixLetter = 'A'; prefixLetter <= 'Z'; prefixLetter++) {
            SampleRequestDto item = new SampleRequestDto();
            item.setText(prefixLetter + serialNumber);
            items.add(item);
        }
        return items;
    }
}
