package com.asi.sda.sample.main;

import com.asi.sda.sample.Sample;
import com.asi.sda.sample.SampleMapper;
import com.asi.sda.sample.database.SampleSimDatabase;
import com.asi.sda.sample.repository.SampleSimDao;
import com.asi.sda.sample.repository.SampleRepository;
import com.asi.sda.sample.service.SampleService;
import com.asi.sda.sample.service.SampleSimService;

import java.util.ArrayList;
import java.util.List;

public class MainSimService {
    private static final SampleSimDatabase database = SampleSimDatabase.getInstance();

    public static void main(String[] args) {
        SampleRepository dao = new SampleSimDao();
        SampleService service = new SampleSimService(dao);

        Sample sample1 = new Sample("0123456789");
        Sample sample2 = new Sample("9876543210");
        List<Sample> samples = new ArrayList<>();
        samples.add(sample1);
        samples.add(sample2);

        System.out.println("<=== SERVICE SIMULATOR ===>");
        System.out.println();

        // create
        System.out.println(service.create(SampleMapper.toRequestDto(new Sample("service"))));
        System.out.println();
        SampleSimDatabase.displayDataTable(database.getDatabase());

        System.out.println("Samples created: " + service.createAll(SampleMapper.toRequestDto(samples)));
        System.out.println();
        SampleSimDatabase.displayDataTable(database.getDatabase());

        // read
        System.out.println(service.find(1));
        System.out.println();
        System.out.println(service.findAll());
        System.out.println();
        System.out.println(service.findByText("service"));
        System.out.println();
        SampleSimDatabase.displayDataTable(database.getDatabase());

        // update
        System.out.println(service.update(2, new Sample("macarena")));
        System.out.println();
        SampleSimDatabase.displayDataTable(database.getDatabase());

        // delete
        System.out.println("Sample deleted: " + service.delete(3));
        System.out.println();
        SampleSimDatabase.displayDataTable(database.getDatabase());
    }
}