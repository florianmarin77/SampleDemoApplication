package com.asi.demo.sample.service;

import com.asi.demo.sample.Sample;
import com.asi.demo.sample.SampleRequestDto;
import com.asi.demo.sample.SampleResponseDto;

import java.util.List;

public interface SampleService {

    // -------------------------------------------- CRUD => CREATE

    /**
     * Inserts all samples in the database.
     *
     * @param requestDtos a list of samples to be saved
     * @return true if all samples were created
     */
    boolean createAll(List<SampleRequestDto> requestDtos);

    /**
     * Inserts a complete sample in the database.
     *
     * @param requestDto the sample to be saved
     * @return the created sample as data transfer object
     */
    SampleResponseDto create(SampleRequestDto requestDto);

    // -------------------------------------------- CRUD => READ

    /**
     * Returns all samples from the database.
     *
     * @return a list of found samples as dtos
     */
    List<SampleResponseDto> findAll();

    /**
     * Returns all samples by first name.
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
     * @return the updated sample as data transfer object
     */
    SampleResponseDto update(Integer id, Sample sampleData);

    // -------------------------------------------- CRUD => DELETE

    /**
     * Deletes a specific sample from the database.
     *
     * @param id the specific sample id
     * @return true if the sample was deleted
     */
    boolean delete(Integer id);

    /**
     * Deletes all samples from the database.
     *
     * @return true if all samples were deleted
     */
    boolean deleteAll();
}
