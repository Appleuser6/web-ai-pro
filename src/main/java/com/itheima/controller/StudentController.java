package com.itheima.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.itheima.mapper.StudentMapper;
import com.itheima.pojo.*;
import com.itheima.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @GetMapping
    public ClassResult getClassList(StudentQueryParam param) {
        log.info("传入的参数student={}", param);
        PageResult<Student> pageInfo =studentService.searchByPage(param);
        return ClassResult.success(pageInfo);


    }
    @PostMapping
    public ClassResult saveStudent( @RequestBody Student student) {
        log.info("添加的学生student={}", student);
        studentService.addStudent(student);
        return ClassResult.success();
    }
    @DeleteMapping("/{ids}")
    public ClassResult deleteStudent(@PathVariable String ids) {
        log.info("删除的学生id={}", ids);

        // 一行验证
        if (ids == null || ids.isBlank()) {
            return ClassResult.error("请选择要删除的学生");
        }

        try {
            studentService.deleteById(
                    Arrays.stream(ids.split(","))
                            .map(String::trim)
                            .filter(s -> !s.isEmpty())
                            .map(Integer::parseInt)
                            .collect(Collectors.toList())
            );
            return ClassResult.success();
        } catch (NumberFormatException e) {
            return ClassResult.error("ID格式错误");
        }
    }
    @GetMapping("/{id}")
    public ClassResult getStudent(@PathVariable String id) {
        List<Student> getlist=studentService.getById(id);
        return ClassResult.success(getlist);
    }
    @PutMapping
    public ClassResult updateStudent( @RequestBody Student student) {
        studentService.updateStudent(student);
        return ClassResult.success();
    }
    @PutMapping("/violation/{id}/{score}")
    public ClassResult updateStudentViolation(@PathVariable String id, @PathVariable String score) {
        log.info("修改的id={},score={}", id, score);
        if (id == null || id.isBlank()) {
            return ClassResult.error("不完整");
        }
        studentService.updateViolationScore(id,score);
        return ClassResult.success();
    }
    }
