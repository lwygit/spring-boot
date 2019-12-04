package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/tvseries")
public class TVSeriesController {

    @GetMapping
    public Map<String,Object> sayHello() {
        Map<String,Object> restful = new HashMap<>();
        restful.put("message","Hello World");
        return restful;
    }
}
