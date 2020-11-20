package com.asi.sda.sample.service;

import com.asi.sda.sample.Sample;
import com.asi.sda.sample.SampleMapper;
import com.asi.sda.sample.SampleRequestDto;
import com.asi.sda.sample.SampleResponseDto;
import com.asi.sda.sample.exception.SampleNotFoundException;
import com.asi.sda.sample.repository.SampleRepository;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

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
        return SampleMapper.toResponseDto(sampleRepository.createAll(samples));
    }

    @Override
    public SampleResponseDto create(SampleRequestDto request) {
        System.out.println(SOURCE + "CREATE");

        Sample sample = SampleMapper.toEntity(request);
        Optional<Sample> optional = sampleRepository.create(sample);

        if (optional.isPresent()) {
            return SampleMapper.toResponseDto(optional.get());
        } else {
            System.out.println(SOURCE + "EXCEPTION: Sample not saved!");
            return SampleMapper.toResponseDto(new Sample()); // never ever
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
                .orElseThrow(() -> new RuntimeException("EXCEPTION: Sample not found!"));

        return SampleMapper.toResponseDto(entity);
    }

    // -------------------------------------------- CRUD => UPDATE

    @Override
    public SampleResponseDto update(Integer id, Sample sampleData) {
        System.out.println(SOURCE + "UPDATE");

        Sample entity = sampleRepository.update(id, sampleData)
                .orElseThrow(() -> new RuntimeException("EXCEPTION: Sample not updated!"));

        return SampleMapper.toResponseDto(entity);
    }

    // -------------------------------------------- CRUD => DELETE

    @Override
    public boolean delete(Integer id) {
        System.out.println(SOURCE + "DELETE");

        return sampleRepository.delete(id);
    }
}
