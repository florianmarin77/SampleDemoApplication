package com.asi.sda.sample.faker;

import com.asi.sda.sample.model.SampleRequestDto;
import net.bytebuddy.utility.RandomString;

import java.util.ArrayList;
import java.util.List;

public class SampleSimFaker {
    public static List<SampleRequestDto> createDummyList() {
        List<SampleRequestDto> dummies = new ArrayList<>();
        RandomString random = new RandomString();
        for (char k = 'A'; k <= 'Z'; k++) {
            SampleRequestDto dummy = new SampleRequestDto();
            dummy.setText(k + random.nextString());
            dummies.add(dummy);
        }
        return dummies;
    }
}
