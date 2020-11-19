package com.asi.sda.sample.repository;

import com.asi.sda.sample.Sample;

import java.util.List;
import java.util.Optional;

public interface SampleRepository {

    // -------------------------------------------- CRUD => CREATE

    /**
     * Inserts all samples in the database.
     *
     * @param samples a list of samples to be saved
     * @return true if all samples are created
     */
    List<Sample> createAll(List<Sample> samples);

    /**
     * Inserts a complete sample in the database.
     *
     * @param sample the sample to be saved
     * @return the created sample
     */
    Optional<Sample> create(Sample sample);

    // -------------------------------------------- CRUD => READ

    /**
     * Returns all samples from the database.
     *
     * @return a list of found samples
     */
    List<Sample> findAll();

    /**
     * Returns all samples by first name.
     *
     * @param text the sample internal text
     * @return a list of found samples
     */
    List<Sample> findByText(String text);

    /**
     * Returns a specific sample by id.
     *
     * @param id the specific sample id
     * @return the found sample if exists
     */
    Optional<Sample> find(Integer id);

    // -------------------------------------------- CRUD => UPDATE

    /**
     * Updates a specific sample by id.
     *
     * @param id         the specific sample id
     * @param sampleData the object data to update
     * @return the updated sample
     */
    Optional<Sample> update(Integer id, Sample sampleData);

    // -------------------------------------------- CRUD => DELETE

    /**
     * Deletes a specific sample from the database.
     *
     * @param id the specific sample id
     * @return true if the sample was deleted
     */
    boolean delete(Integer id);
}
