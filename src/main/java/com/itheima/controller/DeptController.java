package com.itheima.controller;

import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
public class DeptController {
    @Autowired

    private DeptService deptService;

    @GetMapping("/depts")
    public Result list() {
        log.info("查询全部");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }

    @DeleteMapping("/depts")
    public Result delete(Integer id) {
        log.info("删除部门数据" + id);
        deptService.deleteById(id);
        return Result.success();
    }
    @PostMapping("/depts")
    public Result add(@RequestBody Dept dept) {
        log.info("增加一个"+dept+"部门");
        deptService.add(dept);
        return Result.success();
    }
    @GetMapping("/depts/{id}")
    public Result getInfo(@PathVariable Integer id) {
        log.info("回显id"+id);
        Dept dept=deptService.getidDept(id);
        return Result.success(dept);
    }
    @PutMapping("/depts")
    public Result update(@RequestBody Dept dept) {
        log.info("修改的部们"+dept);
        deptService.update(dept);
        return Result.success(dept);
    }

}
