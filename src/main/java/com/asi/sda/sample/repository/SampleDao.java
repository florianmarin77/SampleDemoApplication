package com.asi.sda.sample.repository;

import com.asi.sda.ConsoleMenu;
import com.asi.sda.sample.Sample;
import com.asi.sda.sample.database.SampleDatabase;
import net.bytebuddy.utility.RandomString;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class SampleDao implements SampleRepository {
    private static final String SOURCE = "DAO => ";

    private static final SampleDatabase database = new SampleDatabase();

    // -------------------------------------------- CRUD => CREATE

    @Override
    public List<Sample> createAll(List<Sample> samples) {
        boolean isDone = true; // scenario setup

        List<Sample> entities = SampleDatabase.populateByList(samples);

        if (isDone) {
            System.out.println("Return dao data: " + entities);
            System.out.println(SOURCE + "CREATE=TRUE/SIZE=" + entities.size() + "/all");
        } else {
            System.out.println(SOURCE + "CREATE=FALSE/SIZE=" + 0 + "/all");
        }

        ConsoleMenu.lastInsertId = entities.size();
        SampleDatabase.displayDataTable(entities);

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

            System.out.println("Return dao data: " + entity);
            System.out.println(SOURCE + "CREATE=TRUE/ID=" + entity.getId());
        } else {
            entity = null;
            System.out.println(SOURCE + "CREATE=FALSE/ID=" + 0);
        }

        ConsoleMenu.lastInsertId++;

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
        boolean isDone = true; // scenario setup

        Sample entity = new Sample();

        if (isDone) {
            RandomString randomString = new RandomString();
            String fakeText = randomString.nextString();

            entity.setId(id);
            entity.setText(fakeText);

            System.out.println("Return dao data: " + entity);
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
            System.out.println("Return dao data: " + entity);
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
}
