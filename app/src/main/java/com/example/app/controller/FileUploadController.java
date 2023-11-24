package com.example.app.controller;

import com.example.app.pojo.Result;
import com.example.app.utils.AliOssUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class FileUploadController {
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) throws Exception {
        String originalFilename = file.getOriginalFilename();
        String filename = UUID.randomUUID().toString()+originalFilename.substring(originalFilename.lastIndexOf("."));
//        file.transferTo(new File("D:\\school\\learn\\course\\java\\study\\java_code\\jpg\\"+originalFilename));
        String url = AliOssUtil.uploadFile(filename,file.getInputStream());
        return Result.success(url);
    }


}
