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
        List<Sample> resultsBefore = dao.findAll();
        Sample sample1 = new Sample("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        Sample sample2 = new Sample("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        List<Sample> samples = new ArrayList<>();
        samples.add(sample1);
        samples.add(sample2);

        dao.createAll(samples);
        List<Sample> resultsAfter = dao.findAll();

        assertThat(resultsAfter.size()).isEqualTo(resultsBefore.size() + samples.size());
    }

    @Test
    void create() {
        List<Sample> resultsBefore = dao.findAll();
        Sample sample = new Sample("abcdefghijklmnopqrstuvwxyz");

        dao.create(sample);
        List<Sample> resultsAfter = dao.findAll();

        assertThat(resultsAfter.size()).isEqualTo(resultsBefore.size() + 1);
    }

    @Test
    void findAll() {
        List<Sample> resultsBefore = dao.findAll();
        dao.create(new Sample("ABCDEFGHIJKLMNOPQRSTUVWXYZ"));

        List<Sample> resultsAfter = dao.findAll();

        assertThat(resultsAfter.size()).isGreaterThan(resultsBefore.size());
    }

    @Test
    void findByText() {
        dao.create(new Sample("ABCDEFGHIJKLMNOPQRSTUVWXYZ"));

        List<Sample> results = dao.findByText("ABCDEFGHIJKLMNOPQRSTUVWXYZ");

        for (Sample item : results) {
            assertThat(item.getText()).isEqualTo("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        }
    }

    @Test
    void find() {
        Sample sample = new Sample("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        Sample entity = dao.create(sample);
        Sample result = new Sample();

        Optional<Sample> optional = dao.find(entity.getId());
        if (optional.isPresent()) {
            result = optional.get();
        }

        assertThat(entity.getId()).isEqualTo(result.getId());
    }

    @Test
    void update() {
        Sample sample = new Sample("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        Sample data = new Sample("abcdefghijklmnopqrstuvwxyz");
        Sample entity = dao.create(sample);
        Sample result = new Sample();

        dao.update(entity.getId(), data);
        Optional<Sample> optional = dao.find(entity.getId());
        if (optional.isPresent()) {
            result = optional.get();
        }

        assertThat(result.getText()).isEqualTo(data.getText());
    }

    @Test
    void delete() {
        Sample sample = new Sample("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        Sample entity = dao.create(sample);
        List<Sample> samplesBefore = dao.findAll();

        dao.delete(entity.getId());
        List<Sample> samplesAfter = dao.findAll();

        assertThat(samplesAfter.size()).isLessThan(samplesBefore.size());
    }
}
