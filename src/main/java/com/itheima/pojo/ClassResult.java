package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassResult {
    private Integer cd; // 编码: 1成功, 0为失败
    private String mg; // 错误信息
    private Object db; // 数据

    public static ClassResult success() {
        ClassResult result = new ClassResult();
        result.setCd(1);
        result.setMg("success");
        result.setDb(null);
        return result;
    }

    public static ClassResult success(Object object) {
        ClassResult result = new ClassResult();
        result.setCd(1);
        result.setMg("success");
        result.setDb(object);
        return result;
    }

    public static ClassResult error(String msg) {
        ClassResult result = new ClassResult();
        result.setCd(0);
        result.setMg(msg);
        result.setDb(null);
        return result;
    }
}