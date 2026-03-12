package com.itheima.controller;

import com.itheima.pojo.ClassResult;
import com.itheima.pojo.Student;
import com.itheima.service.EmpService;
import com.itheima.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/report")
public class ReportController {
    @Autowired
    private EmpService empService;
    @Autowired
    private StudentService studentService;
    @GetMapping("/studentCountData")
    public ClassResult studentCountData() {
        log.info("获取各班级学生人数统计");

        try {
            // 调用Service获取统计结果
            Map<String, Object> result = studentService.countStudentsByClass();

            return ClassResult.success(result);
        } catch (Exception e) {
            log.error("获取学生统计报表失败", e);
            return ClassResult.error("获取统计信息失败");


    }


        }
        @GetMapping("/studentDegreeData")
        public ClassResult getDegreeDistribution() {
            log.info("获取学生学历分布统计");

            try {
                List<Map<String, Object>> result = studentService.countStudentsByDegree();
                return ClassResult.success(result);
            } catch (Exception e) {
                log.error("获取学历分布统计失败", e);
                return ClassResult.error("获取统计信息失败");
            }
        }
        @GetMapping("/empGenderData")
    public ClassResult getGenderDistribution() {
        log.info("获取员工性别统计");
            try {
                List<Map<String,Object>> result= empService.empGender();
                return ClassResult.success(result);
            } catch (Exception e) {
                log.info("获取员工性别统计有问题");
                return ClassResult.error("获取员工性别统计有问题");
            }
        }
        @GetMapping("/empJobData")
    public ClassResult getJobDistribution() {
        log.info("获取员职位统计");
        try {
            Map<String,Object> result =empService.empJob();
            log.info("获取成功，结果: {}", result);
            return ClassResult.success(result);
        }catch (Exception e){
            log.error("获取员工职位统计失败，异常信息:", e);
            return ClassResult.error("获取员工职位有问题");
        }
        }
    }

