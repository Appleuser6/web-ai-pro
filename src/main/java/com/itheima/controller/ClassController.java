package com.itheima.controller;

import com.itheima.pojo.ClassQueryParam;
import com.itheima.pojo.ClassResult;
import com.itheima.pojo.Clazz;
import com.itheima.pojo.PageResult;
import com.itheima.service.ClassService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
    @RequestMapping("/clazzs")
public class ClassController {
    @Autowired
   private ClassService classService;
    @GetMapping
    public ClassResult getClasses(ClassQueryParam param) {

        log.info("班级列表查询，参数：name={}", param);
        PageResult<Clazz> pageResult= classService.page(param);
        return ClassResult.success(pageResult);

    }
    @DeleteMapping("/{id}")
    public ClassResult deleteClass(@PathVariable Integer id) {
        log.info("删除班级请求，ID参数值: {}", id);
        if (id == null) {
            log.error("删除失败：ID为空");
            return ClassResult.error("删除失败：班级ID不能为空");
        }
        classService.deleteById(id);
        return ClassResult.success();
    }
    @PostMapping
    public ClassResult addClass(@RequestBody Clazz clazz) {
        log.info("添加的班级: {}", clazz);
        classService.save(clazz);
        return ClassResult.success();

    }
    @GetMapping("/{id}")
    public ClassResult getClass(@PathVariable Integer id) {
        List<Clazz> getclass=classService.getInfo(id);
        if(getclass.size()==0) {
            return ClassResult.success("该用户不存在~");
        }
        return ClassResult.success(getclass);
    }
    @PutMapping
    public ClassResult updateClass(@RequestBody Clazz clazz) {
        List<Clazz> updateList=classService.update(clazz);
        if(updateList.size()==0) {
            return ClassResult.success("未修改");
        }
        return ClassResult.success(updateList);

    }
    @GetMapping("/list")
    public ClassResult getClassList() {
        List<Clazz> allClass=classService.searchAll();
        if(allClass.size()==0) {
            return ClassResult.success("暂无班级");
        }
        return ClassResult.success(allClass);
    }

}
