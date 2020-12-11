package com.asi.sda.sample.repository;

import com.asi.sda.sample.Sample;
import com.asi.sda.sample.database.SampleSimDatabase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.asi.sda.sample.constant.CommonMessages.*;
import static com.asi.sda.sample.constant.SampleMessages.*;


public class SampleJpaDao implements SampleRepository {
    private static final Logger LOGGER = LogManager.getLogger(SampleJpaDao.class);

    private static final SampleSimDatabase database = SampleSimDatabase.getInstance();

    public static int lastInsertId;

    private final EntityManager entityManager;

    public SampleJpaDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Sample> createAll(List<Sample> samples) {
        List<Sample> duplicates = database.getSampleList(); // import
        List<Sample> results = new ArrayList<>();
        List<Sample> clones = new ArrayList<>();

        try {
            LOGGER.info(SAMPLES_START + PLEASE_WAIT);
            entityManager.getTransaction().begin();
            Integer foundId = null;
            for (Sample item : samples) {
                clones.add(item);
                entityManager.persist(item);
                results.add(item);
                foundId = item.getId();
            }
            entityManager.getTransaction().commit();
            LOGGER.info(SAMPLES_FINAL, foundId);
            clones = SampleSimDatabase.generateIdAll(clones, lastInsertId);

            lastInsertId += samples.size();
            LOGGER.info(SAMPLES_SAVED, samples.size());
            if (!foundId.equals(lastInsertId)) {
                LOGGER.warn(LAST_INSERT, lastInsertId, foundId);
            }

            duplicates.addAll(clones);
            database.setSampleList(duplicates); // export
        } catch (Exception exception) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        }

        database.displayTable(clones);
        return results;
    }

    @Override
    public Sample create(Sample sample) {
        List<Sample> duplicates = database.getSampleList(); // import
        List<Sample> clones = new ArrayList<>();
        Sample result = new Sample();

        try {
            clones.add(SampleSimDatabase.generateIdOne(sample, lastInsertId));
            entityManager.getTransaction().begin();
            entityManager.persist(sample);
            entityManager.getTransaction().commit();
            result = sample;

            lastInsertId++;
            Integer foundId = sample.getId();
            LOGGER.info(SAMPLE_SAVED, foundId);
            if (!foundId.equals(lastInsertId)) {
                LOGGER.warn(LAST_INSERT, lastInsertId, foundId);
            }

            duplicates.addAll(clones);
            database.setSampleList(duplicates); // export
        } catch (Exception exception) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        }

        database.displayTable(clones);
        return result;
    }

    @Override
    public List<Sample> findAll() {
        TypedQuery<Sample> typedQuery = entityManager.createQuery(FIND_ALL_JPQL, Sample.class);

        List<Sample> samples = typedQuery.getResultList();

        if (samples.isEmpty()) {
            LOGGER.warn(SAMPLES_NOT_FOUND, 0, "all");
        } else {
            LOGGER.info(SAMPLES_FOUND, samples.size(), "all");
        }

        return samples;
    }

    @Override
    public List<Sample> findByText(String text) {
        TypedQuery<Sample> typedQuery = entityManager.createQuery(FIND_BY_TEXT_JPQL, Sample.class);
        typedQuery.setParameter("text", text);

        // jpql search is case insensitive by default
        List<Sample> results = typedQuery.getResultList();
        List<Sample> entities = new ArrayList<>();
        for (Sample item : results) {
            if (item.getText().equals(text)) {
                entities.add(item);
            }
        }

        if (entities.isEmpty()) {
            LOGGER.warn(SAMPLES_NOT_FOUND, 0, text);
        } else {
            LOGGER.info(SAMPLES_FOUND, entities.size(), text);
        }

        return entities;
    }

    @Override
    public Optional<Sample> find(Integer id) {
        Sample entity = entityManager.find(Sample.class, id);

        if (entity == null) {
            LOGGER.warn(SAMPLE_NOT_FOUND, id);
        } else {
            LOGGER.info(SAMPLE_FOUND, entity.getId());
        }

        return Optional.ofNullable(entity);
    }

    @Override
    public Optional<Sample> update(Integer id, Sample data) {
        List<Sample> duplicates = database.getSampleList(); // import
        Sample entity = null;

        try {
            Sample result = entityManager.find(Sample.class, id);

            if (result == null) {
                LOGGER.warn(SAMPLE_NOT_UPDATED + SAMPLE_NOT_FOUND, id);
            } else {
                result.setText(data.getText());
                entity = result;
                entityManager.getTransaction().begin();
                entityManager.merge(result);
                entityManager.getTransaction().commit();
                LOGGER.info(SAMPLE_UPDATED, result.getId());
            }

            Integer index = null;
            for (int k = 0; k < duplicates.size(); k++) {
                if (duplicates.get(k).getId().equals(id)) {
                    index = k;
                }
            }
            if (index != null) {
                duplicates.get(index).setText(data.getText());
                database.setSampleList(duplicates); // export
            }
        } catch (Exception exception) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        }

        return Optional.ofNullable(entity);
    }

    @Override
    public Optional<Sample> delete(Integer id) {
        List<Sample> duplicates = database.getSampleList(); // import
        Sample entity = null;

        try {
            Sample result = entityManager.find(Sample.class, id);

            if (result == null) {
                LOGGER.warn(SAMPLE_NOT_DELETED + SAMPLE_NOT_FOUND, id);
            } else {
                entity = result;
                entityManager.getTransaction().begin();
                entityManager.remove(result);
                entityManager.getTransaction().commit();

                LOGGER.info(SAMPLE_DELETED, id);
            }

            Integer index = null;
            for (int k = 0; k < duplicates.size(); k++) {
                if (duplicates.get(k).getId().equals(id)) {
                    index = k;
                }
            }
            if (index != null) {
                duplicates.remove((int) index);
                database.setSampleList(duplicates); // export
            }
        } catch (Exception exception) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        }

        return Optional.ofNullable(entity);
    }
}
