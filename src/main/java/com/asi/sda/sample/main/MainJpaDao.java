package com.asi.sda.sample.main;

import com.asi.sda.sample.Sample;
import com.asi.sda.sample.loader.SampleLineLoader;
import com.asi.sda.sample.loader.SampleLoader;
import com.asi.sda.sample.loader.SampleSplitLoader;
import com.asi.sda.sample.repository.SampleJpaDao;
import com.asi.sda.sample.repository.SampleRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MainJpaDao {
    /**
     * private static final Logger LOGGER = Logger.getLogger(MainJpaDao.class.getName());
     * private static final Logger LOGGER = LogManager.getLogger(MainJpaDao.class); => log4j2
     */

    public static final boolean JOKER = true; // loader scenario

    public static void main(String[] args) throws URISyntaxException {

        /***********
         * JPA DAO *
         ***********/

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("MacroMedia");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        SampleRepository sampleJpaDao = new SampleJpaDao(entityManager);


        // populate database by loader scenario

        if (JOKER) {
            Path sampleListPath = Paths.get(ClassLoader.getSystemResource("data/sample/sampleList.csv").toURI());
            SampleLoader sampleSplitLoader = new SampleSplitLoader(); // database resources => sampleList.csv
            sampleJpaDao.createAll(sampleSplitLoader.loadData(Paths.get(String.valueOf(sampleListPath))));
        } else {
            Path sampleListPath = Paths.get(ClassLoader.getSystemResource("data/sample/sampleList.txt").toURI());
            SampleLoader sampleLineLoader = new SampleLineLoader(); // database resources => sampleList.txt
            sampleJpaDao.createAll(sampleLineLoader.loadData(Paths.get(String.valueOf(sampleListPath))));
        }

        // create single sample (id=27)
        sampleJpaDao.create(new Sample("ABCDEFGHIJKLMNOPQRSTUVWXYZ"));

        // find all samples (found list size = 27)
        sampleJpaDao.findAll();

        // find by text (found list size = 1)
        sampleJpaDao.findByText("ABCDEFGHIJKLMNOPQRSTUVWXYZ");

        // find by good id
        sampleJpaDao.find(1);

        // integral sample update id=27
        Sample sampleData = new Sample("abcdefghijklmnopqrstuvwxyz");
        sampleJpaDao.update(27, sampleData);

        // delete good id=27 from samples table
        sampleJpaDao.delete(27);

        // close resources
        entityManager.close();
        entityManagerFactory.close();

        // drop sample table
//        SampleJdbcDao sampleJdbcDao = new SampleJdbcDao();
//        sampleJdbcDao.deleteTable();
    }
}
