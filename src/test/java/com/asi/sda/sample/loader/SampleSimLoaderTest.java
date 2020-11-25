package com.asi.sda.sample.loader;

import com.asi.sda.sample.SampleRequestDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SampleSimLoaderTest {

    @Test
    void generateItemList() {
        List<SampleRequestDto> requests = SampleSimLoader.generateItemList();

        assertThat(requests.get(0).getText()).isEqualTo("A0123456789");
        assertThat(requests.get(25).getText()).isEqualTo("Z0123456789");
    }
}
