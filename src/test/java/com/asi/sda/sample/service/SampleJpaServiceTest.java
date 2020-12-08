package com.asi.sda.sample.service;

import com.asi.sda.sample.Sample;
import com.asi.sda.sample.SampleMapper;
import com.asi.sda.sample.SampleResponseDto;
import com.asi.sda.sample.repository.SampleJpaDao;
import com.asi.sda.sample.repository.SampleRepository;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SampleJpaServiceTest {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("MacroMedia");
    private static final EntityManager em = emf.createEntityManager();
    private static final SampleRepository dao = new SampleJpaDao(em);
    private static final SampleService service = new SampleJpaService(dao);

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

        SampleResponseDto result = service.update(entity.getId(), data);

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
