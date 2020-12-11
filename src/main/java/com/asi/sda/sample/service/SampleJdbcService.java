package com.asi.sda.sample.service;

import com.asi.sda.sample.Sample;
import com.asi.sda.sample.SampleMapper;
import com.asi.sda.sample.SampleRequestDto;
import com.asi.sda.sample.SampleResponseDto;
import com.asi.sda.sample.exception.OutOfRangeException;
import com.asi.sda.sample.exception.SampleNotFoundException;
import com.asi.sda.sample.exception.SampleNotSavedException;
import com.asi.sda.sample.repository.SampleJdbcDao;
import com.asi.sda.sample.repository.SampleJpaDao;
import com.asi.sda.sample.repository.SampleRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

import static com.asi.sda.sample.constant.CommonMessages.OUT_OF_RANGE_ERROR;
import static com.asi.sda.sample.constant.SampleMessages.*;

public class SampleJdbcService implements SampleService {
    private static final Logger LOGGER = LogManager.getLogger(SampleJdbcService.class);
    private static final String SOURCE = "SERVICE => "; // level signature

    private final SampleRepository sampleRepository;

    public SampleJdbcService(SampleRepository sampleRepository) {
        this.sampleRepository = sampleRepository;
    }

    @Override
    public List<SampleResponseDto> createAll(List<SampleRequestDto> requests) {
        System.out.println(SOURCE + "CREATE/all");

        List<Sample> entities = sampleRepository.createAll(SampleMapper.toEntities(requests));

        if (entities.get(0).getId() == null) {
            throw new SampleNotSavedException(SAMPLES_NOT_SAVED_ERROR);
        } else {
            return SampleMapper.toResponseDtos(entities);
        }
    }

    @Override
    public SampleResponseDto create(SampleRequestDto request) {
        System.out.println(SOURCE + "CREATE");

        Sample entity = sampleRepository.create(SampleMapper.toEntity(request));

        if (entity.getId() == null) {
            throw new SampleNotSavedException(SAMPLE_NOT_SAVED_ERROR);
        } else {
            return SampleMapper.toResponseDto(entity);
        }
    }

    @Override
    public List<SampleResponseDto> findAll() {
        System.out.println(SOURCE + "READ/all");

        List<Sample> entities = sampleRepository.findAll();

        return SampleMapper.toResponseDtos(entities);
    }

    @Override
    public List<SampleResponseDto> findByText(String text) {
        System.out.println(SOURCE + "READ/byText");

        List<Sample> entities = sampleRepository.findByText(text);

        return SampleMapper.toResponseDtos(entities);
    }

    @Override
    public SampleResponseDto find(Integer id) {
        System.out.println(SOURCE + "READ");

        if (hasValidId(id)) {
            Optional<Sample> optional = sampleRepository.find(id);

            Sample entity;
            if (optional.isPresent()) {
                entity = optional.get();
            } else {
                throw new SampleNotFoundException(SAMPLE_NOT_FOUND_ERROR);
            }
            return SampleMapper.toResponseDto(entity);
        } else {
            throw new OutOfRangeException(OUT_OF_RANGE_ERROR);
        }
    }

    @Override
    public SampleResponseDto update(Integer id, Sample data) {
        System.out.println(SOURCE + "UPDATE");

        Sample result;
        if (hasValidId(id)) {
            Optional<Sample> optional = sampleRepository.find(id);

            if (optional.isPresent()) {
                sampleRepository.update(id, data);
                result = optional.get();
                result.setText(data.getText());
            } else {
                throw new SampleNotFoundException(SAMPLE_NOT_FOUND_ERROR);
            }
        } else {
            throw new OutOfRangeException(OUT_OF_RANGE_ERROR);
        }
        return SampleMapper.toResponseDto(result);
    }

    @Override
    public SampleResponseDto delete(Integer id) {
        System.out.println(SOURCE + "DELETE");

        Sample result;
        if (hasValidId(id)) {
            Optional<Sample> optional = sampleRepository.find(id);

            if (optional.isPresent()) {
                result = optional.get();
                sampleRepository.delete(id);
            } else {
                throw new SampleNotFoundException(SAMPLE_NOT_FOUND_ERROR);
            }
        } else {
            throw new OutOfRangeException(OUT_OF_RANGE_ERROR);
        }
        return SampleMapper.toResponseDto(result);
    }

    private boolean hasValidId(Integer id) {
        boolean validId = false;

        if (id < 1) {
            LOGGER.error("INVALID ID NUMBER!"); // no negative values allowed
            return false;
        }
        if (id == Integer.MAX_VALUE) {
            LOGGER.warn("ID NUMBER TOO BIG!"); // maximum integer value 2,147,483,647
            return false;
        }
        if (id > SampleJdbcDao.lastInsertId) {
            LOGGER.warn(SAMPLE_OUT_OF_RANGE, id, SampleJdbcDao.lastInsertId);
        } else {
            validId = true;
        }

        return validId;
    }
}
