package com.asi.demo.sample.controller;

import com.asi.demo.sample.Sample;
import com.asi.demo.sample.SampleRequestDto;
import com.asi.demo.sample.SampleResponseDto;
import com.asi.demo.sample.faker.SampleFaker;
import com.asi.demo.sample.loader.SampleLoader;
import com.asi.demo.sample.service.SampleService;

import java.util.List;

public class SampleController {
    private static final String SOURCE = "CONTROLLER => ";

    private final SampleService sampleService;

    public SampleController(SampleService sampleService) {
        this.sampleService = sampleService;
    }

    // -------------------------------------------- CRUD => CREATE

    // POST => "/samples/fake/save/all"
    public List<SampleResponseDto> saveAllByFaker() {
        System.out.println(SOURCE + "POST/samples/fake/save/all");
        List<SampleRequestDto> requests = SampleFaker.createDummyList();
        return sampleService.createAll(requests);
    }

    // POST => "/samples/load/save/all"
    public List<SampleResponseDto> saveAllByLoader() {
        System.out.println(SOURCE + "POST/samples/load/save/all");
        List<SampleRequestDto> requests = SampleLoader.generateItemList();
        return sampleService.createAll(requests);
    }

    // POST => "/samples/fake/save/one"
    public List<SampleResponseDto> saveOneByFaker() {
        System.out.println(SOURCE + "POST/samples/fake/save/one");
        List<SampleRequestDto> requests = SampleFaker.createDummy();
        return sampleService.createAll(requests);
    }

    // POST => "/samples/load/save/one"
    public List<SampleResponseDto> saveOneByLoader() {
        System.out.println(SOURCE + "POST/samples/load/save/one");
        List<SampleRequestDto> requests = SampleLoader.generateItem();
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
    public SampleResponseDto updateById(Integer id, Sample sampleData) {
        System.out.println(SOURCE + "PUT/samples/id");
        return sampleService.update(id, sampleData);
    }

    // -------------------------------------------- CRUD => DELETE

    // DELETE => "samples/id"
    public boolean deleteById(Integer id) {
        System.out.println(SOURCE + "DELETE/samples/id");
        return sampleService.delete(id);
    }

    // DELETE => "samples/all"
    public boolean deleteAll() {
        System.out.println(SOURCE + "DELETE/samples/all");
        return sampleService.deleteAll();
    }
}
