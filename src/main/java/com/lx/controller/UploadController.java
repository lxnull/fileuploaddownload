package com.lx.controller;

import com.lx.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UploadController {

    @Autowired
    private UploadService service;

    @PostMapping("/uploadfile")
    public String upload(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest request) {
        if (multipartFile.isEmpty()) {
            return "文件有误";
        }

        // 得到文件夹名，做隔离。
        String dir = request.getParameter("dir");

        return service.upload(multipartFile, dir);
    }

    @PostMapping("/uploadfilemap")
        public Map<String,Object> uploadmap(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest request) {
        if (multipartFile.isEmpty()) {
            return (Map<String, Object>) new HashMap<String,Object>().put("error","文件有误");
        }

        // 得到文件夹名，做隔离。
        String dir = request.getParameter("dir");

        return service.uploadMap(multipartFile, dir);
    }
}
