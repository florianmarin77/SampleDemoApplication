package com.asi.sda.sample.repository;

import com.asi.sda.sample.Sample;
import com.asi.sda.sample.exception.SampleNotFoundException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

import static com.asi.sda.sample.constant.SampleMessages.SAMPLE_NOT_FOUND_ERROR;
import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SampleJpaDaoSerialTest {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("MacroMedia");
    private static final EntityManager em = emf.createEntityManager();
    private static final SampleRepository dao = new SampleJpaDao(em);

    @Test
    @Order(1)
    void createAll() {
        Sample sample1 = new Sample("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        Sample sample2 = new Sample("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        List<Sample> samples = new ArrayList<>();
        samples.add(sample1);
        samples.add(sample2);

        List<Sample> entities = dao.createAll(samples);

        assertThat(entities.get(0).getId()).isEqualTo(1);
        assertThat(entities.get(1).getId()).isEqualTo(2);
    }

    @Test
    @Order(2)
    void create() {
        Sample sample = new Sample("abcdefghijklmnopqrstuvwxyz");

        Sample entity = dao.create(sample);

        assertThat(entity.getId()).isEqualTo(3);
    }

    @Test
    @Order(3)
    void findAll() {
        List<Sample> results = dao.findAll();

        assertThat(results).hasSize(3);
    }

    @Test
    @Order(4)
    void findByText() {
        List<Sample> results = dao.findByText("ABCDEFGHIJKLMNOPQRSTUVWXYZ");

        assertThat(results).hasSize(2);
        for (Sample item : results) {
            assertThat(item.getText()).isEqualTo("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        }
    }

    @Test
    @Order(5)
    void find() {
        Sample result = dao.find(3)
                .orElseThrow(() -> new SampleNotFoundException(SAMPLE_NOT_FOUND_ERROR));

        assertThat(result.getText()).isEqualTo("abcdefghijklmnopqrstuvwxyz");
    }

    @Test
    @Order(6)
    void update() {
        Sample data = new Sample("abcdefghijklmnopqrstuvwxyz");

        Sample result = dao.update(2, data)
                .orElseThrow(() -> new SampleNotFoundException(SAMPLE_NOT_FOUND_ERROR));

        assertThat(result.getText()).isEqualTo("abcdefghijklmnopqrstuvwxyz");
    }

    @Test
    @Order(7)
    void delete() {
        Sample result = dao.delete(3)
                .orElseThrow(() -> new SampleNotFoundException(SAMPLE_NOT_FOUND_ERROR));

        List<Sample> results = dao.findAll();

        assertThat(results.size()).isEqualTo(2);
        assertThat(result.getId()).isEqualTo(3);
    }
}
