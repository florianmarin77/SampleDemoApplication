package com.lafi.sda.sample.controller;

import com.lafi.sda.sample.model.Sample;
import com.lafi.sda.sample.model.SampleMapper;
import com.lafi.sda.sample.model.SampleResponseDto;
import com.lafi.sda.sample.database.SampleSimDatabase;
import com.lafi.sda.sample.repository.SampleSimDao;
import com.lafi.sda.sample.service.SampleService;
import com.lafi.sda.sample.service.SampleSimService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SampleSimControllerTest {
    private static final SampleSimDao dao = new SampleSimDao();
    private static final SampleService service = new SampleSimService(dao);
    private static final SampleSimController controller = new SampleSimController(service);
    private static final SampleSimDatabase database = SampleSimDatabase.getInstance();

    @Test
    void saveAllByFaker() {
        int before = controller.getAll().size();
        List<SampleResponseDto> responses = controller.saveAllByFaker();
        int after = controller.getAll().size();

        assertThat(after).isEqualTo(before + responses.size());
        database.displayTable(database.getSampleList());
    }

    @Test
    void saveAllByLoader() {
        int before = controller.getAll().size();
        List<SampleResponseDto> responses = controller.saveAllByLoader();
        int after = controller.getAll().size();

        assertThat(after).isEqualTo(before + responses.size());
        database.displayTable(database.getSampleList());
    }

    @Test
    void saveAll() {
        int before = controller.getAll().size();
        Sample sample1 = new Sample("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        Sample sample2 = new Sample("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        List<Sample> samples = new ArrayList<>();
        samples.add(sample1);
        samples.add(sample2);

        controller.saveAll(SampleMapper.toRequestDtos(samples));
        int after = controller.getAll().size();

        assertThat(after).isEqualTo(before + samples.size());
        database.displayTable(database.getSampleList());
    }

    @Test
    void save() {
        int before = controller.getAll().size();
        Sample sample = new Sample("ABCDEFGHIJKLMNOPQRSTUVWXYZ");

        controller.save(SampleMapper.toRequestDto(sample));
        int after = controller.getAll().size();

        assertThat(after).isEqualTo(before + 1);
        database.displayTable(database.getSampleList());
    }

    @Test
    void getById() {
        Sample sample = new Sample("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        SampleResponseDto entity = controller.save(SampleMapper.toRequestDto(sample));

        SampleResponseDto result = controller.getById(entity.getId());

        assertThat(result.getId()).isEqualTo(entity.getId());
        database.displayTable(database.getSampleList());
    }

    @Test
    void getAll() {
        int before = controller.getAll().size();
        controller.save(SampleMapper.toRequestDto(new Sample("ABCDEFGHIJKLMNOPQRSTUVWXYZ")));

        int after = controller.getAll().size();

        assertThat(after).isGreaterThan(before);
        database.displayTable(database.getSampleList());
    }

    @Test
    void getByText() {
        controller.save(SampleMapper.toRequestDto(new Sample("ABCDEFGHIJKLMNOPQRSTUVWXYZ")));

        List<SampleResponseDto> results = controller.getByText("ABCDEFGHIJKLMNOPQRSTUVWXYZ");

        for (SampleResponseDto item : results) {
            assertThat(item.getText()).isEqualTo("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        }
        database.displayTable(database.getSampleList());
    }

    @Test
    void updateById() {
        Sample sample = new Sample("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        Sample data = new Sample("abcdefghijklmnopqrstuvwxyz");
        SampleResponseDto entity = controller.save(SampleMapper.toRequestDto(sample));

        SampleResponseDto result = controller.updateById(entity.getId(), data);

        assertThat(result.getText()).isEqualTo(data.getText());
        database.displayTable(database.getSampleList());
    }

    @Test
    void deleteById() {
        Sample sample = new Sample("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        SampleResponseDto entity = controller.save(SampleMapper.toRequestDto(sample));
        List<SampleResponseDto> resultsBefore = controller.getAll();

        service.delete(entity.getId());
        List<SampleResponseDto> resultsAfter = controller.getAll();

        assertThat(resultsAfter.size()).isLessThan(resultsBefore.size());
        database.displayTable(database.getSampleList());
    }
}
