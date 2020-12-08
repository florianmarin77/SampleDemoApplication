package com.asi.sda.sample.service;

import com.asi.sda.sample.Sample;
import com.asi.sda.sample.SampleMapper;
import com.asi.sda.sample.SampleResponseDto;
import com.asi.sda.sample.database.SampleSimDatabase;
import com.asi.sda.sample.repository.SampleRepository;
import com.asi.sda.sample.repository.SampleSimDao;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SampleSimServiceTest {
    private static final SampleRepository dao = new SampleSimDao();
    private static final SampleService service = new SampleSimService(dao);
    private static final SampleSimDatabase database = SampleSimDatabase.getInstance();

    @Test
    void createAll() {
        int before = service.findAll().size();
        Sample sample1 = new Sample("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        Sample sample2 = new Sample("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        List<Sample> samples = new ArrayList<>();
        samples.add(sample1);
        samples.add(sample2);

        service.createAll(SampleMapper.toRequestDtos(samples));
        int after = service.findAll().size();

        assertThat(after).isEqualTo(before + samples.size());
        database.displayTable(database.getSampleList());
    }

    @Test
    void create() {
        int before = service.findAll().size();
        Sample sample = new Sample("abcdefghijklmnopqrstuvwxyz");

        service.create(SampleMapper.toRequestDto(sample));
        int after = service.findAll().size();

        assertThat(after).isEqualTo(before + 1);
        database.displayTable(database.getSampleList());
    }

    @Test
    void findAll() {
        int before = service.findAll().size();
        service.create(SampleMapper.toRequestDto(new Sample("ABCDEFGHIJKLMNOPQRSTUVWXYZ")));

        int after = service.findAll().size();

        assertThat(after).isGreaterThan(before);
        database.displayTable(database.getSampleList());
    }

    @Test
    void findByText() {
        service.create(SampleMapper.toRequestDto(new Sample("ABCDEFGHIJKLMNOPQRSTUVWXYZ")));

        List<SampleResponseDto> results = service.findByText("ABCDEFGHIJKLMNOPQRSTUVWXYZ");

        for (SampleResponseDto item : results) {
            assertThat(item.getText()).isEqualTo("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        }
        database.displayTable(database.getSampleList());
    }

    @Test
    void find() {
        Sample sample = new Sample("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        SampleResponseDto entity = service.create(SampleMapper.toRequestDto(sample));

        SampleResponseDto result = service.find(entity.getId());

        assertThat(result.getId()).isEqualTo(entity.getId());
        database.displayTable(database.getSampleList());
    }

    @Test
    void update() {
        Sample sample = new Sample("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        Sample data = new Sample("abcdefghijklmnopqrstuvwxyz");
        SampleResponseDto entity = service.create(SampleMapper.toRequestDto(sample));

        SampleResponseDto result = service.update(entity.getId(), data);

        assertThat(result.getText()).isEqualTo(data.getText());
        database.displayTable(database.getSampleList());
    }

    @Test
    void delete() {
        Sample sample = new Sample("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        SampleResponseDto entity = service.create(SampleMapper.toRequestDto(sample));
        int before = service.findAll().size();

        service.delete(entity.getId());
        int after = service.findAll().size();

        assertThat(after).isLessThan(before);
        database.displayTable(database.getSampleList());
    }
}
