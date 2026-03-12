package com.itheima.controller;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RequestMapping("/emps")
@RestController
@Slf4j
public class EmpController {
    @Autowired
    private EmpService empService;
    @GetMapping
//    public Result page(@RequestParam(defaultValue = "1") Integer page,
//                       @RequestParam(defaultValue = "5")Integer pageSize,
//                        String name,Integer gender,
//                        @DateTimeFormat(pattern = "yyyy-mm-dd") LocalDate begin,
//                       @DateTimeFormat(pattern = "yyyy-mm-dd")LocalDate end)  {
//        log.info("分页查询,{},{},{},{},{},{}",page,pageSize,name,gender,begin,end);
//        PageResult<Emp>pageResult=empService.page(page,pageSize,name,gender,begin,end);
//        return Result.success(pageResult);
//    }
    public Result page(EmpQueryParam empQueryParam)  {
        log.info("分页查询,{}",empQueryParam);
        PageResult<Emp>pageResult=empService.page(empQueryParam);
        return Result.success(pageResult);
    }
    //新增员工
    @PostMapping
    public Result save(@RequestBody Emp emp) {
        log.info("add emp:{}",emp);
        empService.save(emp);
        return Result.success();
    }
    @DeleteMapping
    public Result delete(@RequestParam("ids") List<Integer> ids) {
        log.info("delete emp:{}",ids );
        empService.delete(ids);

        return Result.success();
    }
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id) {
        log.info("get emp:{}",id);
        Emp emp=empService.getInfo(id);
        return Result.success(emp);
    }
    @PutMapping()
    public Result update(@RequestBody Emp emp) {
        log.info("update emp:{}",emp);
        empService.update(emp);
        return Result.success();
    }
}
