package com.itheima.pojo;

import com.itheima.service.ClassService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassQueryParam {

//    @RequestParam(required = false) String name, // 班级名称
//    @RequestParam(required = false) String begin, // 开始时间
//    @RequestParam(required = false) String end,   // 结束时间
//    @RequestParam(defaultValue = "1") Integer page,     // 页码，默认1
//    @RequestParam(defaultValue = "10") Integer pageSize
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String begin;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String end;
    private Integer page;
    private Integer pageSize;

}
