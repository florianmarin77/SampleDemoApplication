package com.asi.sda.sample.main.jpa;

import com.asi.sda.sample.Sample;
import com.asi.sda.sample.SampleMapper;
import com.asi.sda.sample.SampleResponseDto;
import com.asi.sda.sample.loader.SampleLineLoader;
import com.asi.sda.sample.loader.SampleLoader;
import com.asi.sda.sample.loader.SampleSplitLoader;
import com.asi.sda.sample.repository.SampleJpaDao;
import com.asi.sda.sample.repository.SampleRepository;
import com.asi.sda.sample.service.SampleJpaService;
import com.asi.sda.sample.service.SampleService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class MainJpaService {
    /**
     * private static final Logger LOGGER = Logger.getLogger(MainJpaService.class.getName());
     * private static final Logger LOGGER = LogManager.getLogger(MainJpaService.class); => log4j2
     */

    private static final boolean JOKER = true; // loader scenario

    public static void main(String[] args) throws URISyntaxException {

        /***************
         * JPA SERVICE *
         ***************/

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MacroMedia");
        EntityManager em = emf.createEntityManager();

        SampleRepository dao = new SampleJpaDao(em);
        SampleService service = new SampleJpaService(dao);

        // populate database by loader scenario
        if (JOKER) {
            SampleLoader loader = new SampleSplitLoader(); // database resources => sampleList.csv
            Path path = Paths.get(ClassLoader.getSystemResource("data/sample/sampleList.csv").toURI());
            service.createAll(SampleMapper.toRequestDtos(loader.loadData(Paths.get(String.valueOf(path)))));
        } else {
            SampleLoader loader = new SampleLineLoader(); // database resources => sampleList.txt
            Path path = Paths.get(ClassLoader.getSystemResource("data/sample/sampleList.txt").toURI());
            service.createAll(SampleMapper.toRequestDtos(loader.loadData(Paths.get(String.valueOf(path)))));
        }

        SampleResponseDto response;
        List<SampleResponseDto> responses;

        // create single sample (id=27)
        response = service.create(SampleMapper.toRequestDto(new Sample("ABCDEFGHIJKLMNOPQRSTUVWXYZ")));
        System.out.println(response);

        // find all samples (found list size = 27)
        responses = service.findAll();
        System.out.println(responses);

        // find by text (found list size = 1)
        responses = service.findByText("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        System.out.println(responses);

        // find by good id
        response = service.find(1);
        System.out.println(response);

        // integral sample update id = 27
        response = service.update(27, new Sample("abcdefghijklmnopqrstuvwxyz"));
        System.out.println(response);

        // delete good id = 27
        response = service.delete(27);
        System.out.println(response);

        // close resources
        em.close();
        emf.close();

        // drop sample table
//        SampleJdbcDao sampleJdbcDao = new SampleJdbcDao();
//        sampleJdbcDao.deleteTable();
    }
}
