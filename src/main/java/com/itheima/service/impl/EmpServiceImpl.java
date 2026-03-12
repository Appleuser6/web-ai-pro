package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpExprMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.*;
import com.itheima.service.EmpLogService;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
@Slf4j
@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;
    @Autowired
    private EmpLogService empLogService;
//    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize) {
//        Long total = empMapper.count();
//        //调用mapper
//       Integer start = (page-1)*pageSize;
//        List<Emp>rows= empMapper.list(start,pageSize);
//
//        return new PageResult<Emp>(total,rows);
//    }
@Override
//pagehelper
public PageResult<Emp> page(EmpQueryParam empQueryParam) {
    PageHelper.startPage(empQueryParam.getPage(),empQueryParam.getPageSize());
    List<Emp> empList=empMapper.list(empQueryParam);
    Page<Emp> p=(Page<Emp>) empList;
    return new PageResult<Emp>(p.getTotal(),p.getResult());

}
    @Transactional
    @Override
    public void save(Emp emp) {

        try {
            LocalDateTime now = LocalDateTime.now();
            emp.setCreateTime(now);
            emp.setUpdateTime(now);
            empMapper.insert(emp);


            List<EmpExpr> exprList=emp.getExprList();
            if(!exprList.isEmpty()){
                exprList.forEach(expr->{
                    expr.setEmpId(emp.getId());
                });
                empExprMapper.insertBatch(exprList);
            }
        } finally {
            EmpLog empLog=new EmpLog(null,LocalDateTime.now(),"add"+emp);
            empLogService.insertLog(empLog);

        }

    }

    public void delete(List<Integer> ids){
    empMapper.deleteByIds(ids);
    empExprMapper.deleteEmpIds(ids);
    }
    public Emp getInfo(Integer id){
    return empMapper.getById(id);
    }
    @Transactional(rollbackFor = Exception.class)
    public void update(Emp emp){
    //根据id修改emp
    emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);
        //根据id删除exp
        List<EmpExpr> exprList=emp.getExprList();
        empExprMapper.deleteEmpIds(Arrays.asList(emp.getId()));
        //再添加
        empExprMapper.insertBatch(exprList);
    }
    public  List<Map<String, Object>> empGender(){
    return empMapper.countGender();


    }
    public  Map<String, Object> empJob(){
        try {
            log.info("开始查询职位分布");
            List<Map<String,Object>> result=empMapper.countJob();
            log.info("数据库查询结果: {}", result);
            List<String> jobList=new ArrayList<>();
            List<Object> dataList=new ArrayList<>();
            for(Map<String,Object> map:result){
                log.info("处理map: {}", map);
             String jobName=map.get("jobName").toString();
             if (jobName==null){
                 continue;
             }
                Long countLong = (Long) map.get("count");
                int sum = countLong.intValue();
             jobList.add(jobName);
             dataList.add(sum);
            }
            Map<String,Object> resultMap=new HashMap<>();
            resultMap.put("jobList",jobList);
            resultMap.put("dataList",dataList);
            log.info("最终返回结果: {}", resultMap);
            return resultMap;
        } catch (Exception e) {
            log.error("empJob方法异常:", e);
            throw new RuntimeException(e);
        }
    }

}
