package com.lafi.sda.sample.loader;

import com.lafi.sda.sample.model.Sample;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SampleLineLoaderTest {

    @Test
    void loadData() throws URISyntaxException {
        SampleLoader loader = new SampleLineLoader();
        List<Sample> samples;

        Path path = Paths.get(ClassLoader.getSystemResource("sample/sampleListTest.txt").toURI());
        samples = loader.loadData(Paths.get(String.valueOf(path)));

        assertThat(samples.get(0).getText()).isEqualTo("Az0123456789");
        assertThat(samples.get(1).getText()).isEqualTo("Za9876543210");
    }
}
