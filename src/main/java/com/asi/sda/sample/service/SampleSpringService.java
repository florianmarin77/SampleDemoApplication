package com.asi.sda.sample.service;

import com.asi.sda.sample.exception.SampleNotFoundException;
import com.asi.sda.sample.model.Sample;
import com.asi.sda.sample.model.SampleMapper;
import com.asi.sda.sample.model.SampleRequestDto;
import com.asi.sda.sample.model.SampleResponseDto;
import com.asi.sda.sample.repository.SampleSpringRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.asi.sda.sample.constant.SampleMessages.SAMPLE_NOT_FOUND_ERROR;

@Service
public class SampleSpringService implements SampleService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SampleSpringService.class);

    private final SampleSpringRepository springRepository;

    @Autowired
    public SampleSpringService(SampleSpringRepository springRepository) {
        this.springRepository = springRepository;
    }

    @Override
    public List<SampleResponseDto> createAll(List<SampleRequestDto> requests) {
        LOGGER.debug("Saving multiple samples: {}", requests);

        List<Sample> samples = SampleMapper.toEntities(requests);

        List<Sample> entities = (List<Sample>) springRepository.saveAll(samples);

        return SampleMapper.toResponseDtos(entities);
    }

    @Override
    public SampleResponseDto create(SampleRequestDto request) {
        LOGGER.debug("Saving single sample: {}", request);

        Sample sample = SampleMapper.toEntity(request);

        Sample entity = springRepository.save(sample);

        return SampleMapper.toResponseDto(entity);
    }

    @Override
    public List<SampleResponseDto> findAll() {
        LOGGER.debug("Finding all samples...");

        List<Sample> entities = (List<Sample>) springRepository.findAll();

        return SampleMapper.toResponseDtos(entities);
    }

    @Override
    public List<SampleResponseDto> findByText(String text) {
        LOGGER.debug("Finding samples by text: {}", text);

        List<Sample> samples = (List<Sample>) springRepository.findAll();

        List<Sample> entities = new ArrayList<>();
        for (Sample item : samples) {
            if (item.getText().equals(text)) {
                entities.add(item);
            }
        }

        return SampleMapper.toResponseDtos(entities);
    }

    @Override
    public SampleResponseDto find(Integer id) {
        LOGGER.debug("Finding sample by id: {}", id);

        Sample entity = springRepository.findById(id)
                .orElseThrow(() -> new SampleNotFoundException(SAMPLE_NOT_FOUND_ERROR));

        return SampleMapper.toResponseDto(entity);
    }

    @Override
    public SampleResponseDto update(Integer id, SampleRequestDto data) {
        LOGGER.debug("Updating sample with id: {} and body: {}", id, data);

        Sample entity = springRepository.findById(id)
                .orElseThrow(() -> new SampleNotFoundException(SAMPLE_NOT_FOUND_ERROR));

        Sample sample = SampleMapper.toEntity(data);
        entity.setText(sample.getText());
        springRepository.save(entity);
        return SampleMapper.toResponseDto(entity);
    }

    @Override
    public SampleResponseDto delete(Integer id) {
        LOGGER.debug("Deleting sample with id: {}", id);

        Sample entity = springRepository.findById(id)
                .orElseThrow(() -> new SampleNotFoundException(SAMPLE_NOT_FOUND_ERROR));

        springRepository.deleteById(id);

        return SampleMapper.toResponseDto(entity);
    }
}
