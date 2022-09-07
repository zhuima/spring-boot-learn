package com.zhuima.springbootapi.api;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogTestApi {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/log")
    public String log() {
//        logger.info("hello");
//        logger.warn("hello");
//        logger.error("hello");
//        logger.debug("hello");
//        logger.trace("hello");


        logger.info(">>>>>>>>>>>>>>. nothing <<<<<<<<<<<<<<<<<<");
        return "logtest";
    }
}
