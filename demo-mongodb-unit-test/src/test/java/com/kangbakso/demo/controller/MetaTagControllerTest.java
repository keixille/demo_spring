package com.kangbakso.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MetaTagController.class)
public class MetaTagControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void metatagTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/metatag"))
                .andExpect(status().isOk());
    }
}
