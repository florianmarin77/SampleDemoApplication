package com.asi.sda.sample.repository;

import com.asi.sda.sample.Sample;
import com.asi.sda.sample.database.SampleSimDatabase;
import com.asi.sda.sample.exception.SampleNotDeletedException;
import com.asi.sda.sample.exception.SampleNotFoundException;
import com.asi.sda.sample.exception.SampleNotSavedException;
import com.asi.sda.sample.exception.SampleNotUpdatedException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SampleSimDao implements SampleRepository {
    private static final String SOURCE = "DAO => ";

    private static final SampleSimDatabase database = SampleSimDatabase.getInstance();

    private static int lastInsertId;

    private static final boolean isActive = true; // exception scenario

    // -------------------------------------------- CRUD => CREATE

    @Override
    public List<Sample> createAll(List<Sample> samples) {
        List<Sample> entities = database.getDatabase(); // import
        boolean isReady = true; // database scenario

        List<Sample> results = new ArrayList<>();

        if (isReady) {
            results = SampleSimDatabase.generateId(samples, lastInsertId);
            lastInsertId = lastInsertId + results.size();
            entities.addAll(results);
            database.setDatabase(entities); // export
            System.out.println(SOURCE + "CREATE=TRUE/SIZE=" + results.size() + "/all");
        } else {
            System.out.println(SOURCE + "CREATE=FALSE/SIZE=" + 0 + "/all");
            if (isActive) {
                throw new SampleNotSavedException("ERROR: Samples saving failed!");
            }
        }

        return results;
    }

    @Override
    public Sample create(Sample sample) {
        List<Sample> entities = database.getDatabase(); // import
        boolean isReady = true; // database scenario

        Sample entity = new Sample();

        if (isReady) {
            lastInsertId++;
            entity.setId(lastInsertId);
            entity.setText(sample.getText());
            entities.add(entity);
            database.setDatabase(entities); // export
            System.out.println(SOURCE + "CREATE=TRUE/ID=" + entity.getId());
        } else {
            System.out.println(SOURCE + "CREATE=FALSE/ID=" + 0);
            if (isActive) {
                throw new SampleNotSavedException("ERROR: Sample saving failed!");
            }
        }

        return entity;
    }

    // -------------------------------------------- CRUD => READ

    @Override
    public List<Sample> findAll() {
        List<Sample> entities = database.getDatabase(); // import
        boolean isReady = true; // database scenario

        if (isReady) {
            System.out.println(SOURCE + "FIND=TRUE/SIZE=" + entities.size() + "/all");
        } else {
            System.out.println(SOURCE + "FIND=FALSE/SIZE=" + 0 + "/all");
            if (isActive) {
                throw new SampleNotSavedException("ERROR: Samples searching failed!");
            }
        }

        return entities;
    }

    @Override
    public List<Sample> findByText(String text) {
        List<Sample> entities = database.getDatabase(); // import
        boolean isReady = true; // database scenario

        List<Sample> results = new ArrayList<>();

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

        return results;
    }

    @Override
    public Optional<Sample> find(Integer id) {
        List<Sample> entities = database.getDatabase(); // import
        boolean isReady = true; // database scenario

        Sample entity = new Sample();

        if (isReady) {
            Integer index = null;
            for (Sample item : entities) {
                if (item.getId().equals(id)) {
                    index = entities.indexOf(item);
                }
            }
            if (index != null) {
                entity = entities.get(index);
                System.out.println(SOURCE + "FIND=TRUE/ID=" + id);
            } else {
                entity = null;
                System.out.println(SOURCE + "FIND=FALSE/ID=" + id);
            }
        } else {
            if (isActive) {
                throw new SampleNotFoundException("ERROR: Sample searching failed!");
            }
        }

        return Optional.ofNullable(entity);
    }

    // -------------------------------------------- CRUD => UPDATE

    @Override
    public void update(Integer id, Sample sampleData) {
        List<Sample> entities = database.getDatabase(); // import
        boolean isReady = true; // database scenario

        if (isReady) {
            Integer index = null;
            for (Sample item : entities) {
                if (item.getId().equals(id)) {
                    index = entities.indexOf(item);
                }
            }
            Sample entity = new Sample();
            if (index != null) {
                entity.setId(id);
                entity.setText(sampleData.getText());
                entities.get(index).setText(sampleData.getText());
                database.setDatabase(entities); // export
                System.out.println(SOURCE + "UPDATE=TRUE/ID=" + id);
            } else {
                System.out.println(SOURCE + "UPDATE=FALSE/ID=" + id);
            }
        } else {
            if (isActive) {
                throw new SampleNotUpdatedException("ERROR: Sample updating failed!");
            }
        }
    }

    // -------------------------------------------- CRUD => DELETE

    @Override
    public void delete(Integer id) {
        List<Sample> entities = database.getDatabase(); // import
        boolean isReady = true; // database scenario

        if (isReady) {
            Integer index = null;
            for (Sample item : entities) {
                if (item.getId().equals(id)) {
                    System.out.println(SOURCE + "FIND=TRUE/ID=" + id);
                    index = entities.indexOf(item);
                }
            }
            if (index != null) {
                entities.remove((int) index);
                database.setDatabase(entities); // export
                System.out.println(SOURCE + "DELETE=TRUE/ID=" + id);
            } else {
                System.out.println(SOURCE + "DELETE=FALSE/ID=" + id);
            }
        } else {
            if (isActive) {
                throw new SampleNotDeletedException("ERROR: Sample deleting failed!");
            }
        }
    }
}
