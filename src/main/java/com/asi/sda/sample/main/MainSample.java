package com.asi.sda.sample.main;

import com.asi.sda.sample.Sample;
import com.asi.sda.sample.SampleMapper;
import com.asi.sda.sample.controller.SampleController;
import com.asi.sda.sample.repository.SampleDao;
import com.asi.sda.sample.repository.SampleRepository;
import com.asi.sda.sample.service.SampleServiceImpl;
import com.asi.sda.sample.service.SampleService;

import java.util.ArrayList;
import java.util.List;

public class MainSample {
    public static void main(String[] args) {
        SampleRepository sampleHibernateDao = new SampleDao();
        SampleService sampleHibernateService = new SampleServiceImpl(sampleHibernateDao);
        SampleController sampleController = new SampleController(sampleHibernateService);

        Sample sample1 = new Sample("0123456789");
        Sample sample2 = new Sample("9876543210");
        List<Sample> samples = new ArrayList<>();
        samples.add(sample1);
        samples.add(sample2);

        System.out.println("============================================================> REPOSITORY SIMULATOR ");

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

        System.out.println("============================================================> SERVICE SIMULATOR");

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

        System.out.println("============================================================> CONTROLLER SIMULATOR");

        // save all by faker
        System.out.println("Return service data: " + sampleController.saveAllByFaker());
        System.out.println();

        // save all by loader
        System.out.println("Return service data: " + sampleController.saveAllByLoader());
        System.out.println();

        // create by save
        System.out.println(sampleController.save(SampleMapper.toRequestDto(new Sample("controller create"))));
        System.out.println();

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
    }
}
