package com.lafi.sda.sample.controller;

import com.lafi.sda.sample.faker.SampleSimFaker;
import com.lafi.sda.sample.loader.SampleSimLoader;
import com.lafi.sda.sample.model.SampleRequestDto;
import com.lafi.sda.sample.model.SampleResponseDto;
import com.lafi.sda.sample.service.SampleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/samples")
public class SampleSpringRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SampleSpringRestController.class);

    private final SampleService sampleService;

    @Autowired
    public SampleSpringRestController(SampleService sampleService) {
        this.sampleService = sampleService;
    }

    @PostMapping("/load")
    public ResponseEntity<List<SampleResponseDto>> loadAll() {
        LOGGER.info("Create samples by loader...");

        return new ResponseEntity<>(sampleService.createAll(SampleSimLoader.generateItemList()), HttpStatus.OK);
    }

    @PostMapping("/fake")
    public ResponseEntity<List<SampleResponseDto>> fakeAll() {
        LOGGER.info("Create samples by faker...");

        return new ResponseEntity<>(sampleService.createAll(SampleSimFaker.createDummyList()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SampleResponseDto> saveOne(@RequestBody SampleRequestDto request) {
        LOGGER.info("Create single sample: {}", request);

        return new ResponseEntity<>(sampleService.create(request), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<SampleResponseDto>> findAll() {
        LOGGER.info("Get all samples...");

        return new ResponseEntity<>(sampleService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SampleResponseDto> findById(@PathVariable Integer id) {
        LOGGER.info("Get sample by id: {}", id);

        return new ResponseEntity<>(sampleService.find(id), HttpStatus.OK);
    }

    @GetMapping("/search/{text}")
    public ResponseEntity<List<SampleResponseDto>> findByText(@PathVariable String text) {
        LOGGER.info("Get all samples by text: {}", text);

        return new ResponseEntity<>(sampleService.findByText(text), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SampleResponseDto> updateById(@PathVariable Integer id, @RequestBody SampleRequestDto request) {
        LOGGER.info("Update sample with id: {} and data: {}", id, request);

        return new ResponseEntity<>(sampleService.update(id, request), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SampleResponseDto> deleteById(@PathVariable Integer id) {
        LOGGER.info("Delete sample with id: {}", id);

        return new ResponseEntity<>(sampleService.delete(id), HttpStatus.OK);
    }
}
