package com.kangbakso.demo.service;

import com.kangbakso.demo.common.util.SwitchService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SwitchServiceTests {
    @Autowired
    private SwitchService switchService;

    @Test
    public void switchServiceTest() {
        assertEquals("this is 0", switchService.switching(0));
        assertEquals("this is 1", switchService.switching(1));
        assertEquals("this is 2", switchService.switching(2));
        assertEquals("this is default", switchService.switching(3));
    }
}
