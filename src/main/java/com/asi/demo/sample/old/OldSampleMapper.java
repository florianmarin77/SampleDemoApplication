package com.asi.demo.sample.old;

import com.asi.demo.sample.Sample;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OldSampleMapper {

    // ========================================> entity to dto

    public OldSampleDto convertToDto(Sample entity) {
        OldSampleDto dto = new OldSampleDto();

        dto.setId(entity.getId());
        dto.setText(entity.getText());

        return dto;
    }

    public List<OldSampleDto> convertToDto(List<Sample> entities) {
        List<OldSampleDto> dtos = new ArrayList<>();

        for (Sample item : entities) {
            OldSampleDto dto = new OldSampleDto();
            dto.setId(item.getId());
            dto.setText(item.getText());
            dtos.add(dto);
        }

        return dtos;
    }

    public List<OldSampleDto> toDto(List<Sample> entities) {
        return entities.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // ========================================> dto to entity

    public Sample convertToEntity(OldSampleDto dto) {
        Sample entity = new Sample();

        entity.setId(dto.getId());
        entity.setText(dto.getText());

        return entity;
    }

    public List<Sample> convertToEntity(List<OldSampleDto> dtos) {
        List<Sample> entities = new ArrayList<>();

        for (OldSampleDto item : dtos) {
            Sample entity = new Sample();
            entity.setId(item.getId());
            entity.setText(item.getText());
            entities.add(entity);
        }

        return entities;
    }

    public List<Sample> toEntity(List<OldSampleDto> dtos) {
        return dtos.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());
    }
}
