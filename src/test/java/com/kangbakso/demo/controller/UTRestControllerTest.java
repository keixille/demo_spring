package com.kangbakso.demo.controller;

import com.kangbakso.demo.common.constant.UTConst;
import com.kangbakso.demo.service.UTService;
import com.kangbakso.demo.service.MongoDbService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import org.mockito.Mockito;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UTRestController.class)
public class UTRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UTService utService;

    @MockBean
    private MongoDbService mongoDbService;

    @Test
    public void getFoodTest() throws Exception {
        Mockito.when(utService.getFoods()).thenReturn("this is test");

        mockMvc.perform(MockMvcRequestBuilders.get("/food"))
                .andExpect(status().isOk())
                .andExpect(content().string("this is test"));
    }

    @Test
    public void getConstantTest() throws Exception {
        String resultString = "This is string : " + UTConst.MAX_STRING + "\n";
        String resultInt = "This is int : " + UTConst.MAX_INT + "\n";
        String resultLong = "This is long : " + UTConst.MAX_LONG + "\n";

        mockMvc.perform(MockMvcRequestBuilders.get("/constant"))
                .andExpect(status().isOk())
                .andExpect(content().string(resultString + resultInt + resultLong));
    }

    @Test
    public void getSwitchingTest() throws Exception {
        Mockito.when(utService.switching(0)).thenReturn("this is 0");

        mockMvc.perform(MockMvcRequestBuilders.get("/switching").param("num", String.valueOf(0)))
                .andExpect(status().isOk())
                .andExpect(content().string("this is 0"));
    }

    @Test
    public void getMongoTest() throws Exception {
        Mockito.when(utService.getFoodDocument()).thenReturn("this is document");

        mockMvc.perform(MockMvcRequestBuilders.get("/mongo"))
                .andExpect(status().isOk())
                .andExpect(content().string("this is document"));
    }

    @Test
    public void getValidConditionTest() throws Exception {
        Mockito.when(utService.getValidDocument()).thenReturn("this is valid document");

        mockMvc.perform(MockMvcRequestBuilders.get("/condition"))
                .andExpect(status().isOk())
                .andExpect(content().string("this is valid document"));
    }

    @Test
    public void getInvalidCondition1Test() throws Exception {
        Mockito.when(utService.getValidDocument()).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.get("/condition"))
                .andExpect(status().isOk())
                .andExpect(content().string("this is not valid document"));
    }

    @Test
    public void getInvalidCondition2Test() throws Exception {
        Mockito.when(utService.getValidDocument()).thenReturn("this is invalid document");

        mockMvc.perform(MockMvcRequestBuilders.get("/condition"))
                .andExpect(status().isOk())
                .andExpect(content().string("this is not valid document"));
    }
}
