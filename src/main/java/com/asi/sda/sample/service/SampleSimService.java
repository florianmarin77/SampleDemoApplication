package com.asi.sda.sample.service;

import com.asi.sda.sample.Sample;
import com.asi.sda.sample.SampleMapper;
import com.asi.sda.sample.SampleRequestDto;
import com.asi.sda.sample.SampleResponseDto;
import com.asi.sda.sample.exception.SampleNotFoundException;
import com.asi.sda.sample.exception.SampleNotSavedException;
import com.asi.sda.sample.repository.SampleRepository;

import java.util.List;

import static com.asi.sda.sample.constant.SampleMessages.SAMPLE_NOT_FOUND_ERROR;

public class SampleSimService implements SampleService {
    private static final String SOURCE = "SERVICE => "; // logger

    private final SampleRepository sampleRepository;

    public SampleSimService(SampleRepository sampleRepository) {
        this.sampleRepository = sampleRepository;
    }

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

    @Override
    public List<SampleResponseDto> findAll() {
        System.out.println(SOURCE + "READ/all");

        List<Sample> entities = sampleRepository.findAll();

        return SampleMapper.toResponseDto(entities);
    }

    @Override
    public List<SampleResponseDto> findByText(String text) {
        System.out.println(SOURCE + "READ/byText");

        List<Sample> entities = sampleRepository.findByText(text);

        return SampleMapper.toResponseDto(entities);
    }

    @Override
    public SampleResponseDto find(Integer id) {
        System.out.println(SOURCE + "READ");

        Sample entity = sampleRepository.find(id)
                .orElseThrow(() -> new SampleNotFoundException(SAMPLE_NOT_FOUND_ERROR));

        return SampleMapper.toResponseDto(entity);
    }

    @Override
    public SampleResponseDto update(Integer id, Sample data) {
        System.out.println(SOURCE + "UPDATE");

        Sample entity = sampleRepository.update(id, data)
                .orElseThrow(() -> new SampleNotFoundException(SAMPLE_NOT_FOUND_ERROR));

        return SampleMapper.toResponseDto(entity);
    }

    @Override
    public SampleResponseDto delete(Integer id) {
        System.out.println(SOURCE + "DELETE");

        Sample entity = sampleRepository.delete(id)
                .orElseThrow(() -> new SampleNotFoundException(SAMPLE_NOT_FOUND_ERROR));

        return SampleMapper.toResponseDto(entity);
    }
}
