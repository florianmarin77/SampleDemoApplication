package com.asi.sda.sample.repository;

import com.asi.sda.sample.Sample;
import com.asi.sda.sample.database.SampleDatabase;
import net.bytebuddy.utility.RandomString;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class SampleDao implements SampleRepository {
    private static final String SOURCE = "DAO => ";

    private static final SampleDatabase database = SampleDatabase.getInstance();

    private static int lastInsertId;

    // -------------------------------------------- CRUD => CREATE

    @Override
    public List<Sample> createAll(List<Sample> samples) {
        List<Sample> entities = database.getDatabase(); // import

        boolean isDone;
        List<Sample> results = SampleDatabase.generateId(samples, lastInsertId);
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
        boolean isDone = true; // scenario setup

        List<Sample> entities = new ArrayList<>();

        if (isDone) {
            Random random = new Random();
            int fakeSize = random.nextInt(9) + 1;
            RandomString randomString = new RandomString();

            for (int k = 0; k < fakeSize; k++) {
                String fakeText = randomString.nextString();
                Sample entity = new Sample();

                entity.setId(k + 1);
                entity.setText(fakeText);
                entities.add(entity);
            }
            System.out.println("Return dao data: " + entities);
            System.out.println(SOURCE + "FIND=TRUE/SIZE=" + entities.size() + "/all");
        } else {
            System.out.println(SOURCE + "FIND=FALSE/SIZE=" + 0 + "/all");
        }

        return entities;
    }

    @Override
    public List<Sample> findByText(String text) {
        boolean isDone = true; // scenario setup

        List<Sample> entities = new ArrayList<>();

        if (isDone) {
            Random random = new Random();
            int fakeSize = random.nextInt(9) + 1;
            for (int k = 0; k < fakeSize; k++) {
                Sample entity = new Sample();

                entity.setId(k + 1);
                entity.setText(text);
                entities.add(entity);
            }
            System.out.println("Return dao data: " + entities);
            System.out.println(SOURCE + "FIND=TRUE/SIZE=" + entities.size() + "/" + text);
        } else {
            System.out.println(SOURCE + "FIND=FALSE/SIZE=" + 0 + "/" + text);
        }

        return entities;
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
