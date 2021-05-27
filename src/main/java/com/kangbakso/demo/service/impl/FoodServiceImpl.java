package com.kangbakso.demo.service.impl;

import com.kangbakso.demo.service.FoodService;
import org.springframework.stereotype.Service;

@Service
public class FoodServiceImpl implements FoodService {
    public String getFoods() {
        return "this is food";
    }
}
