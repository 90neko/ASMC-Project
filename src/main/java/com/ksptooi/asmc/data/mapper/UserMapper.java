package com.ksptooi.asmc.data.mapper;

import java.util.ArrayList;

import com.ksptooi.asmc.entity.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


public interface UserMapper {


    /**
     * 添加用户
     */
    public void insertUser(User user);

    /**
     * 删除用户
     */
    public void deleteUser(@Param("id") int id);

    /**
     * 修改用户
     */
    public void updateUser(User user);

    /**
     * 查询用户
     */
    public ArrayList<User> getUserList(User user);

    /**
     * 查询用户
     */
    public User getUser(User user);

}
