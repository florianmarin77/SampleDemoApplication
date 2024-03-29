package com.lafi.sda.sample.faker;

import com.lafi.sda.sample.model.SampleRequestDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SampleSimFakerTest {

    @Test
    void createDummyList() {
        List<SampleRequestDto> dummies = SampleSimFaker.createDummyList();

        assertThat(dummies.size()).isEqualTo(26); // from prefix A to Z
    }
}
