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

        // create single sample (id=27)
        dao.create(new Sample("ABCDEFGHIJKLMNOPQRSTUVWXYZ"));

        // find all samples (found list size = 27)
        dao.findAll();

        // find by text (found list size = 1)
        dao.findByText("ABCDEFGHIJKLMNOPQRSTUVWXYZ");

        // find by good id
        dao.find(1);

        // integral sample update id = 27
        dao.update(27, new Sample("abcdefghijklmnopqrstuvwxyz"));

        // delete good id = 27
        dao.delete(27);

        // close resources
        em.close();
        emf.close();

        // drop sample table
//        SampleJdbcDao sampleJdbcDao = new SampleJdbcDao();
//        sampleJdbcDao.deleteTable();
    }
}
