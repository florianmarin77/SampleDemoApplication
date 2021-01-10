package com.asi.sda.sample.loader;

import com.asi.sda.sample.model.Sample;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SampleLineLoaderTest {

    @Test
    void loadData() {
        SampleLoader loader = new SampleLineLoader();
        List<Sample> samples = new ArrayList<>();

        // TODO: remove try catch from tests
        // tests should never handle exceptions
        try {
            Path path = Paths.get(ClassLoader.getSystemResource("sample/sampleListTest.txt").toURI());
            samples = loader.loadData(Paths.get(String.valueOf(path)));
        } catch (URISyntaxException exception) {
            exception.printStackTrace();
        }

        assertThat(samples.get(0).getText()).isEqualTo("Az0123456789");
        assertThat(samples.get(1).getText()).isEqualTo("Za9876543210");
    }
}
