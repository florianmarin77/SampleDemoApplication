package com.lafi.sda.sample.faker;

import com.lafi.sda.sample.model.SampleRequestDto;
import net.bytebuddy.utility.RandomString;

import java.util.ArrayList;
import java.util.List;

public class SampleSimFaker {
    public static List<SampleRequestDto> createDummyList() {
        List<SampleRequestDto> dummies = new ArrayList<>();
        RandomString random = new RandomString();

        for (char prefixLetter = 'A'; prefixLetter <= 'Z'; prefixLetter++) {
            SampleRequestDto dummy = new SampleRequestDto();
            dummy.setText(prefixLetter + random.nextString());
            dummies.add(dummy);
        }
        return dummies;
    }
}
