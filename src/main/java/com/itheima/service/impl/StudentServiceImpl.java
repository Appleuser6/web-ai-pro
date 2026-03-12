package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.mapper.StudentMapper;
import com.itheima.pojo.*;
import com.itheima.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    public PageResult<Student> searchByPage(StudentQueryParam param) {
        PageHelper.startPage(param.getPage(), param.getPageSize());
        List<Student> list = studentMapper.getByPage(param);
        Page<Student> page = (Page<Student>) list;
        return new PageResult<Student>(page.getTotal(), page.getResult());
    }

    public void addStudent(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.add(student);
        return;

    }

    public void deleteById(List<Integer> ids) {
        studentMapper.deleteStudent(ids);
        return;

    }

    public List<Student> getById(String id) {
        return studentMapper.getByid(id);
    }

    public void updateStudent(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.updateStu(student);
        return;
    }

    public void updateViolationScore(String id, String score) {
        studentMapper.setViolationScore(id, score);
        return;
    }

    public Map<String, Object> countStudentsByClass() {
        List<Map<String, Object>> classStats = studentMapper.countStudentsGroupByClass();
        List<String> clazzList = new ArrayList<>();
        List<Integer> dataList = new ArrayList<>();

        for (Map<String, Object> stat : classStats) {
            String className = (String) stat.get("className");
            if (className == null) {
                className = "未分配班级";
            }

            Long count = (Long) stat.get("studentCount");
            if (count == null) {
                count = 0L;
            }

            clazzList.add(className);
            dataList.add(count.intValue());
        }

        Map<String, Object> result = new HashMap<>();
        result.put("clazzList", clazzList);
        result.put("dataList", dataList);
        return result;
    }
    public List<Map<String, Object>> countStudentsByDegree(){
        // 1. 查询数据库
        List<Map<String, Object>> degreeStats = studentMapper.countStudentsGroupByDegree();
        // 2. 学历编码映射
        Map<Integer, String> degreeMap = new HashMap<>();
        degreeMap.put(1, "初中");
        degreeMap.put(2, "高中");
        degreeMap.put(3, "大专");
        degreeMap.put(4, "本科");
        degreeMap.put(5, "硕士");
        degreeMap.put(6, "博士");
        // 3. 转换格式
        List<Map<String, Object>> result = new ArrayList<>();

        for (Map<String, Object> stat : degreeStats) {
            Integer degreeCode = (Integer) stat.get("degree");
            Long count = (Long) stat.get("count");

            if (degreeCode != null && degreeMap.containsKey(degreeCode)) {
                Map<String, Object> item = new HashMap<>();
                item.put("name", degreeMap.get(degreeCode));
                item.put("value", count != null ? count.intValue() : 0);
                result.add(item);
            }
        }

        return result;
    }


}
