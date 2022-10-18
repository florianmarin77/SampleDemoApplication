package com.lafi.sda.sample.controller;

import com.lafi.sda.sample.model.Sample;
import com.lafi.sda.sample.model.SampleMapper;
import com.lafi.sda.sample.model.SampleRequestDto;
import com.lafi.sda.sample.model.SampleResponseDto;
import com.lafi.sda.sample.faker.SampleSimFaker;
import com.lafi.sda.sample.loader.SampleSimLoader;
import com.lafi.sda.sample.service.SampleService;

import java.util.List;

public class SampleSimController {
    private static final String SOURCE = "CONTROLLER => ";

    private final SampleService service;

    public SampleSimController(SampleService service) {
        this.service = service;
    }

    // POST => "/samples/save/all/fake"
    public List<SampleResponseDto> saveAllByFaker() {
        System.out.println(SOURCE + "POST/samples/save/all/fake");
        List<SampleRequestDto> requests = SampleSimFaker.createDummyList();
        return service.createAll(requests);
    }

    // POST => "/samples/save/all/load"
    public List<SampleResponseDto> saveAllByLoader() {
        System.out.println(SOURCE + "POST/samples/save/all/load");
        List<SampleRequestDto> requests = SampleSimLoader.generateItemList();
        return service.createAll(requests);
    }

    // POST => "samples/save/all"
    public List<SampleResponseDto> saveAll(List<SampleRequestDto> requests) {
        System.out.println(SOURCE + "POST/samples/save/all");
        return service.createAll(requests);
    }

    // POST => "samples/save"
    public SampleResponseDto save(SampleRequestDto request) {
        System.out.println(SOURCE + "POST/samples/save");
        return service.create(request);
    }

    // GET => "samples/id"
    public SampleResponseDto getById(Integer id) {
        System.out.println(SOURCE + "GET/samples/id");
        return service.find(id);
    }

    // GET => "samples/all"
    public List<SampleResponseDto> getAll() {
        System.out.println(SOURCE + "GET/samples/all");
        return service.findAll();
    }

    // GET => "samples/text"
    public List<SampleResponseDto> getByText(String text) {
        System.out.println(SOURCE + "GET/samples/text");
        return service.findByText(text);
    }

    // PUT => "samples/id"
    public SampleResponseDto updateById(Integer id, Sample data) {
        System.out.println(SOURCE + "PUT/samples/id");
        return service.update(id, SampleMapper.toRequestDto(data));
    }

    // DELETE => "samples/id"
    public SampleResponseDto deleteById(Integer id) {
        System.out.println(SOURCE + "DELETE/samples/id");
        return service.delete(id);
    }
}
