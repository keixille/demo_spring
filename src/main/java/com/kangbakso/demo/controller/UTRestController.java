package com.kangbakso.demo.controller;

import com.kangbakso.demo.common.constant.UTConst;
import com.kangbakso.demo.service.MongoDbService;
import com.kangbakso.demo.service.UTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UTRestController {
    @Autowired
    private UTService utService;

    @Autowired
    private MongoDbService mongoDbService;

    @GetMapping("/food")
    public String getFood() {
        return utService.getFoods();
    }

    @GetMapping("/constant")
    public String getConstant() {
        String resultString = "This is string : " + UTConst.MAX_STRING + "\n";
        String resultInt = "This is int : " + UTConst.MAX_INT + "\n";
        String resultLong = "This is long : " + UTConst.MAX_LONG + "\n";
        return resultString + resultInt + resultLong;
    }

    @GetMapping("/switching")
    public String getSwitching(@RequestParam int num) {
        return utService.switching(num);
    }

    @GetMapping("/mongo")
    public String getMongo() {
        return utService.getFoodDocument();
    }

    @GetMapping("/condition")
    public String getCondition() {
        String validDocument = utService.getValidDocument();

        if (validDocument != null && validDocument == "this is valid document") return "this is valid document";

        return "this is not valid document";
    }
}
