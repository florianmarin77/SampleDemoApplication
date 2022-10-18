package com.lafi.sda.sample.demo.service;


import com.lafi.sda.sample.demo.model.Single;
import com.lafi.sda.sample.demo.model.SingleMapper;
import com.lafi.sda.sample.demo.model.SingleRequestDto;
import com.lafi.sda.sample.demo.model.SingleResponseDto;
import com.lafi.sda.sample.demo.repository.Repository;

import java.util.List;

public class SingleService implements Service {
    private static final String SOURCE = "SERVICE => "; // logger

    private final Repository repository;

    public SingleService(Repository repository) {
        this.repository = repository;
    }

    @Override
    public List<SingleResponseDto> createAll(List<SingleRequestDto> requests) {
        System.out.println(SOURCE + "CREATE/all"); // logger

        List<Single> singles = SingleMapper.toEntities(requests); // input

        List<Single> entities = repository.createAll(singles); // ready

        return SingleMapper.toResponses(entities); // output
    }

    @Override
    public SingleResponseDto create(SingleRequestDto request) {
        System.out.println(SOURCE + "CREATE"); // logger

        Single single = SingleMapper.toEntity(request); // input

        Single entity = repository.create(single); // ready

        return SingleMapper.toResponse(entity); // output
    }

    @Override
    public List<SingleResponseDto> findAll() {
        System.out.println(SOURCE + "READ/all"); // logger

        List<Single> entities = repository.findAll(); // ready

        return SingleMapper.toResponses(entities); // output
    }

    @Override
    public List<SingleResponseDto> findByText(String text) {
        System.out.println(SOURCE + "READ/byText"); // logger

        List<Single> entities = repository.findByText(text); // ready

        return SingleMapper.toResponses(entities); // output
    }

    @Override
    public SingleResponseDto find(Integer id) {
        System.out.println(SOURCE + "READ"); // logger

        // ready only if exists
        Single entity = repository.find(id)
                .orElseThrow(() -> new RuntimeException("ERROR: Item not found!"));

        return SingleMapper.toResponse(entity); // output if exists
    }

    @Override
    public SingleResponseDto update(Integer id, Single data) {
        System.out.println(SOURCE + "UPDATE"); // logger

        // ready only if exists
        Single entity = repository.update(id, data)
                .orElseThrow(() -> new RuntimeException("ERROR: Item not found!"));

        return SingleMapper.toResponse(entity); // output if exists
    }

    @Override
    public SingleResponseDto delete(Integer id) {
        System.out.println(SOURCE + "DELETE"); // logger

        // ready only if exists
        Single entity = repository.delete(id).
                orElseThrow(() -> new RuntimeException("ERROR: Item not found!"));

        return SingleMapper.toResponse(entity); // output if exists
    }
}
