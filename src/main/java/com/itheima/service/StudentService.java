package com.itheima.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;

import java.util.List;
import java.util.Map;

public interface StudentService {
    PageResult<Student> searchByPage(StudentQueryParam param);

    void addStudent(Student student);


    void deleteById(List<Integer> ids);

    List<Student> getById(String id);

    void updateStudent(Student student);

    void updateViolationScore(String id, String score);

    Map<String, Object> countStudentsByClass();


    List<Map<String, Object>> countStudentsByDegree();
}
