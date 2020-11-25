package com.asi.sda.sample.loader;

import com.asi.sda.sample.Sample;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SampleSplitLoaderTest {

    @Test
    void loadData() {
        /**
         * given => the resource file named bookListTest.csv
         * when  => trying to load from this resource file
         * then  => the result list contains expected items
         */

        SampleLoader sampleLoader = new SampleSplitLoader();
        List<Sample> samples = new ArrayList<>();
        Path sampleListPath;
        try {
            sampleListPath = Paths.get(ClassLoader.getSystemResource("sample/sampleListTest.csv").toURI());
            samples = sampleLoader.loadData(Paths.get(String.valueOf(sampleListPath)));
        } catch (URISyntaxException exception) {
            exception.printStackTrace();
        }

        assertThat(samples.get(0).getText()).isEqualTo("Az0123456789");
        assertThat(samples.get(1).getText()).isEqualTo("Za9876543210");
    }
}
