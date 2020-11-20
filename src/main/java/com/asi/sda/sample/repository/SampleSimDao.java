package com.asi.sda.sample.repository;

import com.asi.sda.sample.Sample;
import com.asi.sda.sample.database.SampleSimDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SampleSimDao implements SampleRepository {
    private static final String SOURCE = "DAO => ";

    private static final SampleSimDatabase database = SampleSimDatabase.getInstance();

    private static int lastInsertId;

    // -------------------------------------------- CRUD => CREATE

    @Override
    public List<Sample> createAll(List<Sample> samples) {
        List<Sample> entities = database.getDatabase(); // import

        boolean isDone;
        List<Sample> results = SampleSimDatabase.generateId(samples, lastInsertId);
        lastInsertId = lastInsertId + results.size();
        isDone = true;

        if (isDone) {
            entities.addAll(results);
            database.setDatabase(entities); // export
            System.out.println(SOURCE + "CREATE=TRUE/SIZE=" + results.size() + "/all");
        } else {
            System.out.println(SOURCE + "CREATE=FALSE/SIZE=" + 0 + "/all");
        }

        return results;
    }

    @Override
    public Optional<Sample> create(Sample sample) {
        List<Sample> entities = database.getDatabase(); // import

        boolean isDone;
        lastInsertId++;
        Sample entity = new Sample();
        entity.setId(lastInsertId);
        entity.setText(sample.getText());
        isDone = true;

        if (isDone) {
            entities.add(entity);
            database.setDatabase(entities); // export
            System.out.println(SOURCE + "CREATE=TRUE/ID=" + entity.getId());
        } else {
            entity = null;
            System.out.println(SOURCE + "CREATE=FALSE/ID=" + 0);
        }

        return Optional.ofNullable(entity);
    }

    // -------------------------------------------- CRUD => READ

    @Override
    public List<Sample> findAll() {
        List<Sample> entities = database.getDatabase(); // import
        boolean isDone = true;

        if (isDone) {
            System.out.println(SOURCE + "FIND=TRUE/SIZE=" + entities.size() + "/all");
        } else {
            System.out.println(SOURCE + "FIND=FALSE/SIZE=" + 0 + "/all");
        }

        return entities;
    }

    @Override
    public List<Sample> findByText(String text) {
        List<Sample> entities = database.getDatabase(); // import
        List<Sample> results = new ArrayList<>();

        for (Sample item : entities) {
            if (item.getText().equals(text)) {
                results.add(item);
            }
        }

        if (results.isEmpty()) {
            System.out.println(SOURCE + "FIND=FALSE/SIZE=" + 0 + "/" + text);
        } else {
            System.out.println(SOURCE + "FIND=TRUE/SIZE=" + results.size() + "/" + text);
        }

        return results;
    }

    @Override
    public Optional<Sample> find(Integer id) {
        List<Sample> entities = database.getDatabase(); // import

        int index = 0;
        boolean isFound = false;
        for (Sample item : entities) {
            if (item.getId().equals(id)) {
                index = entities.indexOf(item);
                isFound = true;
            }
        }

        Sample entity;
        if (isFound) {
            System.out.println(SOURCE + "FIND=TRUE/ID=" + id);
            entity = entities.get(index);
        } else {
            System.out.println(SOURCE + "FIND=FALSE/ID=" + id);
            entity = null;
        }

        return Optional.ofNullable(entity);
    }

    // -------------------------------------------- CRUD => UPDATE

    @Override
    public Optional<Sample> update(Integer id, Sample sampleData) {
        List<Sample> entities = database.getDatabase(); // import

        int index = 0;
        boolean isFound = false;
        for (Sample item : entities) {
            if (item.getId().equals(id)) {
                System.out.println(SOURCE + "FIND=TRUE/ID=" + id);
                index = entities.indexOf(item);
                isFound = true;
            }
        }

        Sample entity = new Sample();
        if (isFound) {
            entity.setId(id);
            entity.setText(sampleData.getText());
            entities.get(index).setText(sampleData.getText());
            database.setDatabase(entities); // export
            System.out.println(SOURCE + "UPDATE=TRUE/ID=" + id);
        } else {
            entity = null;
            System.out.println(SOURCE + "UPDATE=FALSE/ID=" + id);
        }

        return Optional.ofNullable(entity);
    }

    // -------------------------------------------- CRUD => DELETE

    @Override
    public boolean delete(Integer id) {
        List<Sample> entities = database.getDatabase(); // import

        int index = 0;
        boolean isFound = false;
        for (Sample item : entities) {
            if (item.getId().equals(id)) {
                System.out.println(SOURCE + "FIND=TRUE/ID=" + id);
                index = entities.indexOf(item);
                isFound = true;
            }
        }

        if (isFound) {
            entities.remove(index);
            database.setDatabase(entities); // export
            System.out.println(SOURCE + "DELETE=TRUE/ID=" + id);
        } else {
            System.out.println(SOURCE + "DELETE=FALSE/ID=" + id);
        }

        return isFound;
    }
}
