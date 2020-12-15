package com.asi.sda.sample.repository;

import com.asi.sda.sample.model.Sample;
import com.asi.sda.sample.config.HibernateConfig;
import com.asi.sda.sample.database.SampleSimDatabase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.asi.sda.sample.constant.CommonMessages.*;
import static com.asi.sda.sample.constant.SampleMessages.*;

public class SampleHibernateDao implements SampleRepository {
    private static final Logger LOGGER = LogManager.getLogger(SampleHibernateDao.class);

    private static final SampleSimDatabase database = SampleSimDatabase.getInstance();

    public static int lastInsertId;

    @Override
    public List<Sample> createAll(List<Sample> samples) {
        List<Sample> duplicates = database.getSampleList(); // import
        List<Sample> results = new ArrayList<>();
        List<Sample> clones = new ArrayList<>();

        Transaction transaction = null;
        try (Session session = openSession()) {
            LOGGER.info(SAMPLES_START + PLEASE_WAIT);
            transaction = session.beginTransaction();
            Integer foundId = null;
            for (Sample item : samples) {
                clones.add(item);
                session.save(item);
                results.add(item);
                foundId = item.getId();
            }
            transaction.commit();
            LOGGER.info(SAMPLES_FINAL, foundId);

            clones = SampleSimDatabase.generateIdAll(clones, lastInsertId);

            lastInsertId += samples.size();
            LOGGER.info(SAMPLES_SAVED, samples.size());
            if (!foundId.equals(lastInsertId)) {
                LOGGER.warn(LAST_INSERT, lastInsertId, foundId);
            }

            duplicates.addAll(clones);
            database.setSampleList(duplicates); // export
        } catch (HibernateException exception) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.error(CREATE_ERROR);
        }

        database.displayTable(clones);
        return results;
    }

    @Override
    public Sample create(Sample sample) {
        List<Sample> duplicates = database.getSampleList(); // import
        List<Sample> clones = new ArrayList<>();
        Sample result = new Sample();

        Transaction transaction = null;
        try (Session session = openSession()) {
            clones.add(SampleSimDatabase.generateIdOne(sample, lastInsertId));
            transaction = session.beginTransaction();
            session.save(sample);
            transaction.commit();
            result = sample;

            lastInsertId++;
            Integer foundId = sample.getId();
            LOGGER.info(SAMPLE_SAVED, foundId);
            if (!foundId.equals(lastInsertId)) {
                LOGGER.warn(LAST_INSERT, lastInsertId, foundId);
            }

            duplicates.addAll(clones);
            database.setSampleList(duplicates); // export
        } catch (HibernateException exception) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.error(CREATE_ERROR);
        }

        database.displayTable(clones);
        return result;
    }

    @Override
    public List<Sample> findAll() {
        List<Sample> duplicates = database.getSampleList(); // import
        List<Sample> results = new ArrayList<>();

        try (Session session = openSession()) {
            Query<Sample> query = session.createQuery(FIND_ALL_HQL);

            results = query.list();

            if (results.isEmpty()) {
                LOGGER.warn(SAMPLES_NOT_FOUND, 0, "all");
            } else {
                LOGGER.info(SAMPLES_FOUND, results.size(), "all");
            }
        } catch (HibernateException exception) {
            LOGGER.error(SEARCH_ERROR);
        }

        database.displayTable(duplicates);
        return results;
    }

    @Override
    public List<Sample> findByText(String text) {
        List<Sample> duplicates = database.getSampleList(); // import
        List<Sample> results = new ArrayList<>();
        List<Sample> clones = new ArrayList<>();

        try (Session session = openSession()) {
            Query<Sample> query = session.createQuery(FIND_BY_TEXT_HQL);
            query.setParameter("text", text);
            List<Sample> entities = query.list();

            // hql search is case insensitive by default
            for (Sample item : entities) {
                if (item.getText().equals(text)) {
                    results.add(item);
                }
            }

            if (results.isEmpty()) {
                LOGGER.warn(SAMPLES_NOT_FOUND, 0, text);
            } else {
                LOGGER.info(SAMPLES_FOUND, results.size(), text);
            }

            for (Sample item : duplicates) {
                if (item.getText().equals(text)) {
                    clones.add(item);
                }
            }
        } catch (HibernateException exception) {
            LOGGER.error(SEARCH_ERROR);
        }

        database.displayTable(clones);
        return results;
    }

    @Override
    public Optional<Sample> find(Integer id) {
        List<Sample> duplicates = database.getSampleList(); // import
        List<Sample> clones = new ArrayList<>();
        Sample result = null;

        try (Session session = openSession()) {
            result = session.find(Sample.class, id);

            if (result == null) {
                LOGGER.warn(SAMPLE_NOT_FOUND, id);
            } else {
                LOGGER.info(SAMPLE_FOUND, result.getId());
            }

            Integer index = null;
            for (int k = 0; k < duplicates.size(); k++) {
                if (duplicates.get(k).getId().equals(id)) {
                    index = k;
                }
            }
            if (index != null) {
                clones.add(duplicates.get(index));
            }
        } catch (HibernateException exception) {
            LOGGER.error(SEARCH_ERROR);
        }

        database.displayTable(clones);
        return Optional.ofNullable(result);
    }

    @Override
    public Optional<Sample> update(Integer id, Sample data) {
        List<Sample> duplicates = database.getSampleList(); // import
        List<Sample> clones = new ArrayList<>();
        Sample result = null;

        Transaction transaction = null;
        try (Session session = openSession()) {
            transaction = session.beginTransaction();
            Sample entity = session.find(Sample.class, id);

            if (entity == null) {
                LOGGER.warn(SAMPLE_NOT_UPDATED + SAMPLE_NOT_FOUND, id);
            } else {
                LOGGER.info(SAMPLE_FOUND, entity.getId());
                entity.setText(data.getText());
                result = entity;
                session.update(entity);
                transaction.commit();
                LOGGER.info(SAMPLE_UPDATED, entity.getId());
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
                clones.add(duplicates.get(index));
            }
        } catch (HibernateException exception) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.error(UPDATE_ERROR);
        }

        database.displayTable(clones);
        return Optional.ofNullable(result);
    }

    @Override
    public Optional<Sample> delete(Integer id) {
        List<Sample> duplicates = database.getSampleList(); // import
        List<Sample> clones = new ArrayList<>();
        Sample result = null;

        Transaction transaction = null;
        try (Session session = openSession()) {
            transaction = session.beginTransaction();
            Sample entity = session.find(Sample.class, id);

            if (entity == null) {
                LOGGER.warn(SAMPLE_NOT_DELETED + SAMPLE_NOT_FOUND, id);
            } else {
                LOGGER.info(SAMPLE_FOUND, entity.getId());
                result = entity;
                session.delete(entity);
                transaction.commit();
                LOGGER.info(SAMPLE_DELETED, id);
            }

            Integer index = null;
            for (int k = 0; k < duplicates.size(); k++) {
                if (duplicates.get(k).getId().equals(id)) {
                    index = k;
                }
            }
            if (index != null) {
                clones.add(duplicates.get(index));
                duplicates.remove((int) index);
                database.setSampleList(duplicates); // export
            }
        } catch (HibernateException exception) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.error(DELETE_ERROR);
        }

        database.displayTable(clones);
        return Optional.ofNullable(result);
    }

    private Session openSession() {
        return HibernateConfig.getSessionFactory().openSession();
    }
}
