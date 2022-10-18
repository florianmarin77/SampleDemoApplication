package com.lafi.sda.sample.repository;

import com.lafi.sda.sample.model.Sample;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SampleSpringRepository extends CrudRepository<Sample, Integer> {
    Sample findByText(String text);
}
