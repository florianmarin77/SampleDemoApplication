package com.lafi.sda.sample.main.jpa;

import com.lafi.sda.sample.model.Sample;
import com.lafi.sda.sample.database.SampleSimDatabase;
import com.lafi.sda.sample.loader.SampleLineLoader;
import com.lafi.sda.sample.loader.SampleLoader;
import com.lafi.sda.sample.loader.SampleSplitLoader;
import com.lafi.sda.sample.repository.SampleJdbcDao;
import com.lafi.sda.sample.repository.SampleJpaDao;
import com.lafi.sda.sample.repository.SampleRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

public class MainJpaDao {
    private static final SampleSimDatabase database = SampleSimDatabase.getInstance();

    private static final boolean JOKER = true; // loader scenario

    public static void main(String[] args) throws URISyntaxException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MacroMedia");
        EntityManager em = emf.createEntityManager();

        SampleRepository dao = new SampleJpaDao(em);

        SampleJdbcDao jdbcDao = new SampleJdbcDao(); // drop table

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

        // drop sample table
        System.out.println("Table deleted: " + jdbcDao.deleteTable());

        // close resources
        em.close();
        emf.close();
    }
}
