package com.itheima.service;

import com.itheima.pojo.ClassQueryParam;
import com.itheima.pojo.Clazz;
import com.itheima.pojo.PageResult;

import java.util.List;

public interface ClassService {
    PageResult<Clazz> page(ClassQueryParam param);

    void deleteById(Integer id);

    void save(Clazz clazz);

    List<Clazz> getInfo(Integer id);

    List<Clazz> update(Clazz clazz);

    List<Clazz> searchAll();
}
