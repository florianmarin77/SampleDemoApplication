package com.lafi.sda.sample.demo.service;

import com.lafi.sda.sample.demo.model.Single;
import com.lafi.sda.sample.demo.model.SingleRequestDto;
import com.lafi.sda.sample.demo.model.SingleResponseDto;

import java.util.List;

public interface Service {

    /**
     * Inserts all singles in the database.
     *
     * @param requests a list of singles to be created
     * @return a list of created singles
     */
    List<SingleResponseDto> createAll(List<SingleRequestDto> requests);

    /**
     * Inserts a complete single in the database.
     *
     * @param request the single to be created
     * @return the created single as data transfer object
     */
    SingleResponseDto create(SingleRequestDto request);

    /**
     * Returns all singles from the database.
     *
     * @return a list of found singles as dtos
     */
    List<SingleResponseDto> findAll();

    /**
     * Returns all singles by text.
     *
     * @param text the single internal text
     * @return a list of found singles as dtos
     */
    List<SingleResponseDto> findByText(String text);

    /**
     * Returns a specific single by id.
     *
     * @param id the specific single id
     * @return the found single as data transfer object
     */
    SingleResponseDto find(Integer id);

    /**
     * Updates a specific single by id.
     *
     * @param id   the specific single id
     * @param data the object data to update
     * @return the found single as data transfer object
     */
    SingleResponseDto update(Integer id, Single data);

    /**
     * Deletes a specific single from the database.
     *
     * @param id the specific single id
     * @return the found single as data transfer object
     */
    SingleResponseDto delete(Integer id);

}
