package com.asi.sda.sample.controller;

import com.asi.sda.sample.faker.SampleSimFaker;
import com.asi.sda.sample.loader.SampleSimLoader;
import com.asi.sda.sample.model.SampleRequestDto;
import com.asi.sda.sample.model.SampleResponseDto;
import com.asi.sda.sample.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/samples")
public class SampleSpringRestController {

    private final SampleService sampleService;

    @Autowired
    public SampleSpringRestController(SampleService sampleService) {
        this.sampleService = sampleService;
    }

    @PostMapping("/load")
    public ResponseEntity<List<SampleResponseDto>> loadAll() {
        return new ResponseEntity<>(sampleService.createAll(SampleSimLoader.generateItemList()), HttpStatus.OK);
    }

    @PostMapping("/fake")
    public ResponseEntity<List<SampleResponseDto>> fakeAll() {
        return new ResponseEntity<>(sampleService.createAll(SampleSimFaker.createDummyList()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SampleResponseDto> saveOne(@RequestBody SampleRequestDto request) {
        return new ResponseEntity<>(sampleService.create(request), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<SampleResponseDto>> findAll() {
        return new ResponseEntity<>(sampleService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SampleResponseDto> findById(@PathVariable Integer id) {
        return new ResponseEntity<>(sampleService.find(id), HttpStatus.OK);
    }

    @GetMapping("/text/{text}")
    public ResponseEntity<List<SampleResponseDto>> findByText(@PathVariable String text) {
        return new ResponseEntity<>(sampleService.findByText(text), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SampleResponseDto> updateById(@PathVariable Integer id, @RequestBody SampleRequestDto request) {
        return new ResponseEntity<>(sampleService.update(id, request), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SampleResponseDto> deleteById(@PathVariable Integer id) {
        return new ResponseEntity<>(sampleService.delete(id), HttpStatus.OK);
    }
}
