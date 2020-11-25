package com.asi.sda.sample.loader;

import com.asi.sda.sample.Sample;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static com.asi.sda.sample.constant.CommonMessages.GENERIC_ISSUE;

public class SampleLineLoader implements SampleLoader {
    private static final Logger LOGGER = LogManager.getLogger(SampleLineLoader.class);

    @Override
    public List<Sample> loadData(Path path) {
        List<String> data = new ArrayList<>();

        try {
            data = Files.readAllLines(path);
        } catch (IOException exception) {
            LOGGER.error(GENERIC_ISSUE, exception.getMessage());
        }

        int k = 0;
        String index = null; // unused by default
        String chars = null;
        String digits = null;

        List<Sample> books = new ArrayList<>();

        for (String item : data) {
            k++;
            switch (k) {
                case 1:
                    index = item; // read but not used
                    break;
                case 2:
                    chars = item;
                    break;
                case 3:
                    digits = item;
                    break;
                default:
                    LOGGER.error("SWITCH OUT OF RANGE");
                    break;
            }
            if (k == 3) {
                String text = chars + digits;
                Sample book = new Sample(text);
                books.add(book);
                k = 0;
            }
        }

        return books;
    }
}
