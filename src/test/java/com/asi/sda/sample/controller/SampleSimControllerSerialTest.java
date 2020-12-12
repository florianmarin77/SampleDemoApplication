package com.asi.sda.sample.controller;

import com.asi.sda.sample.Sample;
import com.asi.sda.sample.SampleMapper;
import com.asi.sda.sample.SampleResponseDto;
import com.asi.sda.sample.database.SampleSimDatabase;
import com.asi.sda.sample.repository.SampleSimDao;
import com.asi.sda.sample.service.SampleService;
import com.asi.sda.sample.service.SampleSimService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SampleSimControllerSerialTest {
    private static final SampleSimDao dao = new SampleSimDao();
    private static final SampleService service = new SampleSimService(dao);
    private static final SampleSimController controller = new SampleSimController(service);
    private static final SampleSimDatabase database = SampleSimDatabase.getInstance();

    @Test
    @Order(1)
    void saveAllByFaker() {
        List<SampleResponseDto> responses = controller.saveAllByFaker();

        assertThat(responses.size()).isEqualTo(26);
        assertThat(responses.get(0).getId()).isEqualTo(1);
        database.displayTable(database.getSampleList());
    }

    @Test
    @Order(2)
    void saveAllByLoader() {
        List<SampleResponseDto> responses = controller.saveAllByLoader();

        assertThat(responses.size()).isEqualTo(26);
        assertThat(responses.get(0).getId()).isEqualTo(27);
        database.displayTable(database.getSampleList());
    }

    @Test
    @Order(3)
    void saveAll() {
        Sample sample1 = new Sample("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        Sample sample2 = new Sample("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        List<Sample> samples = new ArrayList<>();
        samples.add(sample1);
        samples.add(sample2);

        List<SampleResponseDto> responses = controller.saveAll(SampleMapper.toRequestDtos(samples));

        assertThat(responses.get(0).getId()).isEqualTo(53);
        assertThat(responses.get(1).getId()).isEqualTo(54);
        database.displayTable(database.getSampleList());
    }

    @Test
    @Order(4)
    void save() {
        Sample sample = new Sample("abcdefghijklmnopqrstuvwxyz");

        SampleResponseDto response = controller.save(SampleMapper.toRequestDto(sample));

        assertThat(response.getId()).isEqualTo(55);
        database.displayTable(database.getSampleList());
    }

    @Test
    @Order(5)
    void getById() {
        SampleResponseDto result = controller.getById(55);

        assertThat(result.getText()).isEqualTo("abcdefghijklmnopqrstuvwxyz");
        database.displayTable(database.getSampleList());
    }

    @Test
    @Order(6)
    void getAll() {
        List<SampleResponseDto> results = controller.getAll();

        assertThat(results).hasSize(55);
        database.displayTable(database.getSampleList());
    }

    @Test
    @Order(7)
    void getByText() {
        List<SampleResponseDto> results = controller.getByText("ABCDEFGHIJKLMNOPQRSTUVWXYZ");

        assertThat(results).hasSize(2);
        for (SampleResponseDto item : results) {
            assertThat(item.getText()).isEqualTo("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        }
        database.displayTable(database.getSampleList());
    }

    @Test
    @Order(8)
    void updateById() {
        Sample data = new Sample("abcdefghijklmnopqrstuvwxyz");

        SampleResponseDto result = controller.updateById(54, data);

        assertThat(result.getText()).isEqualTo("abcdefghijklmnopqrstuvwxyz");
        database.displayTable(database.getSampleList());
    }

    @Test
    @Order(9)
    void deleteById() {
        SampleResponseDto result = controller.deleteById(55);

        List<SampleResponseDto> results = controller.getAll();

        assertThat(results.size()).isEqualTo(54);
        assertThat(result.getId()).isEqualTo(55);
        database.displayTable(database.getSampleList());
    }
}
