package com.itheima.mapper;

import com.itheima.pojo.ClassQueryParam;
import com.itheima.pojo.Clazz;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ClassMapper {

    List<Clazz> list(ClassQueryParam param);
@Delete("delete from clazz where id = #{id}")
    void delete(Integer id);

    void saveClass(Clazz clazz);

    List<Clazz> getByClassId(Integer id);

    int update(Clazz clazz);

    List<Clazz> getAllClass();
}
