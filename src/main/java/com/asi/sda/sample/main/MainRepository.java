package com.asi.sda.sample.main;

import com.asi.sda.sample.Sample;
import com.asi.sda.sample.database.SampleDatabase;
import com.asi.sda.sample.repository.SampleDao;
import com.asi.sda.sample.repository.SampleRepository;

import java.util.ArrayList;
import java.util.List;

public class MainRepository {
    private static final SampleDatabase database = SampleDatabase.getInstance();

    public static void main(String[] args) {
        SampleRepository dao = new SampleDao();

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
        SampleDatabase.displayDataTable(database.getDatabase());

        // createAll
        System.out.println("Samples created: " + dao.createAll(samples));
        System.out.println();
        SampleDatabase.displayDataTable(database.getDatabase());

        // read
        System.out.println(dao.find(1));
        System.out.println();
        System.out.println(dao.findAll());
        System.out.println();
        System.out.println(dao.findByText("repository"));
        System.out.println();
        SampleDatabase.displayDataTable(database.getDatabase());

        // update
        System.out.println(dao.update(2, new Sample("macarena")));
        System.out.println();
        SampleDatabase.displayDataTable(database.getDatabase());

        // delete
        System.out.println("Sample deleted: " + dao.delete(3));
        System.out.println();
        SampleDatabase.displayDataTable(database.getDatabase());
    }
}
