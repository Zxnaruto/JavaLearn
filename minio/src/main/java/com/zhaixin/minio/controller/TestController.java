package com.zhaixin.minio.controller;

import com.zhaixin.minio.utils.MinioUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Program: minio
 * @Classname: TestController
 * @Author:  Zhai
 * @Description:
 * @Date: 2021/07/07 18:49
 */
@RestController
public class TestController {
    @Autowired
    private MinioUtils minioUtils;
    @Value("${minio.bucketName}")
    private String BUCKET_NAME;

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) throws Exception {
        return minioUtils.uploadFile(file,BUCKET_NAME);
    }

    @GetMapping("/download")
    public String download(@RequestParam("url") String url, HttpServletResponse response) throws IOException {
        minioUtils.downloadFile(url, BUCKET_NAME ,response);
        return "success";
    }

    @GetMapping("/url")
    public String url(@RequestParam("url") String url) throws IOException {
        return minioUtils.getDownloadUrl(url,BUCKET_NAME);
    }
}

