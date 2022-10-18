package com.lafi.sda.sample.demo.model;

import java.util.ArrayList;
import java.util.List;

public class SingleMapper {

    // entity to request dto

    public static SingleRequestDto toRequest(Single entity) {
        SingleRequestDto request = new SingleRequestDto();
        request.setText(entity.getText());
        return request;
    }

    public static List<SingleRequestDto> toRequests(List<Single> entities) {
        List<SingleRequestDto> requests = new ArrayList<>();
        for (Single item : entities) {
            SingleRequestDto request = new SingleRequestDto();
            request.setText(item.getText());
            requests.add(request);
        }
        return requests;
    }

    // entity to response dto

    public static SingleResponseDto toResponse(Single entity) {
        SingleResponseDto response = new SingleResponseDto();
        response.setId(entity.getId());
        response.setText(entity.getText());
        return response;
    }

    public static List<SingleResponseDto> toResponses(List<Single> entities) {
        List<SingleResponseDto> responses = new ArrayList<>();
        for (Single item : entities) {
            SingleResponseDto response = new SingleResponseDto();
            response.setId(item.getId());
            response.setText(item.getText());
            responses.add(response);
        }
        return responses;
    }

    // request dto to entity

    public static Single toEntity(SingleRequestDto request) {
        Single entity = new Single();
        entity.setText(request.getText());
        return entity;
    }

    public static List<Single> toEntities(List<SingleRequestDto> requests) {
        List<Single> entities = new ArrayList<>();
        for (SingleRequestDto item : requests) {
            Single entity = new Single();
            entity.setText(item.getText());
            entities.add(entity);
        }
        return entities;
    }
}
