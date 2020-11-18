package com.asi.demo.sample;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SampleMapper {

    // ========================================> entity to request dto

    public static SampleRequestDto toRequestDto(Sample entity) {
        SampleRequestDto request = new SampleRequestDto();
        request.setText(entity.getText());
        return request;
    }

    public static List<SampleRequestDto> toRequestDto(List<Sample> entities) {
        List<SampleRequestDto> requests = new ArrayList<>();
        for (Sample item : entities) {
            SampleRequestDto request = new SampleRequestDto();
            request.setText(item.getText());
            requests.add(request);
        }
        return requests;
    }

    public static List<SampleRequestDto> toRequestDtos(List<Sample> entities) {
        return entities.stream()
                .map(SampleMapper::toRequestDto)
                .collect(Collectors.toList());
    }

    // ========================================> entity to response dto

    public static SampleResponseDto toResponseDto(Sample entity) {
        SampleResponseDto response = new SampleResponseDto();
        response.setId(entity.getId());
        response.setText(entity.getText());
        return response;
    }

    public static List<SampleResponseDto> toResponseDto(List<Sample> entities) {
        List<SampleResponseDto> responses = new ArrayList<>();
        for (Sample item : entities) {
            SampleResponseDto response = new SampleResponseDto();
            response.setId(item.getId());
            response.setText(item.getText());
            responses.add(response);
        }
        return responses;
    }

    public static List<SampleResponseDto> toResponseDtos(List<Sample> entities) {
        return entities.stream()
                .map(SampleMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    // ========================================> request dto to entity

    public static Sample toEntity(SampleRequestDto request) {
        Sample entity = new Sample();
        entity.setText(request.getText());
        return entity;
    }

    public static List<Sample> toEntity(List<SampleRequestDto> requests) {
        List<Sample> entities = new ArrayList<>();
        for (SampleRequestDto item : requests) {
            Sample entity = new Sample();
            entity.setText(item.getText());
            entities.add(entity);
        }
        return entities;
    }

    public static List<Sample> toEntities(List<SampleRequestDto> requests) {
        return requests.stream()
                .map(SampleMapper::toEntity)
                .collect(Collectors.toList());
    }
}
