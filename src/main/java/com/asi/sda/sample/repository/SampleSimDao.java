package com.asi.sda.sample.repository;

import com.asi.sda.sample.model.Sample;
import com.asi.sda.sample.database.SampleSimDatabase;
import com.asi.sda.sample.exception.SampleNotFoundException;
import com.asi.sda.sample.exception.SampleNotSavedException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.asi.sda.sample.constant.SampleMessages.SAMPLE_NOT_FOUND_ERROR;

public class SampleSimDao implements SampleRepository {
    private static final String SOURCE = "DAO => ";

    private static final SampleSimDatabase database = SampleSimDatabase.getInstance();

    private static int lastInsertId;

    private static final boolean isActive = true; // exception scenario

    @Override
    public List<Sample> createAll(List<Sample> samples) {
        List<Sample> entities = database.getSampleList(); // import
        List<Sample> results = new ArrayList<>();

        boolean isReady = true; // database scenario

        if (isReady) {
            results = SampleSimDatabase.generateIdAll(samples, lastInsertId);
            lastInsertId = lastInsertId + results.size();
            entities.addAll(results);
            database.setSampleList(entities); // export
            System.out.println(SOURCE + "CREATE=TRUE/SIZE=" + results.size() + "/all");
        } else {
            System.out.println(SOURCE + "CREATE=FALSE/SIZE=" + 0 + "/all");
            if (isActive) {
                throw new SampleNotSavedException("ERROR: Samples saving failed!");
            }
        }

        database.displayTable(results);
        return results;
    }

    @Override
    public Sample create(Sample sample) {
        List<Sample> entities = database.getSampleList(); // import
        List<Sample> results = new ArrayList<>();
        Sample result = new Sample();

        boolean isReady = true; // database scenario

        if (isReady) {
            result = SampleSimDatabase.generateIdOne(sample, lastInsertId);
            lastInsertId++;
            entities.add(result);
            results.add(result);
            database.setSampleList(entities); // export
            System.out.println(SOURCE + "CREATE=TRUE/ID=" + result.getId());
        } else {
            System.out.println(SOURCE + "CREATE=FALSE/ID=" + 0);
            if (isActive) {
                throw new SampleNotSavedException("ERROR: Sample saving failed!");
            }
        }

        database.displayTable(results);
        return result;
    }

    @Override
    public List<Sample> findAll() {
        List<Sample> entities = database.getSampleList(); // import
        List<Sample> results = new ArrayList<>();

        boolean isReady = true; // database scenario

        if (isReady) {
            results = entities;
            System.out.println(SOURCE + "FIND=TRUE/SIZE=" + results.size() + "/all");
        } else {
            System.out.println(SOURCE + "FIND=FALSE/SIZE=" + 0 + "/all");
            if (isActive) {
                throw new SampleNotSavedException("ERROR: Samples searching failed!");
            }
        }

        database.displayTable(results);
        return results;
    }

    @Override
    public List<Sample> findByText(String text) {
        List<Sample> entities = database.getSampleList(); // import
        List<Sample> results = new ArrayList<>();

        boolean isReady = true; // database scenario

        if (isReady) {
            for (Sample item : entities) {
                if (item.getText().equals(text)) {
                    results.add(item);
                }
            }
            System.out.println(SOURCE + "FIND=TRUE/SIZE=" + results.size() + "/" + text);
        } else {
            System.out.println(SOURCE + "FIND=FALSE/SIZE=" + 0 + "/" + text);
            if (isActive) {
                throw new SampleNotFoundException("ERROR: Samples searching failed!");
            }
        }

        database.displayTable(results);
        return results;
    }

    @Override
    public Optional<Sample> find(Integer id) {
        List<Sample> entities = database.getSampleList(); // import
        List<Sample> results = new ArrayList<>();
        Sample result = null;

        boolean isReady = true; // database scenario

        if (isReady) {
            Integer index = null;
            for (int k = 0; k < entities.size(); k++) {
                if (entities.get(k).getId().equals(id)) {
                    index = k;
                }
            }
            if (index != null) {
                result = entities.get(index);
                System.out.println(SOURCE + "FIND=TRUE/ID=" + id);
            } else {
                System.out.println(SOURCE + "FIND=FALSE/ID=" + id);
            }
            results.add(result);
        } else {
            if (isActive) {
                throw new SampleNotFoundException(SAMPLE_NOT_FOUND_ERROR);
            }
        }

        database.displayTable(results);
        return Optional.ofNullable(result);
    }

    @Override
    public Optional<Sample> update(Integer id, Sample data) {
        List<Sample> entities = database.getSampleList(); // import
        List<Sample> results = new ArrayList<>();
        Sample result = null;

        boolean isReady = true; // database scenario

        if (isReady) {
            Integer index = null;
            for (int k = 0; k < entities.size(); k++) {
                if (entities.get(k).getId().equals(id)) {
                    index = k;
                }
            }
            if (index != null) {
                entities.get(index).setText(data.getText());
                result = entities.get(index);
                database.setSampleList(entities); // export
                System.out.println(SOURCE + "UPDATE=TRUE/ID=" + id);
            } else {
                System.out.println(SOURCE + "UPDATE=FALSE/ID=" + id);
            }
            results.add(result);
        } else {
            if (isActive) {
                throw new SampleNotFoundException(SAMPLE_NOT_FOUND_ERROR);
            }
        }

        database.displayTable(results);
        return Optional.ofNullable(result);
    }

    @Override
    public Optional<Sample> delete(Integer id) {
        List<Sample> entities = database.getSampleList(); // import
        List<Sample> results = new ArrayList<>();
        Sample result = null;

        boolean isReady = true; // database scenario

        if (isReady) {
            Integer index = null;
            for (int k = 0; k < entities.size(); k++) {
                if (entities.get(k).getId().equals(id)) {
                    index = k;
                }
            }
            if (index != null) {
                result = entities.get(index);
                entities.remove((int) index);
                database.setSampleList(entities); // export
                System.out.println(SOURCE + "DELETE=TRUE/ID=" + id);
            } else {
                System.out.println(SOURCE + "DELETE=FALSE/ID=" + id);
            }
            results.add(result);
        } else {
            if (isActive) {
                throw new SampleNotFoundException(SAMPLE_NOT_FOUND_ERROR);
            }
        }

        database.displayTable(results);
        return Optional.ofNullable(result);
    }
}
