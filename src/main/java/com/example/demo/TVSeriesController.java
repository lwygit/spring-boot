package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/tvseries")
public class TVSeriesController {

    @GetMapping
    public List<TVSeriesDto> sayHello() {
        List<TVSeriesDto> list = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2019,Calendar.DECEMBER,4,17,13,0);
        list.add(new TVSeriesDto(1,"name",1,calendar.getTime()));
        list.add(new TVSeriesDto(2,"name",1,calendar.getTime()));
        return list;
    }
}
