package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.ClassMapper;
import com.itheima.pojo.*;
import com.itheima.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {
    @Autowired
    private ClassMapper classMapper;
  public  PageResult<Clazz> page(ClassQueryParam param){
      PageHelper.startPage(param.getPage(),param.getPageSize());
      List<Clazz> classList=classMapper.list(param);
      Page<Clazz> p=(Page<Clazz>) classList;
      return new PageResult<Clazz>(p.getTotal(),p.getResult());


  }
  public void deleteById(Integer id) {
      classMapper.delete(id);

  }
  public void save(Clazz clazz){
      clazz.setCreateTime(LocalDateTime.now());
      clazz.setUpdateTime(LocalDateTime.now());
      classMapper.saveClass(clazz);
  }
    public List<Clazz> getInfo(Integer id) {
    List<Clazz> list=classMapper.getByClassId(id);
    return list;
    }
    @Transactional(rollbackFor = Exception.class)
    public List<Clazz> update(Clazz clazz){
      clazz.setUpdateTime(LocalDateTime.now());
        int updated = classMapper.update(clazz);

        if (updated > 0) {
            // 查询更新后的数据
            return classMapper.getByClassId(clazz.getId());
        }
        return Collections.emptyList();
//        emp.setUpdateTime(LocalDateTime.now());
//        empMapper.updateById(emp);
//        //根据id删除exp
//        List<EmpExpr> exprList=emp.getExprList();
//        empExprMapper.deleteEmpIds(Arrays.asList(emp.getId()));
//        //再添加
//        empExprMapper.insertBatch(exprList);

    }
    public List<Clazz> searchAll(){
        List<Clazz> classList = classMapper.getAllClass();
        return  classList;
    }
}

