package com.asi.sda.sample.repository;

import com.asi.sda.sample.Sample;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class SampleSimDaoTest {
    private static final SampleRepository dao = new SampleSimDao();

    @Test
    void createAll() {
        int before = dao.findAll().size();

        Sample sample1 = new Sample("0123456789");
        Sample sample2 = new Sample("9876543210");
        List<Sample> samples = new ArrayList<>();
        samples.add(sample1);
        samples.add(sample2);

        dao.createAll(samples);
        int after = dao.findAll().size();

        assertThat(after).isEqualTo(before + samples.size());
    }

    @Test
    void create() {
        int before = dao.findAll().size();

        Sample sample = new Sample("ABCDEFGHIJKLMNOPQRSTUVWXYZ");

        dao.create(sample);
        int after = dao.findAll().size();

        assertThat(after).isEqualTo(before + 1);
    }

    @Test
    void findAll() {
        int before = dao.findAll().size();

        dao.create(new Sample("ABCDEFGHIJKLMNOPQRSTUVWXYZ"));

        int after = dao.findAll().size();

        assertThat(after).isGreaterThan(before);
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

        Sample result = dao.create(sample);

        Optional<Sample> optional = dao.update(result.getId(), data);
        if (optional.isPresent()) {
            entity = optional.get();
        }

        assertThat(entity.getText()).isEqualTo(data.getText());
    }

    @Test
    void delete() {
        Sample sample = new Sample("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        Sample result = dao.create(sample);
        int before = dao.findAll().size();

        dao.delete(result.getId());
        int after = dao.findAll().size();

        assertThat(after).isLessThan(before);
    }
}
