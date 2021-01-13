package com.asi.sda.sample.loader;

import com.asi.sda.sample.model.Sample;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SampleSplitLoaderTest {

    @Test
    void loadData() throws URISyntaxException {
        SampleLoader loader = new SampleSplitLoader();
        List<Sample> samples;

        Path path = Paths.get(ClassLoader.getSystemResource("sample/sampleListTest.csv").toURI());
        samples = loader.loadData(Paths.get(String.valueOf(path)));

        assertThat(samples.get(0).getText()).isEqualTo("Az0123456789");
        assertThat(samples.get(1).getText()).isEqualTo("Za9876543210");
    }
}
