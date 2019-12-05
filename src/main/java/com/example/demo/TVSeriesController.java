package com.example.demo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/tvseries")
public class TVSeriesController {
    private static final Log log = LogFactory.getLog(TVSeriesController.class);

    @GetMapping
    public List<TVSeriesDto> sayHello() {
        if (log.isWarnEnabled()) {
            log.trace("sayHello()，被调用了！");
        }
        List<TVSeriesDto> list = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2019, Calendar.DECEMBER, 4, 17, 13, 0);
        list.add(new TVSeriesDto(1, "lwy", 1, calendar.getTime()));
        list.add(new TVSeriesDto(2, "liangwenyuan", 1, calendar.getTime()));
        return list;
    }

    @GetMapping("/{id}")
    public TVSeriesDto getOne(@PathVariable int id) {
        if (log.isTraceEnabled()) {
            log.trace("getOne() id=" + id);
        }
        if (id == 101) {
            return createWestWorld();
        } else if (id == 102) {
            return createPoi();
        } else {
            throw new ResourceNotFoundException();
        }


    }

    private TVSeriesDto createWestWorld() {
        Calendar c = Calendar.getInstance();
        c.set(2011, Calendar.SEPTEMBER, 22, 0, 0, 0);
        return new TVSeriesDto(101, "West world", 5, c.getTime());
    }

    private TVSeriesDto createPoi() {
        Calendar c = Calendar.getInstance();
        c.set(2016, Calendar.OCTOBER, 22, 0, 0, 0);
        return new TVSeriesDto(102, "poi", 5, c.getTime());
    }

    @PostMapping
    public TVSeriesDto insertOne(@RequestBody TVSeriesDto tvSeriesDto) {
        if (log.isTraceEnabled()) {
            log.trace("新增的数据"+tvSeriesDto);
        }
        tvSeriesDto.setId(9999);
        return tvSeriesDto;
    }


}
