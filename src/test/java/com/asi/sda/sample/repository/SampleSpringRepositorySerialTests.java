package com.asi.sda.sample.repository;

import com.asi.sda.sample.model.Sample;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SampleSpringRepositorySerialTests {

    @Autowired
    private SampleSpringRepository repository;

    @Test
    @Rollback(false)
    @Order(1)
    void createSample() {
        Sample savedSample = repository.save(new Sample("tralala"));

        assertThat(savedSample.getId()).isPositive();
    }

    @Test
    @Order(2)
    void findSampleByText() {
        Sample sample = repository.findByText("tralala");

        assertThat(sample.getText()).isEqualTo("tralala");
    }

    @Test
    @Order(3)
    void listAllSamples() {
        List<Sample> samples = (List<Sample>) repository.findAll();

        assertThat(samples).size().isPositive();
    }

    @Test
    @Rollback(false)
    @Order(4)
    void updateSample() {
        Sample sample = repository.findByText("tralala");
        sample.setText("trilulilu");
        repository.save(sample);

        Sample updatedSample = repository.findByText("trilulilu");
        assertThat(updatedSample.getText()).isEqualTo("trilulilu");

    }

    @Test
    @Rollback(false)
    @Order(5)
    void deleteSample() {
        Sample sample = repository.findByText("trilulilu");
        repository.deleteById(sample.getId());

        Sample deletedSample = repository.findByText("trilulilu");
        assertThat(deletedSample).isNull();
    }
}
