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
import java.util.stream.Collectors;

import static com.asi.sda.sample.constant.SampleMessages.SAMPLE_NOT_FOUND_ERROR;

@Service
public class SampleSpringService implements SampleService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SampleSpringService.class);

    private final SampleSpringRepository repository;

    @Autowired
    public SampleSpringService(SampleSpringRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<SampleResponseDto> createAll(List<SampleRequestDto> requests) {
        LOGGER.debug("Saving multiple samples: {}", requests);

        List<Sample> samples = SampleMapper.toEntities(requests);

        List<Sample> entities = (List<Sample>) repository.saveAll(samples);

        return SampleMapper.toResponseDtos(entities);
    }

    @Override
    public SampleResponseDto create(SampleRequestDto request) {
        LOGGER.debug("Saving single sample: {}", request);

        Sample sample = SampleMapper.toEntity(request);

        Sample entity = repository.save(sample);

        return SampleMapper.toResponseDto(entity);
    }

    @Override
    public List<SampleResponseDto> findAll() {
        LOGGER.debug("Finding all samples...");

        List<Sample> entities = (List<Sample>) repository.findAll();

        return SampleMapper.toResponseDtos(entities);
    }

    @Override
    public List<SampleResponseDto> findByText(String text) {
        LOGGER.debug("Finding all samples by text: {}", text);

        List<Sample> samples = (List<Sample>) repository.findAll();

        List<Sample> entities = new ArrayList<>();
        for (Sample item : samples) {
            if (item.getText().equals(text)) {
                entities.add(item);
            }
        }

        return SampleMapper.toResponseDtos(entities);
    }

    public List<SampleResponseDto> findByTextLambda(String text) {
        LOGGER.debug("Finding all samples by text: {}", text);

        List<Sample> samples = (List<Sample>) repository.findAll();
        return samples.stream()
            .filter(sample -> sample.getText().equals(text))
            .map(SampleMapper::toResponseDto)
            .collect(Collectors.toList());
    }

    @Override
    public SampleResponseDto find(Integer id) {
        LOGGER.debug("Finding sample by id: {}", id);

        Sample entity = repository.findById(id)
            .orElseThrow(() -> new SampleNotFoundException(SAMPLE_NOT_FOUND_ERROR));

        return SampleMapper.toResponseDto(entity);
    }

    public SampleResponseDto findLambda(Integer id) {
        LOGGER.debug("Finding sample by id: {}", id);

        return repository.findById(id)
                .map(SampleMapper::toResponseDto)
                .orElseThrow(() -> new SampleNotFoundException(SAMPLE_NOT_FOUND_ERROR));
    }

    @Override
    public SampleResponseDto update(Integer id, SampleRequestDto data) {
        LOGGER.debug("Updating sample by id: {} and data: {}", id, data);

        Sample entity = repository.findById(id)
                .orElseThrow(() -> new SampleNotFoundException(SAMPLE_NOT_FOUND_ERROR));

        Sample sample = SampleMapper.toEntity(data);
        entity.setText(sample.getText());
        repository.save(entity);
        return SampleMapper.toResponseDto(entity);
    }

    @Override
    public SampleResponseDto delete(Integer id) {
        LOGGER.debug("Deleting sample by id: {}", id);

        Sample entity = repository.findById(id)
                .orElseThrow(() -> new SampleNotFoundException(SAMPLE_NOT_FOUND_ERROR));

        repository.deleteById(id);

        return SampleMapper.toResponseDto(entity);
    }
}
