package com.kangbakso.demo.controller;

import com.kangbakso.demo.common.constant.DummyConst;
import com.kangbakso.demo.common.util.SwitchService;
import com.kangbakso.demo.service.FoodService;
import com.kangbakso.demo.service.MongoDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UnitTestController {
    @Autowired
    private FoodService foodService;

    @Autowired
    private SwitchService switchService;

    @Autowired
    private MongoDbService mongoDbService;

    @GetMapping("/food")
    public String getFood() {
        return foodService.getFoods();
    }

    @GetMapping("/constant")
    public String getConstant() {
        String resultString = "This is string : " + DummyConst.MAX_STRING + "\n";
        String resultInt = "This is int : " + DummyConst.MAX_INT + "\n";
        String resultLong = "This is long : " + DummyConst.MAX_LONG + "\n";
        return resultString + resultInt + resultLong;
    }

    @GetMapping("/switching")
    public String getSwitching(@RequestParam int num) {
        return switchService.switching(num);
    }

    @GetMapping("/mongo")
    public String getMongo() {
        return mongoDbService.getFoodDocument();
    }
}
