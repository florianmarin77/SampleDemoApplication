package com.asi.sda.sample.repository;

import com.asi.sda.sample.Sample;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class SampleJpaDaoTest {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("MacroMedia");
    private static final EntityManager em = emf.createEntityManager();
    private static final SampleRepository dao = new SampleJpaDao(em);

    @Test
    void createAll() {
        List<Sample> samplesBefore = dao.findAll();
        Sample sample1 = new Sample("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        Sample sample2 = new Sample("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        List<Sample> samples = new ArrayList<>();
        samples.add(sample1);
        samples.add(sample2);

        dao.createAll(samples);
        List<Sample> samplesAfter = dao.findAll();

        assertThat(samplesAfter.size()).isEqualTo(samplesBefore.size() + samples.size());
    }

    @Test
    void create() {
        List<Sample> samplesBefore = dao.findAll();
        Sample sample = new Sample("ABCDEFGHIJKLMNOPQRSTUVWXYZ");

        dao.create(sample);
        List<Sample> samplesAfter = dao.findAll();

        assertThat(samplesAfter.size()).isEqualTo(samplesBefore.size() + 1);
    }

    @Test
    void findAll() {
        List<Sample> samplesBefore = dao.findAll();
        dao.create(new Sample("ABCDEFGHIJKLMNOPQRSTUVWXYZ"));

        List<Sample> samplesAfter = dao.findAll();

        assertThat(samplesAfter.size()).isGreaterThan(samplesBefore.size());
    }

    @Test
    void findByText() {
        dao.create(new Sample("ABCDEFGHIJKLMNOPQRSTUVWXYZ"));

        List<Sample> samples = dao.findByText("ABCDEFGHIJKLMNOPQRSTUVWXYZ");

        // case sensitive validation after jpql search
        for (Sample item : samples) {
            assertThat(item.getText()).isEqualTo("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        }
    }

    @Test
    void find() {
        Sample sample = new Sample("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        Sample entity = new Sample();
        dao.create(sample);

        Optional<Sample> optional = dao.find(sample.getId());
        if (optional.isPresent()) {
            entity = optional.get();
        }

        assertThat(sample.getId()).isEqualTo(entity.getId());
    }

    @Test
    void update() {
        Sample sample = new Sample("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        Sample data = new Sample("abcdefghijklmnopqrstuvwxyz");
        Sample entity = new Sample();
        dao.create(sample);

        dao.update(sample.getId(), data);
        Optional<Sample> optional = dao.find(sample.getId());
        if (optional.isPresent()) {
            entity = optional.get();
        }

        assertThat(entity.getText()).isEqualTo(data.getText());
    }

    @Test
    void delete() {
        Sample sample = new Sample("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        dao.create(sample);
        List<Sample> samplesBefore = dao.findAll();

        dao.delete(sample.getId());
        List<Sample> samplesAfter = dao.findAll();

        assertThat(samplesAfter.size()).isLessThan(samplesBefore.size());
    }
}
