package com.itheima;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class  WebAiProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebAiProjectApplication.class, args);
    }

}
