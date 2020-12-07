package com.asi.sda.sample.service;

import com.asi.sda.sample.Sample;
import com.asi.sda.sample.SampleMapper;
import com.asi.sda.sample.SampleResponseDto;
import com.asi.sda.sample.repository.SampleJpaDao;
import com.asi.sda.sample.repository.SampleRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SampleJpaServiceSerialTest {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("MacroMedia");
    private static final EntityManager em = emf.createEntityManager();
    private static final SampleRepository dao = new SampleJpaDao(em);
    private static final SampleService service = new SampleJpaService(dao);

    @Test
    @Order(1)
    void createAll() {
        Sample sample1 = new Sample("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        Sample sample2 = new Sample("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        List<Sample> samples = new ArrayList<>();
        samples.add(sample1);
        samples.add(sample2);

        service.createAll(SampleMapper.toRequestDtos(samples));

        assertThat(samples.get(0).getId()).isEqualTo(1);
        assertThat(samples.get(1).getId()).isEqualTo(2);
    }

    @Test
    @Order(2)
    void create() {
        Sample sample = new Sample("abcdefghijklmnopqrstuvwxyz");

        service.create(SampleMapper.toRequestDto(sample));

        assertThat(sample.getId()).isEqualTo(3);
    }

    @Test
    @Order(3)
    void findAll() {
        List<SampleResponseDto> samples = service.findAll();

        assertThat(samples).hasSize(3);
    }

    @Test
    @Order(4)
    void findByText() {
        List<SampleResponseDto> samples = service.findByText("ABCDEFGHIJKLMNOPQRSTUVWXYZ");

        assertThat(samples).hasSize(2);
        for (SampleResponseDto item : samples) {
            assertThat(item.getText()).isEqualTo("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        }
    }

    @Test
    @Order(5)
    void find() {
        SampleResponseDto result = service.find(3);

        assertThat(result.getText()).isEqualTo("abcdefghijklmnopqrstuvwxyz");
    }

    @Test
    @Order(6)
    void update() {
        Sample data = new Sample("abcdefghijklmnopqrstuvwxyz");

        SampleResponseDto result = service.update(2, data);

        assertThat(result.getText()).isEqualTo("abcdefghijklmnopqrstuvwxyz");
    }

    @Test
    @Order(7)
    void delete() {
        SampleResponseDto result = service.delete(3);

        List<Sample> samples = dao.findAll();

        assertThat(samples.size()).isEqualTo(2);
        assertThat(result.getId()).isEqualTo(3);
    }
}
