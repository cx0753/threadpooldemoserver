package com.example.threadpooldemoserver.controller;

import com.example.threadpooldemoserver.service.AsyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private AsyncService asyncService;

    @RequestMapping("/")
    public String submit() {
        logger.info("---Controller Start submit---");
        asyncService.executeAsync();
        logger.info("---Controller End submit---");
        return "success";
    }
}
