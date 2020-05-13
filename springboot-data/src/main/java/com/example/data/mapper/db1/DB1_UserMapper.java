package com.example.data.mapper.db1;

import com.example.data.entity.User;
import org.apache.ibatis.annotations.Insert;


public interface DB1_UserMapper {

    @Insert("insert into user(id,first_name,last_name) values(#{id},#{firstName},#{lastName})")
    int insertUser(User user);

    User queryUserById(int id);


}
