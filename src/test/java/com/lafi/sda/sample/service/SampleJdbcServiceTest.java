package com.lafi.sda.sample.service;

import com.lafi.sda.sample.model.Sample;
import com.lafi.sda.sample.model.SampleMapper;
import com.lafi.sda.sample.model.SampleResponseDto;
import com.lafi.sda.sample.repository.SampleJdbcDao;
import com.lafi.sda.sample.repository.SampleRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SampleJdbcServiceTest {
    private static final SampleRepository dao = new SampleJdbcDao();
    private static final SampleService service = new SampleJdbcService(dao);

    private static final SampleJdbcDao jdbcDao = new SampleJdbcDao(); // create-drop table

    @BeforeAll
    static void setUp() {
        jdbcDao.createTable();
    }

    @AfterAll
    static void tearDown() {
        jdbcDao.deleteTable();
    }

    @Test
    void createAll() {
        List<SampleResponseDto> resultsBefore = service.findAll();
        Sample sample1 = new Sample("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        Sample sample2 = new Sample("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        List<Sample> samples = new ArrayList<>();
        samples.add(sample1);
        samples.add(sample2);

        service.createAll(SampleMapper.toRequestDtos(samples));
        List<SampleResponseDto> resultsAfter = service.findAll();

        assertThat(resultsAfter.size()).isEqualTo(resultsBefore.size() + samples.size());
    }

    @Test
    void create() {
        List<SampleResponseDto> resultsBefore = service.findAll();
        Sample sample = new Sample("ABCDEFGHIJKLMNOPQRSTUVWXYZ");

        service.create(SampleMapper.toRequestDto(sample));
        List<SampleResponseDto> resultsAfter = service.findAll();

        assertThat(resultsAfter.size()).isEqualTo(resultsBefore.size() + 1);
    }

    @Test
    void findAll() {
        List<SampleResponseDto> resultsBefore = service.findAll();
        service.create(SampleMapper.toRequestDto(new Sample("ABCDEFGHIJKLMNOPQRSTUVWXYZ")));

        List<SampleResponseDto> resultsAfter = service.findAll();

        assertThat(resultsAfter.size()).isGreaterThan(resultsBefore.size());
    }

    @Test
    void findByText() {
        service.create(SampleMapper.toRequestDto(new Sample("ABCDEFGHIJKLMNOPQRSTUVWXYZ")));

        List<SampleResponseDto> results = service.findByText("ABCDEFGHIJKLMNOPQRSTUVWXYZ");

        for (SampleResponseDto item : results) {
            assertThat(item.getText()).isEqualTo("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        }
    }

    @Test
    void find() {
        Sample sample = new Sample("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        SampleResponseDto entity = service.create(SampleMapper.toRequestDto(sample));

        SampleResponseDto result = service.find(entity.getId());

        assertThat(result.getId()).isEqualTo(entity.getId());
    }

    @Test
    void update() {
        Sample sample = new Sample("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        Sample data = new Sample("abcdefghijklmnopqrstuvwxyz");
        SampleResponseDto entity = service.create(SampleMapper.toRequestDto(sample));

        SampleResponseDto result = service.update(entity.getId(), SampleMapper.toRequestDto(data));

        assertThat(result.getText()).isEqualTo(data.getText());
    }

    @Test
    void delete() {
        Sample sample = new Sample("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        SampleResponseDto entity = service.create(SampleMapper.toRequestDto(sample));
        List<SampleResponseDto> resultsBefore = service.findAll();

        service.delete(entity.getId());
        List<SampleResponseDto> resultsAfter = service.findAll();

        assertThat(resultsAfter.size()).isLessThan(resultsBefore.size());
    }
}
