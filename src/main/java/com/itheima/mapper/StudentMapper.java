package com.itheima.mapper;

import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {
    List<Student> getByPage(StudentQueryParam param);

    void add(Student student);

    void deleteStudent(List<Integer> ids);

    List<Student> getByid(String id);

    void updateStu(Student student);

    void setViolationScore(String id, String score);


    List<Map<String, Object>> countStudentsGroupByClass();


    List<Map<String, Object>> countStudentsGroupByDegree();
}
