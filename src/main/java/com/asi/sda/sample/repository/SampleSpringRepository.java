package com.asi.sda.sample.repository;

import com.asi.sda.sample.model.Sample;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SampleSpringRepository extends CrudRepository<Sample, Integer> {
    // empty interface by default
}
