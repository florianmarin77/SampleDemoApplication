package com.asi.sda.sample.service;

import com.asi.sda.sample.Sample;
import com.asi.sda.sample.SampleRequestDto;
import com.asi.sda.sample.SampleResponseDto;

import java.util.List;

public interface SampleService {

    /**
     * Inserts all samples in the database.
     *
     * @param requests a list of samples to be created
     * @return a list of created samples
     */
    List<SampleResponseDto> createAll(List<SampleRequestDto> requests);

    /**
     * Inserts a complete sample in the database.
     *
     * @param request the sample to be created
     * @return the created sample as data transfer object
     */
    SampleResponseDto create(SampleRequestDto request);

    /**
     * Returns all samples from the database.
     *
     * @return a list of found samples as dtos
     */
    List<SampleResponseDto> findAll();

    /**
     * Returns all samples by text.
     *
     * @param text the sample internal text
     * @return a list of found samples as dtos
     */
    List<SampleResponseDto> findByText(String text);

    /**
     * Returns a specific sample by id.
     *
     * @param id the specific sample id
     * @return the found sample as data transfer object
     */
    SampleResponseDto find(Integer id);

    /**
     * Updates a specific sample by id.
     *
     * @param id   the specific sample id
     * @param data the object data to update
     * @return the found sample as data transfer object
     */
    SampleResponseDto update(Integer id, Sample data);

    /**
     * Deletes a specific sample from the database.
     *
     * @param id the specific sample id
     * @return the found sample as data transfer object
     */
    SampleResponseDto delete(Integer id);
}
