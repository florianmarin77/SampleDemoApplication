package com.asi.sda.sample.faker;

import com.asi.sda.sample.SampleRequestDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SampleSimFakerTest {

    @Test
    void createDummyList() {
        List<SampleRequestDto> dummies = SampleSimFaker.createDummyList();

        assertThat(dummies.size()).isEqualTo(26);
    }
}
