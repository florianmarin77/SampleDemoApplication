package com.lafi.sda.sample.main.jpa;

import com.lafi.sda.sample.model.Sample;
import com.lafi.sda.sample.model.SampleMapper;
import com.lafi.sda.sample.model.SampleResponseDto;
import com.lafi.sda.sample.database.SampleSimDatabase;
import com.lafi.sda.sample.loader.SampleLineLoader;
import com.lafi.sda.sample.loader.SampleLoader;
import com.lafi.sda.sample.loader.SampleSplitLoader;
import com.lafi.sda.sample.repository.SampleJdbcDao;
import com.lafi.sda.sample.repository.SampleJpaDao;
import com.lafi.sda.sample.repository.SampleRepository;
import com.lafi.sda.sample.service.SampleJpaService;
import com.lafi.sda.sample.service.SampleService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class MainJpaService {
    private static final SampleSimDatabase database = SampleSimDatabase.getInstance();

    private static final boolean JOKER = true; // loader scenario

    public static void main(String[] args) throws URISyntaxException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MacroMedia");
        EntityManager em = emf.createEntityManager();

        SampleRepository dao = new SampleJpaDao(em);
        SampleService service = new SampleJpaService(dao);

        SampleJdbcDao jdbcDao = new SampleJdbcDao(); // drop table

        // populate database by loader scenario
        if (JOKER) {
            SampleLoader loader = new SampleSplitLoader(); // database resources => sampleList.csv
            Path path = Paths.get(ClassLoader.getSystemResource("data/sample/sampleList.csv").toURI());
            service.createAll(SampleMapper.toRequestDtos(loader.loadData(Paths.get(String.valueOf(path)))));
            database.displayTable(database.getSampleList());
        } else {
            SampleLoader loader = new SampleLineLoader(); // database resources => sampleList.txt
            Path path = Paths.get(ClassLoader.getSystemResource("data/sample/sampleList.txt").toURI());
            service.createAll(SampleMapper.toRequestDtos(loader.loadData(Paths.get(String.valueOf(path)))));
            database.displayTable(database.getSampleList());
        }

        SampleResponseDto response;
        List<SampleResponseDto> responses;

        // create single sample (id=27)
        response = service.create(SampleMapper.toRequestDto(new Sample("ABCDEFGHIJKLMNOPQRSTUVWXYZ")));
        System.out.println(response);
        database.displayTable(database.getSampleList());

        // find all samples (found list size = 27)
        responses = service.findAll();
        System.out.println(responses);
        // find by text (found list size = 1)
        responses = service.findByText("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        System.out.println(responses);
        // find by good id
        response = service.find(27);
        System.out.println(response);
        database.displayTable(database.getSampleList());

        // integral sample update id = 27
        response = service.update(27, SampleMapper.toRequestDto(new Sample("abcdefghijklmnopqrstuvwxyz")));
        System.out.println(response);
        database.displayTable(database.getSampleList());

        // delete good id = 27
        response = service.delete(27);
        System.out.println(response);
        database.displayTable(database.getSampleList());

        // drop sample table
        System.out.println("Table deleted: " + jdbcDao.deleteTable());

        // close resources
        em.close();
        emf.close();
    }
}
