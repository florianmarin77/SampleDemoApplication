package com.asi.demo.sample.faker;

import com.asi.demo.sample.SampleRequestDto;
import net.bytebuddy.utility.RandomString;

import java.util.ArrayList;
import java.util.List;

public class SampleFaker {
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

    public static SampleRequestDto createDummy() {
        SampleRequestDto dummy = new SampleRequestDto();
        dummy.setText(new RandomString().nextString());
        return dummy;
    }
}
