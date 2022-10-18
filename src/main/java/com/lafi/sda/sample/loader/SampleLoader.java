package com.lafi.sda.sample.loader;

import com.lafi.sda.sample.model.Sample;

import java.nio.file.Path;
import java.util.List;

public interface SampleLoader {
    List<Sample> loadData(Path path);
}
