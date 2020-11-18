package com.asi.demo.sample.loader;

import com.asi.demo.sample.Sample;

import java.util.ArrayList;
import java.util.List;

public class SampleLoader {
    public List<Sample> generateList() {
        List<Sample> samples = new ArrayList<>();
        String numText = "0123456789";
        for (char k = 'A'; k <= 'Z'; k++) {
            Sample sample = new Sample();
            sample.setText(k + numText);
            samples.add(sample);
        }
        return samples;
    }
}
