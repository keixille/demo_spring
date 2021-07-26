package com.kangbakso.demoprometheus.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class WebController {
    private static final Logger LOGGER = LogManager.getLogger();

    @GetMapping("/greeting")
    public String greeting() {
        return "Welcome string";
    }

    @GetMapping("/wrk-url")
    public String wrkUrl(@RequestHeader("x-wrk") String header, @RequestParam String param) {
        LOGGER.info(header);
        LOGGER.info(param);
        return "Success";
    }
}
