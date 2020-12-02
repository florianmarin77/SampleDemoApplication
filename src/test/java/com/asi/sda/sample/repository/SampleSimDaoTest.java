package com.asi.sda.sample.repository;

import com.asi.sda.sample.Sample;
import com.asi.sda.sample.database.SampleSimDatabase;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class SampleSimDaoTest {
    private static final SampleRepository dao = new SampleSimDao();
    private static final SampleSimDatabase database = SampleSimDatabase.getInstance();

    @Test
    void createAll() {
        int before = dao.findAll().size();

        Sample sample1 = new Sample("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        Sample sample2 = new Sample("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        List<Sample> samples = new ArrayList<>();
        samples.add(sample1);
        samples.add(sample2);

        dao.createAll(samples);
        int after = dao.findAll().size();

        assertThat(after).isEqualTo(before + samples.size());

        database.displayTable(database.getSampleList());
    }

    @Test
    void create() {
        int before = dao.findAll().size();

        Sample sample = new Sample("abcdefghijklmnopqrstuvwxyz");

        dao.create(sample);
        int after = dao.findAll().size();

        assertThat(after).isEqualTo(before + 1);

        database.displayTable(database.getSampleList());
    }

    @Test
    void findAll() {
        int before = dao.findAll().size();

        dao.create(new Sample("ABCDEFGHIJKLMNOPQRSTUVWXYZ"));

        int after = dao.findAll().size();

        assertThat(after).isGreaterThan(before);

        database.displayTable(database.getSampleList());
    }

    @Test
    void findByText() {
        dao.create(new Sample("ABCDEFGHIJKLMNOPQRSTUVWXYZ"));

        List<Sample> samples = dao.findByText("ABCDEFGHIJKLMNOPQRSTUVWXYZ");

        // case sensitive validation after jpql search
        for (Sample item : samples) {
            assertThat(item.getText()).isEqualTo("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        }

        database.displayTable(database.getSampleList());
    }

    @Test
    void find() {
        Sample sample = new Sample("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        Sample result = dao.create(sample);
        Sample entity = new Sample();

        Optional<Sample> optional = dao.find(result.getId());
        if (optional.isPresent()) {
            entity = optional.get();
        }

        assertThat(result.getId()).isEqualTo(entity.getId());

        database.displayTable(database.getSampleList());
    }

    @Test
    void update() {
        Sample sample = new Sample("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        Sample result = dao.create(sample);

        Sample data = new Sample("abcdefghijklmnopqrstuvwxyz");
        Sample entity = new Sample();

        Optional<Sample> optional = dao.update(result.getId(), data);
        if (optional.isPresent()) {
            entity = optional.get();
        }

        assertThat(entity.getText()).isEqualTo(data.getText());

        database.displayTable(database.getSampleList());
    }

    @Test
    void delete() {
        Sample sample = new Sample("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        Sample result = dao.create(sample);
        int before = dao.findAll().size();

        dao.delete(result.getId());
        int after = dao.findAll().size();

        assertThat(after).isLessThan(before);

        database.displayTable(database.getSampleList());
    }
}
