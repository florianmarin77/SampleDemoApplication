package com.asi.demo.sample.faker;

import com.asi.demo.sample.Sample;
import net.bytebuddy.utility.RandomString;

import java.util.ArrayList;
import java.util.List;

public class SampleFaker {
    public List<Sample> generateList() {
        List<Sample> samples = new ArrayList<>();
        RandomString randomString = new RandomString();
        for (char k = 'A'; k <= 'Z'; k++) {
            Sample sample = new Sample();
            String fakeText = randomString.nextString();
            sample.setText(k + fakeText);
            samples.add(sample);
        }
        return samples;
    }
}
