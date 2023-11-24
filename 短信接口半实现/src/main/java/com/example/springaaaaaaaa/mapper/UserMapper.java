package com.example.springaaaaaaaa.mapper;

import com.example.springaaaaaaaa.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * ClassName: UserMapper
 * Package: com.example.springaaaaaaaa.mapper
 */
@Mapper
public interface UserMapper {
    @Select("select * from user where username=#{username}")
    User findByUserName(String username);
    @Insert("insert into user(username,password,create_time,update_time)"+
    " values(#{username},#{password},now(),now())")
    void add(String username, String password);
}
