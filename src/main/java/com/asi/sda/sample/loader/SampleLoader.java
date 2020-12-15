package com.asi.sda.sample.loader;

import com.asi.sda.sample.model.Sample;

import java.nio.file.Path;
import java.util.List;

public interface SampleLoader {
    List<Sample> loadData(Path path);
}
