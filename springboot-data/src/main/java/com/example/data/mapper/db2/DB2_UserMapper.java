package com.example.data.mapper.db2;

import com.example.data.entity.User;
import org.apache.ibatis.annotations.Select;

public interface DB2_UserMapper {
    @Select("select id,first_name firstName,last_name lastName from user where id =#{id}")
    User queryUserById(int id);
}
