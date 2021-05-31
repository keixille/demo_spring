package com.kangbakso.demo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UTServiceTests {
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
}