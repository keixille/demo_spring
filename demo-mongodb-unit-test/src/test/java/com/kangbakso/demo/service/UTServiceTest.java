package com.kangbakso.demo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UTServiceTest {
    @Autowired
    private UTService utService;

    @Test
    public void foodServiceTest() {
        assertEquals("this is food", utService.getFoods());
    }

    @Test
    public void switchServiceTest() {
        assertEquals("this is 0", utService.switching(0));
        assertEquals("this is 1", utService.switching(1));
        assertEquals("this is 2", utService.switching(2));
        assertEquals("this is default", utService.switching(3));
    }

    @Test
    public void getRandomTest() {
        assertNotNull(utService.getRandom());
    }

    @Test
    public void findRandomTest() {
        assertTrue(utService.findRandom("test random"));
    }
}
