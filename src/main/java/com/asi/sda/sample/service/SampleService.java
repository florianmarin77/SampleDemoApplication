package com.asi.sda.sample.service;

import com.asi.sda.sample.Sample;
import com.asi.sda.sample.SampleRequestDto;
import com.asi.sda.sample.SampleResponseDto;

import java.util.List;

public interface SampleService {

    // -------------------------------------------- CRUD => CREATE

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

    // -------------------------------------------- CRUD => READ

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

    // -------------------------------------------- CRUD => UPDATE

    /**
     * Updates a specific sample by id.
     *
     * @param id         the specific sample id
     * @param sampleData the object data to update
     */
    void update(Integer id, Sample sampleData);

    // -------------------------------------------- CRUD => DELETE

    /**
     * Deletes a specific sample from the database.
     *
     * @param id the specific sample id
     */
    void delete(Integer id);
}
