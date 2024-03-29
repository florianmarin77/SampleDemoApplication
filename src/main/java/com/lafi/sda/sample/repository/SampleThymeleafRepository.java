package com.lafi.sda.sample.repository;

import com.lafi.sda.sample.model.Sample;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SampleThymeleafRepository extends JpaRepository<Sample, Integer> {
    Sample findByText(String text);
}
