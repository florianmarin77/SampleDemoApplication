package com.asi.sda.sample.faker;

import com.asi.sda.sample.model.SampleRequestDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SampleSimFakerTest {

    @Test
    void createDummyList() {
        List<SampleRequestDto> dummies = SampleSimFaker.createDummyList();

        // TODO: add a parameter to createDummyList
        // avoid magic numbers like 26. how do you explain that number in code?
        assertThat(dummies.size()).isEqualTo(26);
    }
}
