package com.asi.sda.sample.old;

import com.asi.sda.sample.Sample;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OldSampleMapper {

    // entity to dto

    public OldSampleDto toDto(Sample entity) {
        OldSampleDto dto = new OldSampleDto();
        dto.setId(entity.getId());
        dto.setText(entity.getText());
        return dto;
    }

    public List<OldSampleDto> toDto(List<Sample> entities) {
        List<OldSampleDto> dtos = new ArrayList<>();
        for (Sample item : entities) {
            OldSampleDto dto = new OldSampleDto();
            dto.setId(item.getId());
            dto.setText(item.getText());
            dtos.add(dto);
        }
        return dtos;
    }

    public List<OldSampleDto> toDtos(List<Sample> entities) {
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // dto to entity

    public Sample toEntity(OldSampleDto dto) {
        Sample entity = new Sample();
        entity.setId(dto.getId());
        entity.setText(dto.getText());
        return entity;
    }

    public List<Sample> toEntity(List<OldSampleDto> dtos) {
        List<Sample> entities = new ArrayList<>();
        for (OldSampleDto item : dtos) {
            Sample entity = new Sample();
            entity.setId(item.getId());
            entity.setText(item.getText());
            entities.add(entity);
        }
        return entities;
    }

    public List<Sample> toEntities(List<OldSampleDto> dtos) {
        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
