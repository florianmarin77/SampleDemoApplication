package com.asi.sda.sample.loader;

import com.asi.sda.sample.Sample;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.asi.sda.sample.constant.CommonMessages.GENERIC_ISSUE;


public class SampleSplitLoader implements SampleLoader {
    private static final Logger LOGGER = LogManager.getLogger(SampleSplitLoader.class);

    @Override
    public List<Sample> loadData(Path path) {
        List<String> data = new ArrayList<>();
        try {
            data = Files.readAllLines(path);
        } catch (IOException exception) {
            LOGGER.error(GENERIC_ISSUE, exception.getMessage());
        }

        List<Sample> result = new ArrayList<>();
        for (String item : data) {
            List<String> itemData = Pattern.compile(",")
                    .splitAsStream(item).collect(Collectors.toList());
            result.add(new Sample(itemData.get(0) + itemData.get(1)));
        }

        return result;
    }
}
