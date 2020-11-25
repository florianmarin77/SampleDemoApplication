package com.asi.sda.sample.service;

import com.asi.sda.sample.Sample;
import com.asi.sda.sample.SampleMapper;
import com.asi.sda.sample.SampleRequestDto;
import com.asi.sda.sample.SampleResponseDto;
import com.asi.sda.sample.exception.*;
import com.asi.sda.sample.repository.SampleJpaDao;
import com.asi.sda.sample.repository.SampleRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

import static com.asi.sda.sample.constant.SampleMessages.*;
import static com.asi.sda.sample.constant.CommonMessages.OUT_OF_RANGE_ERROR;

public class SampleJpaService implements SampleService {

    private static final Logger LOGGER = LogManager.getLogger(SampleJpaService.class);


    private SampleRepository sampleRepository;

    public SampleJpaService(SampleRepository sampleRepository) {
        this.sampleRepository = sampleRepository;
    }

    // -------------------------------------------- CRUD => CREATE

    @Override
    public List<SampleResponseDto> createAll(List<SampleRequestDto> requests) {
        List<Sample> entities = sampleRepository.createAll(SampleMapper.toEntities(requests));

        if (entities.get(0).getId() == null) {
            throw new SampleNotSavedException(SAMPLES_NOT_SAVED_ERROR);
        } else {
            return SampleMapper.toResponseDtos(entities);
        }
    }

    @Override
    public SampleResponseDto create(SampleRequestDto request) {
        Sample entity = sampleRepository.create(SampleMapper.toEntity(request));

        if (entity.getId() == null) {
            throw new SampleNotSavedException(SAMPLE_NOT_SAVED_ERROR);
        } else {
            return SampleMapper.toResponseDto(entity);
        }
    }

    // -------------------------------------------- CRUD => READ

    @Override
    public List<SampleResponseDto> findAll() {
        List<Sample> entities = sampleRepository.findAll();

        return SampleMapper.toResponseDtos(entities);
    }

    @Override
    public List<SampleResponseDto> findByText(String text) {
        List<Sample> entities = sampleRepository.findByText(text);

        return SampleMapper.toResponseDtos(entities);
    }

    @Override
    public SampleResponseDto find(Integer id) {
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

    // -------------------------------------------- CRUD => UPDATE

    @Override
    public void update(Integer id, Sample sampleData) {
        if (hasValidId(id)) {
            Optional<Sample> optional = sampleRepository.find(id);

            if (optional.isPresent()) {
                sampleRepository.update(id, sampleData);
            } else {
                throw new SampleNotUpdatedException(SAMPLE_NOT_UPDATED_ERROR);
            }
        } else {
            throw new OutOfRangeException(OUT_OF_RANGE_ERROR);
        }
    }

    // -------------------------------------------- CRUD => DELETE

    @Override
    public void delete(Integer id) {
        if (hasValidId(id)) {
            Optional<Sample> optional = sampleRepository.find(id);

            if (optional.isPresent()) {
                sampleRepository.delete(id);
            } else {
                throw new SampleNotDeletedException(SAMPLE_NOT_DELETED_ERROR);
            }
        } else {
            throw new OutOfRangeException(OUT_OF_RANGE_ERROR);
        }
    }

    // ==================================================> SPECIAL METHODS

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
        if (id > SampleJpaDao.lastInsertId) {
            LOGGER.warn(SAMPLE_OUT_OF_RANGE, id, SampleJpaDao.lastInsertId);
        } else {
            validId = true;
        }

        return validId;
    }
}
