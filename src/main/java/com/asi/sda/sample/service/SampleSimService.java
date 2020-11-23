package com.asi.sda.sample.service;

import com.asi.sda.sample.Sample;
import com.asi.sda.sample.SampleMapper;
import com.asi.sda.sample.SampleRequestDto;
import com.asi.sda.sample.SampleResponseDto;
import com.asi.sda.sample.exception.SampleNotFoundException;
import com.asi.sda.sample.exception.SampleNotSavedException;
import com.asi.sda.sample.repository.SampleRepository;

import java.util.List;

public class SampleSimService implements SampleService {
    private static final String SOURCE = "SERVICE => ";

    private final SampleRepository sampleRepository;

    public SampleSimService(SampleRepository sampleRepository) {
        this.sampleRepository = sampleRepository;
    }

    // -------------------------------------------- CRUD => CREATE

    @Override
    public List<SampleResponseDto> createAll(List<SampleRequestDto> requests) {
        System.out.println(SOURCE + "CREATE/all");

        List<Sample> samples = SampleMapper.toEntity(requests);
        List<Sample> entities = sampleRepository.createAll(samples);

        if (entities.isEmpty()) {
            throw new SampleNotSavedException("EXCEPTION: Samples not saved!");
        } else {
            return SampleMapper.toResponseDto(entities);
        }
    }

    @Override
    public SampleResponseDto create(SampleRequestDto request) {
        System.out.println(SOURCE + "CREATE");

        Sample sample = SampleMapper.toEntity(request);
        Sample entity = sampleRepository.create(sample);

        if (entity == null) {
            throw new SampleNotSavedException("EXCEPTION: Sample not saved!");
        } else {
            return SampleMapper.toResponseDto(entity);
        }
    }

    // -------------------------------------------- CRUD => READ

    @Override
    public List<SampleResponseDto> findAll() {
        System.out.println(SOURCE + "READ/all");

        return SampleMapper.toResponseDto(sampleRepository.findAll());
    }

    @Override
    public List<SampleResponseDto> findByText(String text) {
        System.out.println(SOURCE + "READ/byText");

        return SampleMapper.toResponseDto(sampleRepository.findByText(text));
    }

    @Override
    public SampleResponseDto find(Integer id) {
        System.out.println(SOURCE + "READ");

        Sample entity = sampleRepository.find(id)
                .orElseThrow(() -> new SampleNotFoundException("EXCEPTION: Sample not found!"));

        return SampleMapper.toResponseDto(entity);
    }

    // -------------------------------------------- CRUD => UPDATE

    @Override
    public void update(Integer id, Sample sampleData) {
        System.out.println(SOURCE + "UPDATE");

        sampleRepository.update(id, sampleData);
    }

    // -------------------------------------------- CRUD => DELETE

    @Override
    public void delete(Integer id) {
        System.out.println(SOURCE + "DELETE");

        sampleRepository.delete(id);
    }
}
