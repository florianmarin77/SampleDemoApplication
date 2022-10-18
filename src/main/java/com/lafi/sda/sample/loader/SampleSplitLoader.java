package com.lafi.sda.sample.loader;

import com.lafi.sda.sample.model.Sample;
import com.lafi.sda.sample.constant.CommonMessages;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class SampleSplitLoader implements SampleLoader {
    private static final Logger LOGGER = LogManager.getLogger(SampleSplitLoader.class);

    @Override
    public List<Sample> loadData(Path path) {
        List<String> data = new ArrayList<>();
        try {
            data = Files.readAllLines(path);
        } catch (IOException exception) {
            LOGGER.error(CommonMessages.GENERIC_ISSUE, exception.getMessage());
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
