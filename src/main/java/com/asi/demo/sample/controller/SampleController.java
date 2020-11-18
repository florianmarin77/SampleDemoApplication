package com.asi.demo.sample.controller;

import com.asi.demo.sample.Sample;
import com.asi.demo.sample.SampleMapper;
import com.asi.demo.sample.SampleRequestDto;
import com.asi.demo.sample.SampleResponseDto;
import com.asi.demo.sample.database.SampleDatabase;
import com.asi.demo.sample.faker.SampleFaker;
import com.asi.demo.sample.loader.SampleLoader;
import com.asi.demo.sample.service.SampleService;

import java.util.List;

public class SampleController {

    private final SampleService sampleService;

    public SampleController(SampleService sampleService) {
        this.sampleService = sampleService;
    }

    // -------------------------------------------- CRUD => CREATE

    // POST => "samples/create/save/item=data"
    public SampleResponseDto saveItem(SampleRequestDto requestDto) {
        return sampleService.create(requestDto);
    }

    // GET + POST => "/samples/populate/fake/create/all"
    public boolean populateByFaker() {
        SampleFaker sampleFaker = new SampleFaker();
        SampleMapper sampleMapper = new SampleMapper();

        List<Sample> samples = sampleFaker.generateList(); // list by data faker

        SampleDatabase.displayDataTable(samples); // convert and display table by database

        return sampleService.createAll(sampleMapper.toRequestDto(samples));
    }

    // GET + POST => "/samples/populate/load/create/all"
    public boolean populateByLoader() {
        SampleLoader sampleLoader = new SampleLoader();
        SampleMapper sampleMapper = new SampleMapper();

        List<Sample> samples = sampleLoader.generateList(); // list by data loader

        SampleDatabase.displayDataTable(samples); // convert and display table by database

        return sampleService.createAll(sampleMapper.toRequestDto(samples));
    }

    // -------------------------------------------- CRUD => READ

    // GET => "samples/find/id=item"
    public SampleResponseDto getById(Integer id) {
        return sampleService.find(id);
    }

    // GET => "samples/find/all=list"
    public List<SampleResponseDto> getAll() {
        return sampleService.findAll();
    }

    // GET => "samples/find/text=list"
    public List<SampleResponseDto> getByText(String text) {
        return sampleService.findByText(text);
    }

    // -------------------------------------------- CRUD => UPDATE

    // PUT => "samples/update/item=id+data"
    public SampleResponseDto updateById(Integer id, Sample sampleData) {
        return sampleService.update(id, sampleData);
    }

    // -------------------------------------------- CRUD => DELETE

    // DELETE => "samples/delete/item=id"
    public boolean deleteById(Integer id) {
        return sampleService.delete(id);
    }

    // DELETE => "samples/delete/all"
    public boolean deleteAll() {
        return sampleService.deleteAll();
    }
}
