package com.asi.sda.sample.service;

import com.asi.sda.sample.repository.SampleJpaDao;
import com.asi.sda.sample.repository.SampleRepository;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.*;

class SampleJpaServiceTest {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("MacroMedia");
    private static final EntityManager em = emf.createEntityManager();
    private static final SampleRepository dao = new SampleJpaDao(em);
    private static final SampleService service =  new SampleJpaService(dao);

    @Test
    void createAll() {
    }

    @Test
    void create() {
    }

    @Test
    void findAll() {
    }

    @Test
    void findByText() {
    }

    @Test
    void find() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}
