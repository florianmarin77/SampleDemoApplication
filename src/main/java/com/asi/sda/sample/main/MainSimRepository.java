package com.asi.sda.sample.main;

import com.asi.sda.sample.Sample;
import com.asi.sda.sample.database.SampleSimDatabase;
import com.asi.sda.sample.repository.SampleRepository;
import com.asi.sda.sample.repository.SampleSimDao;

import java.util.ArrayList;
import java.util.List;

public class MainSimRepository {
    private static final SampleSimDatabase database = SampleSimDatabase.getInstance();

    public static void main(String[] args) {
        SampleRepository dao = new SampleSimDao();

        Sample sample1 = new Sample("0123456789");
        Sample sample2 = new Sample("9876543210");
        List<Sample> samples = new ArrayList<>();
        samples.add(sample1);
        samples.add(sample2);

        System.out.println("<=== REPOSITORY SIMULATOR ===>");
        System.out.println();

        // create
        System.out.println(dao.create(new Sample("repository")));
        System.out.println();
        SampleSimDatabase.displayDataTable(database.getSampleList());

        // createAll
        System.out.println("Samples created: " + dao.createAll(samples));
        System.out.println();
        SampleSimDatabase.displayDataTable(database.getSampleList());

        // read
        System.out.println(dao.find(1));
        System.out.println();
        System.out.println(dao.findAll());
        System.out.println();
        System.out.println(dao.findByText("repository"));
        System.out.println();
        SampleSimDatabase.displayDataTable(database.getSampleList());

        // update
        dao.update(2, new Sample("macarena"));
        SampleSimDatabase.displayDataTable(database.getSampleList());

        // delete
        dao.delete(3);
        SampleSimDatabase.displayDataTable(database.getSampleList());
    }
}
