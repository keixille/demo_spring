package com.kangbakso.demo.controller;

import com.kangbakso.demo.common.constant.DummyConst;
import com.kangbakso.demo.common.util.SwitchService;
import com.kangbakso.demo.service.FoodService;
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

@WebMvcTest(UnitTestController.class)
public class UnitTestControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FoodService foodService;

    @MockBean
    private SwitchService switchService;

    @MockBean
    private MongoDbService mongoDbService;

    @Test
    public void getFoodTest() throws Exception {
        Mockito.when(foodService.getFoods()).thenReturn("this is test");

        mockMvc.perform(MockMvcRequestBuilders.get("/food"))
                .andExpect(status().isOk())
                .andExpect(content().string("this is test"));
    }

    @Test
    public void getConstantTest() throws Exception {
        String resultString = "This is string : " + DummyConst.MAX_STRING + "\n";
        String resultInt = "This is int : " + DummyConst.MAX_INT + "\n";
        String resultLong = "This is long : " + DummyConst.MAX_LONG + "\n";

        mockMvc.perform(MockMvcRequestBuilders.get("/constant"))
                .andExpect(status().isOk())
                .andExpect(content().string(resultString + resultInt + resultLong));
    }

    @Test
    public void getSwitchingTest() throws Exception {
        Mockito.when(switchService.switching(0)).thenReturn("this is 0");

        mockMvc.perform(MockMvcRequestBuilders.get("/switching").param("num", String.valueOf(0)))
                .andExpect(status().isOk())
                .andExpect(content().string("this is 0"));
    }

    @Test
    public void getMongoTest() throws Exception {
        Mockito.when(mongoDbService.getFoodDocument()).thenReturn("this is document");

        mockMvc.perform(MockMvcRequestBuilders.get("/mongo"))
                .andExpect(status().isOk())
                .andExpect(content().string("this is document"));
    }
}
