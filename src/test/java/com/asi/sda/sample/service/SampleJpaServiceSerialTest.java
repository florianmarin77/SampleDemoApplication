package com.asi.sda.sample.service;

import com.asi.sda.sample.repository.SampleJpaDao;
import com.asi.sda.sample.repository.SampleRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SampleJpaServiceSerialTest {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("MacroMedia");
    private static final EntityManager em = emf.createEntityManager();
    private static final SampleRepository dao = new SampleJpaDao(em);
    private static final SampleService service =  new SampleJpaService(dao);

    @Test
    @Order(1)
    void createAll() {
    }

    @Test
    @Order(2)
    void create() {
    }

    @Test
    @Order(3)
    void findAll() {
    }

    @Test
    @Order(4)
    void findByText() {
    }

    @Test
    @Order(5)
    void find() {
    }

    @Test
    @Order(6)
    void update() {
    }

    @Test
    @Order(7)
    void delete() {
    }
}
