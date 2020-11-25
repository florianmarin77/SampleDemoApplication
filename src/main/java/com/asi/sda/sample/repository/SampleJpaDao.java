package com.asi.sda.sample.repository;

import com.asi.sda.sample.Sample;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

import static com.asi.sda.sample.constant.CommonMessages.*;
import static com.asi.sda.sample.constant.SampleMessages.*;


public class SampleJpaDao implements SampleRepository {

    private static final Logger LOGGER = LogManager.getLogger(SampleJpaDao.class);

    public static int lastInsertId;


    private final EntityManager entityManager;

    public SampleJpaDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // -------------------------------------------- CRUD => CREATE

    @Override
    public List<Sample> createAll(List<Sample> samples) {
        try {
            LOGGER.info(SAMPLES_START + PLEASE_WAIT);
            entityManager.getTransaction().begin();
            Integer foundId = null;
            for (Sample item : samples) {
                entityManager.persist(item);
                foundId = item.getId();
            }
            entityManager.getTransaction().commit();
            LOGGER.info(SAMPLES_FINAL, foundId);

            lastInsertId += samples.size();
            LOGGER.info(SAMPLES_SAVED, samples.size());
            if (!foundId.equals(lastInsertId)) {
                LOGGER.warn(LAST_INSERT, lastInsertId, foundId);
            }
        } catch (Exception exception) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        }

        return samples; // entity if successfully
    }

    @Override
    public Sample create(Sample sample) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(sample);
            entityManager.getTransaction().commit();

            lastInsertId++;
            Integer foundId = sample.getId();
            LOGGER.info(SAMPLE_SAVED, foundId);
            if (!foundId.equals(lastInsertId)) {
                LOGGER.warn(LAST_INSERT, lastInsertId, foundId);
            }
        } catch (Exception exception) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        }

        return sample; // entity if successfully
    }

    // -------------------------------------------- CRUD => READ

    @Override
    public List<Sample> findAll() {
        TypedQuery<Sample> typedQuery = entityManager
                .createQuery(FIND_ALL_JPQL, Sample.class);

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
        TypedQuery<Sample> typedQuery = entityManager
                .createQuery(FIND_BY_TEXT_JPQL, Sample.class);
        typedQuery.setParameter("text", text);

        List<Sample> entities = typedQuery.getResultList();

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

    // -------------------------------------------- CRUD => UPDATE

    @Override
    public void update(Integer id, Sample sampleData) {
        Sample foundSample = entityManager.find(Sample.class, id);

        try {
            if (foundSample == null) {
                LOGGER.warn(SAMPLE_NOT_UPDATED + SAMPLE_NOT_FOUND, id);
            } else {
                foundSample.setText(sampleData.getText());

                entityManager.getTransaction().begin();
                entityManager.merge(foundSample);
                entityManager.getTransaction().commit();
                LOGGER.info(SAMPLE_UPDATED, foundSample.getId());
            }
        } catch (Exception exception) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        }
    }

    // -------------------------------------------- CRUD => DELETE

    @Override
    public void delete(Integer id) {
        try {
            Sample foundSample = entityManager.find(Sample.class, id);

            if (foundSample == null) {
                LOGGER.warn(SAMPLE_NOT_DELETED + SAMPLE_NOT_FOUND, id);
            } else {
                entityManager.getTransaction().begin();
                entityManager.remove(foundSample);
                entityManager.getTransaction().commit();

                LOGGER.info(SAMPLE_DELETED, id);
            }
        } catch (Exception exception) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        }
    }
}
