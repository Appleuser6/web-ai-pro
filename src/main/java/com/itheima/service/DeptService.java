package com.itheima.service;

import com.itheima.pojo.Dept;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DeptService {

    List<Dept> findAll();

    void deleteById(Integer id);

    void add(Dept dept);

    Dept getidDept(Integer id);

    void update(Dept dept);
}
