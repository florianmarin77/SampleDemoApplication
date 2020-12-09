package com.asi.sda.sample.main.jpa;

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
import java.util.List;
import java.util.Optional;

public class MainJpaDao {
    /**
     * private static final Logger LOGGER = Logger.getLogger(MainJpaDao.class.getName());
     * private static final Logger LOGGER = LogManager.getLogger(MainJpaDao.class); => log4j2
     */

    private static final boolean JOKER = true; // loader scenario

    public static void main(String[] args) throws URISyntaxException {

        /***********
         * JPA DAO *
         ***********/

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MacroMedia");
        EntityManager em = emf.createEntityManager();

        SampleRepository dao = new SampleJpaDao(em);

        // populate database by loader scenario
        if (JOKER) {
            SampleLoader loader = new SampleSplitLoader(); // database resources => sampleList.csv
            Path path = Paths.get(ClassLoader.getSystemResource("data/sample/sampleList.csv").toURI());
            dao.createAll(loader.loadData(Paths.get(String.valueOf(path))));
        } else {
            SampleLoader loader = new SampleLineLoader(); // database resources => sampleList.txt
            Path path = Paths.get(ClassLoader.getSystemResource("data/sample/sampleList.txt").toURI());
            dao.createAll(loader.loadData(Paths.get(String.valueOf(path))));
        }

        Sample sample;
        List<Sample> samples;
        Optional<Sample> optional;

        // create single sample (id=27)
        sample = dao.create(new Sample("ABCDEFGHIJKLMNOPQRSTUVWXYZ"));
        System.out.println(sample);

        // find all samples (found list size = 27)
        samples = dao.findAll();
        System.out.println(samples);

        // find by text (found list size = 1)
        samples = dao.findByText("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        System.out.println(samples);

        // find by good id
        optional = dao.find(1);
        System.out.println(optional);

        // integral sample update id = 27
        optional = dao.update(27, new Sample("abcdefghijklmnopqrstuvwxyz"));
        System.out.println(optional);

        // delete good id = 27
        optional = dao.delete(27);
        System.out.println(optional);

        // close resources
        em.close();
        emf.close();

        // drop sample table
//        SampleJdbcDao sampleJdbcDao = new SampleJdbcDao();
//        sampleJdbcDao.deleteTable();
    }
}
