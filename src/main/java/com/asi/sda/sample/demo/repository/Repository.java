package com.asi.sda.sample.demo.repository;

import com.asi.sda.sample.demo.model.Single;

import java.util.List;
import java.util.Optional;

public interface Repository {

    /**
     * Inserts all singles in the database.
     *
     * @param singles a list of singles to be created
     * @return a list of created singles
     */
    List<Single> createAll(List<Single> singles);

    /**
     * Inserts a complete single in the database.
     *
     * @param single the single to be created
     * @return the created single
     */
    Single create(Single single);

    /**
     * Returns all singles from the database.
     *
     * @return a list of found singles
     */
    List<Single> findAll();

    /**
     * Returns all singles by text.
     *
     * @param text the single internal text
     * @return a list of found singles
     */
    List<Single> findByText(String text);

    /**
     * Returns a specific single by id.
     *
     * @param id the specific single id
     * @return the found single only if id exists
     */
    Optional<Single> find(Integer id);

    /**
     * Updates a specific single by id.
     *
     * @param id   the specific single id
     * @param data the object data to update
     * @return the updated single only if id exists
     */
    Optional<Single> update(Integer id, Single data);

    /**
     * Deletes a specific single from the database.
     *
     * @param id the specific single id
     * @return the deleted single only if id exists
     */
    Optional<Single> delete(Integer id);

}
