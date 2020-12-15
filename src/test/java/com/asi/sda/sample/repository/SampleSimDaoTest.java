package com.asi.sda.sample.repository;

import com.asi.sda.sample.model.Sample;
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

        List<Sample> results = dao.findByText("ABCDEFGHIJKLMNOPQRSTUVWXYZ");

        for (Sample item : results) {
            assertThat(item.getText()).isEqualTo("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        }
        database.displayTable(database.getSampleList());
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

        assertThat(result.getId()).isEqualTo(entity.getId());
        database.displayTable(database.getSampleList());
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
        database.displayTable(database.getSampleList());
    }

    @Test
    void delete() {
        Sample sample = new Sample("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        Sample entity = dao.create(sample);
        int before = dao.findAll().size();

        dao.delete(entity.getId());
        int after = dao.findAll().size();

        assertThat(after).isLessThan(before);
        database.displayTable(database.getSampleList());
    }
}
