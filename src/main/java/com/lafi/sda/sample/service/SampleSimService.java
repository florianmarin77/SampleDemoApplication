package com.lafi.sda.sample.service;

import com.lafi.sda.sample.model.Sample;
import com.lafi.sda.sample.model.SampleMapper;
import com.lafi.sda.sample.model.SampleRequestDto;
import com.lafi.sda.sample.model.SampleResponseDto;
import com.lafi.sda.sample.exception.SampleNotFoundException;
import com.lafi.sda.sample.exception.SampleNotSavedException;
import com.lafi.sda.sample.repository.SampleRepository;
import com.lafi.sda.sample.constant.SampleMessages;

import java.util.List;

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
                .orElseThrow(() -> new SampleNotFoundException(SampleMessages.SAMPLE_NOT_FOUND_ERROR));

        return SampleMapper.toResponseDto(entity);
    }

    @Override
    public SampleResponseDto update(Integer id, SampleRequestDto data) {
        System.out.println(SOURCE + "UPDATE");

        Sample entity = sampleRepository.update(id, SampleMapper.toEntity(data))
                .orElseThrow(() -> new SampleNotFoundException(SampleMessages.SAMPLE_NOT_FOUND_ERROR));

        return SampleMapper.toResponseDto(entity);
    }

    @Override
    public SampleResponseDto delete(Integer id) {
        System.out.println(SOURCE + "DELETE");

        Sample entity = sampleRepository.delete(id)
                .orElseThrow(() -> new SampleNotFoundException(SampleMessages.SAMPLE_NOT_FOUND_ERROR));

        return SampleMapper.toResponseDto(entity);
    }
}
