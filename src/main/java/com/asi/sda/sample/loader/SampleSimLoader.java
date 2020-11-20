package com.asi.sda.sample.loader;

import com.asi.sda.sample.SampleRequestDto;

import java.util.ArrayList;
import java.util.List;

public class SampleSimLoader {
    public static List<SampleRequestDto> generateItemList() {
        List<SampleRequestDto> items = new ArrayList<>();
        String numText = "0123456789";
        for (char k = 'A'; k <= 'Z'; k++) {
            SampleRequestDto item = new SampleRequestDto();
            item.setText(k + numText);
            items.add(item);
        }
        return items;
    }
}
