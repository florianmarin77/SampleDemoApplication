package com.asi.sda.sample.repository;

import com.asi.sda.sample.Sample;

import java.util.List;
import java.util.Optional;

public interface SampleRepository {

    /**
     * Inserts all samples in the database.
     *
     * @param samples a list of samples to be created
     * @return a list of created samples
     */
    List<Sample> createAll(List<Sample> samples);

    /**
     * Inserts a complete sample in the database.
     *
     * @param sample the sample to be created
     * @return the created sample
     */
    Sample create(Sample sample);

    /**
     * Returns all samples from the database.
     *
     * @return a list of found samples
     */
    List<Sample> findAll();

    /**
     * Returns all samples by text.
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

    /**
     * Updates a specific sample by id.
     *
     * @param id   the specific sample id
     * @param data the object data to update
     */
    void update(Integer id, Sample data);

    /**
     * Deletes a specific sample from the database.
     *
     * @param id the specific sample id
     */
    void delete(Integer id);
}
