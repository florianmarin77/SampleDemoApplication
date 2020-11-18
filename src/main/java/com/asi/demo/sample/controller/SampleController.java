package com.asi.demo.sample.controller;

import com.asi.demo.sample.Sample;
import com.asi.demo.sample.SampleRequestDto;
import com.asi.demo.sample.SampleResponseDto;
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

    // POST => "/samples/fake/save/all"
    public List<SampleResponseDto> saveAllByFaker() {
        List<SampleRequestDto> requests = SampleFaker.createDummyList();
        return sampleService.createAll(requests);
    }

    // POST => "/samples/load/save/all"
    public List<SampleResponseDto> saveAllByLoader() {
        List<SampleRequestDto> requests = SampleLoader.generateItemList();
        return sampleService.createAll(requests);
    }

    // POST => "/samples/fake/save/one"
    public SampleResponseDto saveOneByFaker() {
        SampleRequestDto request = SampleFaker.createDummy();
        return sampleService.create(request);
    }

    // POST => "/samples/load/save/one"
    public SampleResponseDto saveOneByLoader() {
        SampleRequestDto request = SampleLoader.generateItem();
        return sampleService.create(request);
    }

    // POST => "samples/save"
    public SampleResponseDto save(SampleRequestDto request) {
        return sampleService.create(request);
    }

    // -------------------------------------------- CRUD => READ

    // GET => "samples/id"
    public SampleResponseDto getById(Integer id) {
        return sampleService.find(id);
    }

    // GET => "samples/all"
    public List<SampleResponseDto> getAll() {
        return sampleService.findAll();
    }

    // GET => "samples/text"
    public List<SampleResponseDto> getByText(String text) {
        return sampleService.findByText(text);
    }

    // -------------------------------------------- CRUD => UPDATE

    // PUT => "samples/id"
    public SampleResponseDto updateById(Integer id, Sample sampleData) {
        return sampleService.update(id, sampleData);
    }

    // -------------------------------------------- CRUD => DELETE

    // DELETE => "samples/id"
    public boolean deleteById(Integer id) {
        return sampleService.delete(id);
    }

    // DELETE => "samples/all"
    public boolean deleteAll() {
        return sampleService.deleteAll();
    }
}
