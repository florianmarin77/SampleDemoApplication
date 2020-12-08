package com.asi.sda.sample.main;

import com.asi.sda.sample.Sample;
import com.asi.sda.sample.SampleMapper;
import com.asi.sda.sample.controller.SampleSimController;
import com.asi.sda.sample.database.SampleSimDatabase;
import com.asi.sda.sample.repository.SampleRepository;
import com.asi.sda.sample.repository.SampleSimDao;
import com.asi.sda.sample.service.SampleService;
import com.asi.sda.sample.service.SampleSimService;

import java.util.ArrayList;
import java.util.List;

public class MainSimController {
    private static final SampleSimDatabase database = SampleSimDatabase.getInstance();

    public static void main(String[] args) {
        SampleRepository dao = new SampleSimDao();
        SampleService service = new SampleSimService(dao);
        SampleSimController controller = new SampleSimController(service);

        Sample sample1 = new Sample("0123456789");
        Sample sample2 = new Sample("9876543210");
        List<Sample> samples = new ArrayList<>();
        samples.add(sample1);
        samples.add(sample2);

        System.out.println("<=== CONTROLLER SIMULATOR ===>");
        System.out.println();

        // save all by faker
        System.out.println(controller.saveAllByFaker());
        System.out.println();
        database.displayTable(database.getSampleList());

        // save all by loader
        System.out.println(controller.saveAllByLoader());
        System.out.println();
        database.displayTable(database.getSampleList());

        // save
        System.out.println(controller.save(SampleMapper.toRequestDto(new Sample("controller"))));
        System.out.println();
        database.displayTable(database.getSampleList());

        // save all
        System.out.println(controller.saveAll(SampleMapper.toRequestDto(samples)));
        System.out.println();
        database.displayTable(database.getSampleList());

        // find by id
        System.out.println(controller.getById(53));
        System.out.println();
        // find all
        System.out.println(controller.getAll());
        System.out.println();
        // find by text
        System.out.println(controller.getByText("controller"));
        System.out.println();
        database.displayTable(database.getSampleList());

        // update by id
        System.out.println(controller.updateById(54, new Sample("macarena")));
        System.out.println();
        database.displayTable(database.getSampleList());

        // delete by id
        System.out.println(controller.deleteById(55));
        System.out.println();
        database.displayTable(database.getSampleList());
    }
}
