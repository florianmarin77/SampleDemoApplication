package com.asi.sda.sample.main;

import com.asi.sda.sample.Sample;
import com.asi.sda.sample.SampleMapper;
import com.asi.sda.sample.controller.SampleController;
import com.asi.sda.sample.database.SampleDatabase;
import com.asi.sda.sample.repository.SampleDao;
import com.asi.sda.sample.repository.SampleRepository;
import com.asi.sda.sample.service.SampleService;
import com.asi.sda.sample.service.SampleServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class MainController {
    private static final SampleDatabase database = SampleDatabase.getInstance();

    public static void main(String[] args) {
        SampleRepository dao = new SampleDao();
        SampleService service = new SampleServiceImpl(dao);
        SampleController controller = new SampleController(service);

        Sample sample1 = new Sample("0123456789");
        Sample sample2 = new Sample("9876543210");
        List<Sample> samples = new ArrayList<>();
        samples.add(sample1);
        samples.add(sample2);

        System.out.println("<=== CONTROLLER SIMULATOR ===>");
        System.out.println();

        // save all by faker
        System.out.println("Return data: " + controller.saveAllByFaker());
        System.out.println();
        SampleDatabase.displayDataTable(database.getDatabase());

        // save all by loader
        System.out.println("Return data: " + controller.saveAllByLoader());
        System.out.println();
        SampleDatabase.displayDataTable(database.getDatabase());

        // save
        System.out.println(controller.save(SampleMapper.toRequestDto(new Sample("controller"))));
        System.out.println();
        SampleDatabase.displayDataTable(database.getDatabase());

        // save all
        System.out.println("Samples created: " + controller.saveAll(SampleMapper.toRequestDto(samples)));
        System.out.println();
        SampleDatabase.displayDataTable(database.getDatabase());

        // find by id
        System.out.println(controller.getById(53));
        System.out.println();
        // find all
        System.out.println(controller.getAll());
        System.out.println();
        // find by text
        System.out.println(controller.getByText("controller"));
        System.out.println();
        SampleDatabase.displayDataTable(database.getDatabase());

        // update by id
        System.out.println(controller.updateById(54, new Sample("macarena")));
        System.out.println();
        SampleDatabase.displayDataTable(database.getDatabase());

        // delete by id
        System.out.println("Sample deleted: " + controller.deleteById(55));
        System.out.println();
        SampleDatabase.displayDataTable(database.getDatabase());
    }
}
