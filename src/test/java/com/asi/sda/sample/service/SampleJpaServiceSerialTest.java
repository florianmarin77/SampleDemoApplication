package com.asi.sda.sample.service;

import com.asi.sda.sample.model.Sample;
import com.asi.sda.sample.model.SampleMapper;
import com.asi.sda.sample.model.SampleResponseDto;
import com.asi.sda.sample.repository.SampleJdbcDao;
import com.asi.sda.sample.repository.SampleJpaDao;
import com.asi.sda.sample.repository.SampleRepository;
import org.junit.jupiter.api.*;

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

    private static final SampleJdbcDao jdbcDao = new SampleJdbcDao(); // drop table

    @AfterAll
    static void tearDown() {
        jdbcDao.deleteTable();
    }

    @Test
    @Order(1)
    void createAll() {
        Sample sample1 = new Sample("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        Sample sample2 = new Sample("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        List<Sample> samples = new ArrayList<>();
        samples.add(sample1);
        samples.add(sample2);

        List<SampleResponseDto> responses = service.createAll(SampleMapper.toRequestDtos(samples));

        assertThat(responses.get(0).getId()).isEqualTo(1);
        assertThat(responses.get(1).getId()).isEqualTo(2);
    }

    @Test
    @Order(2)
    void create() {
        Sample sample = new Sample("abcdefghijklmnopqrstuvwxyz");

        SampleResponseDto response = service.create(SampleMapper.toRequestDto(sample));

        assertThat(response.getId()).isEqualTo(3);
    }

    @Test
    @Order(3)
    void findAll() {
        List<SampleResponseDto> results = service.findAll();

        assertThat(results).hasSize(3);
    }

    @Test
    @Order(4)
    void findByText() {
        List<SampleResponseDto> results = service.findByText("ABCDEFGHIJKLMNOPQRSTUVWXYZ");

        assertThat(results).hasSize(2);
        for (SampleResponseDto item : results) {
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

        SampleResponseDto result = service.update(2, SampleMapper.toRequestDto(data));

        assertThat(result.getText()).isEqualTo("abcdefghijklmnopqrstuvwxyz");
    }

    @Test
    @Order(7)
    void delete() {
        SampleResponseDto result = service.delete(3);

        List<SampleResponseDto> results = service.findAll();

        assertThat(results.size()).isEqualTo(2);
        assertThat(result.getId()).isEqualTo(3);
    }
}
