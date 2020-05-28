package com.example.data.mapper.db1;

import com.example.data.entity.User;
import org.apache.ibatis.annotations.Insert;

import java.util.List;


public interface DB1_UserMapper {

    @Insert("insert into user(user_id,first_name,last_name) values(#{userId},#{firstName},#{lastName})")
    int insertUser(User user);

    int batchInsertUsers(List<User> userList);

    User queryUserById(int id);


}
