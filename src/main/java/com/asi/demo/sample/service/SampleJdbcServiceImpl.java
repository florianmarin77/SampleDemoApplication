package com.asi.demo.sample.service;

import com.asi.demo.sample.Sample;
import com.asi.demo.sample.SampleMapper;
import com.asi.demo.sample.SampleRequestDto;
import com.asi.demo.sample.SampleResponseDto;
import com.asi.demo.sample.repository.SampleRepository;

import java.util.List;
import java.util.Optional;

public class SampleJdbcServiceImpl implements SampleService {
    private static final String SOURCE = "JDBC-SERVICE => ";

    private final SampleRepository sampleRepository;
    
    // =================> constructor

    public SampleJdbcServiceImpl(SampleRepository sampleRepository) {
        this.sampleRepository = sampleRepository;
    }

    // -------------------------------------------- CRUD => CREATE

    @Override
    public boolean createAll(List<SampleRequestDto> requestDtos) {
        System.out.println(SOURCE + "CREATE/all");

        List<Sample> samples = SampleMapper.toEntity(requestDtos);

        return sampleRepository.createAll(samples);
    }

    @Override
    public SampleResponseDto create(SampleRequestDto requestDto) {
        System.out.println(SOURCE + "CREATE");

        Sample sample = SampleMapper.toEntity(requestDto);

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

        Optional<Sample> optional = sampleRepository.find(id);

        if (optional.isPresent()) {
            return SampleMapper.toResponseDto(optional.get());
        } else {
            System.out.println(SOURCE + "EXCEPTION: Sample not found!");
            return SampleMapper.toResponseDto(new Sample()); // never ever
        }
    }

    // -------------------------------------------- CRUD => UPDATE

    @Override
    public SampleResponseDto update(Integer id, Sample sampleData) {
        System.out.println(SOURCE + "UPDATE");

        Optional<Sample> optional = sampleRepository.update(id, sampleData);

        if (optional.isPresent()) {
            return SampleMapper.toResponseDto(optional.get());
        } else {
            System.out.println(SOURCE + "EXCEPTION: Sample not updated!");
            return SampleMapper.toResponseDto(new Sample()); // never ever
        }
    }

    // -------------------------------------------- CRUD => DELETE

    @Override
    public boolean delete(Integer id) {
        System.out.println(SOURCE + "DELETE");

        return sampleRepository.delete(id);
    }

    @Override
    public boolean deleteAll() {
        System.out.println(SOURCE + "DELETE/all");

        return sampleRepository.deleteAll();
    }
}
