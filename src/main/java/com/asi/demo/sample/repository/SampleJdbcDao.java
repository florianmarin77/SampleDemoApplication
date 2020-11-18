package com.asi.demo.sample.repository;

import com.asi.demo.sample.Sample;
import com.asi.demo.sample.database.SampleDatabase;
import net.bytebuddy.utility.RandomString;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class SampleJdbcDao implements SampleRepository {
    private static final String SOURCE = "JDBC-DAO => ";

    // -------------------------------------------- CRUD => CREATE

    @Override
    public List<Sample> createAll(List<Sample> samples) {
        boolean isDone = true; // scenario setup

        List<Sample> entities = SampleDatabase.displayDataTable(samples);

        if (isDone) {
            System.out.println(entities);
            System.out.println(SOURCE + "CREATE=TRUE/SIZE=" + entities.size() + "/all");
        } else {
            System.out.println(SOURCE + "CREATE=FALSE/SIZE=" + 0 + "/all");
        }

        return entities;
    }

    @Override
    public Optional<Sample> create(Sample sample) {
        boolean isDone = true; // scenario setup

        Sample entity = new Sample();

        if (isDone) {
            Random random = new Random();
            int fakeId = random.nextInt(9) + 1;

            entity.setId(fakeId);
            entity.setText(sample.getText());

            System.out.println(entity);
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
            System.out.println(entities);
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
            System.out.println(entities);
            System.out.println(SOURCE + "FIND=TRUE/SIZE=" + entities.size() + "/" + text);
        } else {
            System.out.println(SOURCE + "FIND=FALSE/SIZE=" + 0 + "/" + text);
        }

        return entities;
    }

    @Override
    public Optional<Sample> find(Integer id) {
        boolean isDone = true; // scenario setup

        Sample entity = new Sample();

        if (isDone) {
            RandomString randomString = new RandomString();
            String fakeText = randomString.nextString();

            entity.setId(id);
            entity.setText(fakeText);

            System.out.println(entity);
            System.out.println(SOURCE + "FIND=TRUE/ID=" + id);
        } else {
            entity = null;
            System.out.println(SOURCE + "FIND=FALSE/ID=" + id);
        }

        return Optional.ofNullable(entity);
    }

    // -------------------------------------------- CRUD => UPDATE

    @Override
    public Optional<Sample> update(Integer id, Sample sampleData) {
        boolean isDone = true; // scenario setup

        Sample entity = new Sample();

        if (isDone) {
            entity.setId(id);
            entity.setText(sampleData.getText());
            System.out.println(entity);
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
        boolean isDone = true; // scenario setup

        if (isDone) {
            System.out.println(SOURCE + "DELETE=TRUE/ID=" + id);
        } else {
            System.out.println(SOURCE + "DELETE=FALSE/ID=" + id);
        }

        return isDone;
    }

    @Override
    public boolean deleteAll() {
        boolean isDone = true; // scenario setup

        if (isDone) {
            Random random = new Random();
            int fakeSize = random.nextInt(9) + 1;
            System.out.println(SOURCE + "DELETE=TRUE/SIZE=" + fakeSize + "/all");
        } else {
            System.out.println(SOURCE + "DELETE=FALSE/SIZE=" + 0 + "/all");
        }

        return isDone;
    }
}
