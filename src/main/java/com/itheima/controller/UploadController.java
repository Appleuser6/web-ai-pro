package com.itheima.controller;

import com.itheima.pojo.Result;
import com.itheima.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@Slf4j

public class UploadController {
    @Autowired
    private AliyunOSSOperator aliyunOSSOperatoro;
//    @PostMapping("/upload")
//    public Result upload(String name,Integer age,@RequestParam("file") MultipartFile file)throws IOException {
//        log.info("接收参数: {},{},{}", name, age, file);
//        // 获取原始文件名
//        String originalFilename = file.getOriginalFilename(); // 例如：1.jpg 或 image.png
//        // 获取文件扩展名
//        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
//        // 生成新的文件名（使用UUID避免重复）
//        String newFileName = UUID.randomUUID().toString() + extension;
//        // 确保目录存在
//        File directory = new File("D:/images/");
//        if (!directory.exists()) {
//            directory.mkdirs();
//        }
//        // 保存文件到本地
//        String filePath = "D:/images/" + newFileName;
//        file.transferTo(new File(filePath));
//
//        log.info("文件保存成功: {}", filePath);
//        return Result.success();
//    }
    @PostMapping("/upload")
    public Result upload2(@RequestParam("file") MultipartFile file) throws Exception {
        log.info("image:{}", file.getOriginalFilename());
        String originalFilename = file.getOriginalFilename();
        String url= aliyunOSSOperatoro.upload(file.getBytes(),originalFilename);
        log.info("url:{}", url);
        return Result.success(url);



    }
}
