package com.asi.sda.sample.old;

import com.asi.sda.sample.model.Sample;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SampleOldMapper {

    // entity to dto

    public SampleOldDto toDto(Sample entity) {
        SampleOldDto dto = new SampleOldDto();
        dto.setId(entity.getId());
        dto.setText(entity.getText());
        return dto;
    }

    public List<SampleOldDto> toDto(List<Sample> entities) {
        List<SampleOldDto> dtos = new ArrayList<>();
        for (Sample item : entities) {
            SampleOldDto dto = new SampleOldDto();
            dto.setId(item.getId());
            dto.setText(item.getText());
            dtos.add(dto);
        }
        return dtos;
    }

    public List<SampleOldDto> toDtos(List<Sample> entities) {
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // dto to entity

    public Sample toEntity(SampleOldDto dto) {
        Sample entity = new Sample();
        entity.setId(dto.getId());
        entity.setText(dto.getText());
        return entity;
    }

    public List<Sample> toEntity(List<SampleOldDto> dtos) {
        List<Sample> entities = new ArrayList<>();
        for (SampleOldDto item : dtos) {
            Sample entity = new Sample();
            entity.setId(item.getId());
            entity.setText(item.getText());
            entities.add(entity);
        }
        return entities;
    }

    public List<Sample> toEntities(List<SampleOldDto> dtos) {
        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
