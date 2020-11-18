package com.asi.demo.sample.main;

import com.asi.demo.sample.Sample;
import com.asi.demo.sample.SampleMapper;
import com.asi.demo.sample.controller.SampleController;
import com.asi.demo.sample.repository.SampleHibernateDao;
import com.asi.demo.sample.repository.SampleJdbcDao;
import com.asi.demo.sample.repository.SampleJpaDao;
import com.asi.demo.sample.repository.SampleRepository;
import com.asi.demo.sample.service.SampleHibernateServiceImpl;
import com.asi.demo.sample.service.SampleJdbcServiceImpl;
import com.asi.demo.sample.service.SampleJpaServiceImpl;
import com.asi.demo.sample.service.SampleService;

import java.util.ArrayList;
import java.util.List;

public class MainSample {
    public static void main(String[] args) {
        testHibernateImplementation();
        testJdbcImplementation();
        testJpaImplementation();
    }

    private static void testHibernateImplementation() {
        SampleRepository sampleHibernateDao = new SampleHibernateDao();
        SampleService sampleHibernateService = new SampleHibernateServiceImpl(sampleHibernateDao);
        SampleController sampleController = new SampleController(sampleHibernateService);

        Sample sample1 = new Sample("#0123456789");
        Sample sample2 = new Sample("@0123456789");
        List<Sample> samples = new ArrayList<>();
        samples.add(sample1);
        samples.add(sample2);

        System.out.println("============================================================> REPOSITORY BY HIBERNATE ");

        // create
        System.out.println(sampleHibernateDao.create(new Sample("repository create")));
        System.out.println();
        System.out.println("Samples created: " + sampleHibernateDao.createAll(samples));
        System.out.println();

        // read
        System.out.println(sampleHibernateDao.find(5));
        System.out.println();
        System.out.println(sampleHibernateDao.findAll());
        System.out.println();
        System.out.println(sampleHibernateDao.findByText("repository read"));
        System.out.println();

        // update
        System.out.println(sampleHibernateDao.update(7, new Sample("repository update")));
        System.out.println();

        // delete
        System.out.println("Sample deleted: " + sampleHibernateDao.delete(3));
        System.out.println();
        System.out.println("Samples deleted: " + sampleHibernateDao.deleteAll());
        System.out.println();

        System.out.println("============================================================> SERVICE BY HIBERNATE");

        // create
        System.out.println(sampleHibernateService.create(SampleMapper.toRequestDto(new Sample("service create"))));
        System.out.println();
        System.out.println("Samples created: " + sampleHibernateService.createAll(SampleMapper.toRequestDto(samples)));
        System.out.println();

        // read
        System.out.println(sampleHibernateService.find(5));
        System.out.println();
        System.out.println(sampleHibernateService.findAll());
        System.out.println();
        System.out.println(sampleHibernateService.findByText("service read"));
        System.out.println();

        // update
        System.out.println(sampleHibernateService.update(7, new Sample("service update")));
        System.out.println();

        // delete
        System.out.println("Sample deleted: " + sampleHibernateService.delete(3));
        System.out.println();
        System.out.println("Samples deleted: " + sampleHibernateService.deleteAll());
        System.out.println();

        System.out.println("============================================================> CONTROLLER BY HIBERNATE");

        // save by faker
        System.out.println(sampleController.saveAllByFaker());
        System.out.println();

        // save by loader
        System.out.println(sampleController.saveAllByLoader());
        System.out.println();

        // create by save
        System.out.println(sampleController.save(SampleMapper.toRequestDto(new Sample("controller create"))));

        // find by id
        System.out.println(sampleController.getById(5));
        System.out.println();

        // find all
        System.out.println(sampleController.getAll());
        System.out.println();

        // find by text
        System.out.println(sampleController.getByText("controller read"));
        System.out.println();

        // update by id
        System.out.println(sampleController.updateById(7, new Sample("controller update")));
        System.out.println();

        // delete by id
        System.out.println("Sample deleted: " + sampleController.deleteById(3));
        System.out.println();

        // delete all
        System.out.println("Samples deleted: " + sampleController.deleteAll());
        System.out.println();
    }

    private static void testJdbcImplementation() {
        SampleRepository sampleJdbcDao = new SampleJdbcDao();
        SampleService sampleJdbcService = new SampleJdbcServiceImpl(sampleJdbcDao);
        SampleController sampleController = new SampleController(sampleJdbcService);

        Sample sample1 = new Sample("#0123456789");
        Sample sample2 = new Sample("@0123456789");
        List<Sample> samples = new ArrayList<>();
        samples.add(sample1);
        samples.add(sample2);

        System.out.println("============================================================> REPOSITORY BY JDBC ");

        // create
        System.out.println(sampleJdbcDao.create(new Sample("repository create")));
        System.out.println();
        System.out.println("Samples created: " + sampleJdbcDao.createAll(samples));
        System.out.println();

        // read
        System.out.println(sampleJdbcDao.find(5));
        System.out.println();
        System.out.println(sampleJdbcDao.findAll());
        System.out.println();
        System.out.println(sampleJdbcDao.findByText("repository read"));
        System.out.println();

        // update
        System.out.println(sampleJdbcDao.update(7, new Sample("repository update")));
        System.out.println();

        // delete
        System.out.println("Sample deleted: " + sampleJdbcDao.delete(3));
        System.out.println();
        System.out.println("Samples deleted: " + sampleJdbcDao.deleteAll());
        System.out.println();

        System.out.println("============================================================> SERVICE BY JDBC");

        // create
        System.out.println(sampleJdbcService.create(SampleMapper.toRequestDto(new Sample("service create"))));
        System.out.println();
        System.out.println("Samples created: " + sampleJdbcService.createAll(SampleMapper.toRequestDto(samples)));
        System.out.println();

        // read
        System.out.println(sampleJdbcService.find(5));
        System.out.println();
        System.out.println(sampleJdbcService.findAll());
        System.out.println();
        System.out.println(sampleJdbcService.findByText("service read"));
        System.out.println();

        // update
        System.out.println(sampleJdbcService.update(7, new Sample("service update")));
        System.out.println();

        // delete
        System.out.println("Sample deleted: " + sampleJdbcService.delete(3));
        System.out.println();
        System.out.println("Samples deleted: " + sampleJdbcService.deleteAll());
        System.out.println();

        System.out.println("============================================================> CONTROLLER BY JDBC");

        // save by faker
        System.out.println(sampleController.saveAllByFaker());
        System.out.println();

        // save by loader
        System.out.println(sampleController.saveAllByLoader());
        System.out.println();

        // create by save
        System.out.println(sampleController.save(SampleMapper.toRequestDto(new Sample("controller create"))));

        // find by id
        System.out.println(sampleController.getById(5));
        System.out.println();

        // find all
        System.out.println(sampleController.getAll());
        System.out.println();

        // find by text
        System.out.println(sampleController.getByText("controller read"));
        System.out.println();

        // update by id
        System.out.println(sampleController.updateById(7, new Sample("controller update")));
        System.out.println();

        // delete by id
        System.out.println("Sample deleted: " + sampleController.deleteById(3));
        System.out.println();

        // delete all
        System.out.println("Samples deleted: " + sampleController.deleteAll());
        System.out.println();
    }

    private static void testJpaImplementation() {
        SampleRepository sampleJpaDao = new SampleJpaDao();
        SampleService sampleJpaService = new SampleJpaServiceImpl(sampleJpaDao);
        SampleController sampleController = new SampleController(sampleJpaService);

        Sample sample1 = new Sample("#0123456789");
        Sample sample2 = new Sample("@0123456789");
        List<Sample> samples = new ArrayList<>();
        samples.add(sample1);
        samples.add(sample2);

        System.out.println("============================================================> REPOSITORY BY JPA ");

        // create
        System.out.println(sampleJpaDao.create(new Sample("repository create")));
        System.out.println();
        System.out.println("Samples created: " + sampleJpaDao.createAll(samples));
        System.out.println();

        // read
        System.out.println(sampleJpaDao.find(5));
        System.out.println();
        System.out.println(sampleJpaDao.findAll());
        System.out.println();
        System.out.println(sampleJpaDao.findByText("repository read"));
        System.out.println();

        // update
        System.out.println(sampleJpaDao.update(7, new Sample("repository update")));
        System.out.println();

        // delete
        System.out.println("Sample deleted: " + sampleJpaDao.delete(3));
        System.out.println();
        System.out.println("Samples deleted: " + sampleJpaDao.deleteAll());
        System.out.println();

        System.out.println("============================================================> SERVICE BY JPA");

        // create
        System.out.println(sampleJpaService.create(SampleMapper.toRequestDto(new Sample("service create"))));
        System.out.println();
        System.out.println("Samples created: " + sampleJpaService.createAll(SampleMapper.toRequestDto(samples)));
        System.out.println();

        // read
        System.out.println(sampleJpaService.find(5));
        System.out.println();
        System.out.println(sampleJpaService.findAll());
        System.out.println();
        System.out.println(sampleJpaService.findByText("service read"));
        System.out.println();

        // update
        System.out.println(sampleJpaService.update(7, new Sample("service update")));
        System.out.println();

        // delete
        System.out.println("Sample deleted: " + sampleJpaService.delete(3));
        System.out.println();
        System.out.println("Samples deleted: " + sampleJpaService.deleteAll());
        System.out.println();

        System.out.println("============================================================> CONTROLLER BY JPA");

        // save by faker
        System.out.println(sampleController.saveAllByFaker());
        System.out.println();

        // save by loader
        System.out.println(sampleController.saveAllByLoader());
        System.out.println();

        // create by save
        System.out.println(sampleController.save(SampleMapper.toRequestDto(new Sample("controller create"))));

        // find by id
        System.out.println(sampleController.getById(5));
        System.out.println();

        // find all
        System.out.println(sampleController.getAll());
        System.out.println();

        // find by text
        System.out.println(sampleController.getByText("controller read"));
        System.out.println();

        // update by id
        System.out.println(sampleController.updateById(7, new Sample("controller update")));
        System.out.println();

        // delete by id
        System.out.println("Sample deleted: " + sampleController.deleteById(3));
        System.out.println();

        // delete all
        System.out.println("Samples deleted: " + sampleController.deleteAll());
        System.out.println();
    }
}
