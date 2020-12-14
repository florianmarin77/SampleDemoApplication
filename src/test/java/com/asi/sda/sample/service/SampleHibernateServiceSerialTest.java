package com.asi.sda.sample.service;

import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SampleHibernateServiceSerialTest {

    @AfterAll
    static void tearDown() {
    }

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
