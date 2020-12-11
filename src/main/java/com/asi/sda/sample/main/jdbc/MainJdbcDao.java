package com.asi.sda.sample.main.jdbc;

import com.asi.sda.sample.Sample;
import com.asi.sda.sample.database.SampleSimDatabase;
import com.asi.sda.sample.loader.SampleLineLoader;
import com.asi.sda.sample.loader.SampleLoader;
import com.asi.sda.sample.loader.SampleSplitLoader;
import com.asi.sda.sample.repository.SampleJdbcDao;
import com.asi.sda.sample.repository.SampleRepository;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

public class MainJdbcDao {
    private static final SampleSimDatabase database = SampleSimDatabase.getInstance();

    private static final boolean JOKER = true; // loader scenario

    public static void main(String[] args) throws URISyntaxException {
        SampleRepository dao = new SampleJdbcDao();
        SampleJdbcDao jdbcDao = new SampleJdbcDao();

        // create sample table
        System.out.println("Table created: " + jdbcDao.createTable());
        database.displayTable(database.getSampleList());

        // populate database by loader scenario
        if (JOKER) {
            SampleLoader loader = new SampleSplitLoader(); // database resources => sampleList.csv
            Path path = Paths.get(ClassLoader.getSystemResource("data/sample/sampleList.csv").toURI());
            System.out.println(dao.createAll(loader.loadData(Paths.get(String.valueOf(path)))));
            database.displayTable(database.getSampleList());
        } else {
            SampleLoader loader = new SampleLineLoader(); // database resources => sampleList.txt
            Path path = Paths.get(ClassLoader.getSystemResource("data/sample/sampleList.txt").toURI());
            System.out.println(dao.createAll(loader.loadData(Paths.get(String.valueOf(path)))));
            database.displayTable(database.getSampleList());
        }

        Sample sample;
        List<Sample> samples;
        Optional<Sample> optional;

        // create single sample (id = 27)
        sample = dao.create(new Sample("ABCDEFGHIJKLMNOPQRSTUVWXYZ"));
        System.out.println(sample);
        database.displayTable(database.getSampleList());

        // find all samples (found list size = 27)
        samples = dao.findAll();
        System.out.println(samples);
        // find by text (found list size = 1)
        samples = dao.findByText("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        System.out.println(samples);
        // find by good id
        optional = dao.find(27);
        System.out.println(optional);
        database.displayTable(database.getSampleList());

        // integral sample update id = 27
        optional = dao.update(27, new Sample("abcdefghijklmnopqrstuvwxyz"));
        System.out.println(optional);
        database.displayTable(database.getSampleList());

        // delete good id = 27
        optional = dao.delete(27);
        System.out.println(optional);
        database.displayTable(database.getSampleList());

        // delete sample table
        System.out.println("Table deleted: " + jdbcDao.deleteTable());
    }
}
