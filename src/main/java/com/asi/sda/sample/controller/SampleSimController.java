package com.asi.sda.sample.controller;

import com.asi.sda.sample.Sample;
import com.asi.sda.sample.SampleRequestDto;
import com.asi.sda.sample.SampleResponseDto;
import com.asi.sda.sample.faker.SampleSimFaker;
import com.asi.sda.sample.loader.SampleSimLoader;
import com.asi.sda.sample.service.SampleService;

import java.util.List;

public class SampleSimController {
    private static final String SOURCE = "CONTROLLER => ";

    private final SampleService sampleService;

    public SampleSimController(SampleService sampleService) {
        this.sampleService = sampleService;
    }

    // -------------------------------------------- CRUD => CREATE

    // POST => "/samples/save/all/fake"
    public List<SampleResponseDto> saveAllByFaker() {
        System.out.println(SOURCE + "POST/samples/save/all/fake");
        List<SampleRequestDto> requests = SampleSimFaker.createDummyList();
        return sampleService.createAll(requests);
    }

    // POST => "/samples/save/all/load"
    public List<SampleResponseDto> saveAllByLoader() {
        System.out.println(SOURCE + "POST/samples/save/all/load");
        List<SampleRequestDto> requests = SampleSimLoader.generateItemList();
        return sampleService.createAll(requests);
    }

    // POST => "samples/save/all"
    public List<SampleResponseDto> saveAll(List<SampleRequestDto> requests) {
        System.out.println(SOURCE + "POST/samples/save/all");
        return sampleService.createAll(requests);
    }

    // POST => "samples/save"
    public SampleResponseDto save(SampleRequestDto request) {
        System.out.println(SOURCE + "POST/samples/save");
        return sampleService.create(request);
    }

    // -------------------------------------------- CRUD => READ

    // GET => "samples/id"
    public SampleResponseDto getById(Integer id) {
        System.out.println(SOURCE + "GET/samples/id");
        return sampleService.find(id);
    }

    // GET => "samples/all"
    public List<SampleResponseDto> getAll() {
        System.out.println(SOURCE + "GET/samples/all");
        return sampleService.findAll();
    }

    // GET => "samples/text"
    public List<SampleResponseDto> getByText(String text) {
        System.out.println(SOURCE + "GET/samples/text");
        return sampleService.findByText(text);
    }

    // -------------------------------------------- CRUD => UPDATE

    // PUT => "samples/id"
    public void updateById(Integer id, Sample sampleData) {
        System.out.println(SOURCE + "PUT/samples/id");
        sampleService.update(id, sampleData);
    }

    // -------------------------------------------- CRUD => DELETE

    // DELETE => "samples/id"
    public void deleteById(Integer id) {
        System.out.println(SOURCE + "DELETE/samples/id");
        sampleService.delete(id);
    }
}
