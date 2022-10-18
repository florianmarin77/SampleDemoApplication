package com.lafi.sda.sample.database;

import com.lafi.sda.sample.model.Sample;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SampleSimDatabaseTest {

    @Test
    void generateId() {
        List<Sample> samples = new ArrayList<>();
        Sample sample1 = new Sample("0123456789");
        Sample sample2 = new Sample("9876543210");
        samples.add(sample1);
        samples.add(sample2);
        int lastInsertId = 0;

        List<Sample> results = SampleSimDatabase.generateIdAll(samples, lastInsertId);

        assertThat(results.get(0).getId()).isEqualTo(1);
        assertThat(results.get(0).getText()).isEqualTo("0123456789");
        assertThat(results.get(1).getId()).isEqualTo(2);
        assertThat(results.get(1).getText()).isEqualTo("9876543210");
    }
}
