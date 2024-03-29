package com.example.demo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.commons.io.IOUtils;
//import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
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
            log.trace("新增的数据" + tvSeriesDto);
        }
        tvSeriesDto.setId(12);
        return tvSeriesDto;
    }

    @PutMapping("/{id}")
    public TVSeriesDto updateOne(@PathVariable int id, @RequestBody TVSeriesDto tvSeriesDto) {
        if (log.isTraceEnabled()) {
            log.trace("更新的数据" + tvSeriesDto);
        }
        if (id == 101 || id == 102) {
            return createPoi();
        } else {
            throw new ResourceNotFoundException();
        }

    }

    @DeleteMapping("/{id}")
    public Map<String, String> DeleteOne(@PathVariable int id, HttpServletRequest request,
                                         @RequestParam(value = "delete_reason", required = false) String deleteReason) throws Exception {
        Map<String, String> result = new HashMap();
        if (log.isTraceEnabled()) {
            log.trace("删除的数据" + id);
        }
        if (id == 101) {
            result.put("message", "#101被" + request.getRemoteAddr() + "删除（原因：" + deleteReason + ")");
        } else if (id == 102) {
            throw new RuntimeException("#102不能被删除");
        } else {
            throw new ResourceNotFoundException();
        }
        return result;
    }

    @PostMapping(value = "/{id}/photos", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void addPhoto(@PathVariable int id, @RequestParam("photo") MultipartFile imgFile) throws Exception {
        if (log.isTraceEnabled()) {
            log.trace("接收的文件" + id + "收到的文件" + imgFile.getOriginalFilename());
        }
        FileOutputStream fos = new FileOutputStream("target/" + imgFile.getOriginalFilename());
        IOUtils.copy(imgFile.getInputStream(), fos);
        fos.close();
    }

    @GetMapping(value="/{id}/icon",produces=MediaType.IMAGE_JPEG_VALUE)
    public byte[] getIcon(@PathVariable int id) throws Exception {
        if(log.isTraceEnabled()) {
            log.trace("getIcon("+id+")");
        }
        String iconFile = "poi.jpg";
        InputStream is = new FileInputStream(iconFile);
        return IOUtils.toByteArray(is);
    }

}
