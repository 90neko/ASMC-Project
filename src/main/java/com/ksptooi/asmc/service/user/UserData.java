package com.ksptooi.asmc.service.user;

import com.ksptooi.asmc.data.mapper.UserMapper;
import com.ksptooi.asmc.entity.user.User;
import com.ksptooi.asmc.main.Asmc;

import java.util.ArrayList;

public class UserData implements UserDataService{

    UserMapper mapper = null;

    public UserData() {

        Asmc.getLogger().info("初始化内部组件 - UserDataService");
        this.mapper = Asmc.getContainerService().getBean(UserMapper.class);
    }

    /**
     * 添加用户
     *
     * @param user
     */
    @Override
    public void insertUser(User user) {
        mapper.insertUser(user);
    }

    /**
     * 删除用户
     *
     * @param id
     */
    @Override
    public void deleteUser(int id) {
        mapper.deleteUser(id);
    }

    /**
     * 修改用户
     *
     * @param user
     */
    @Override
    public void updateUser(User user) {
        mapper.updateUser(user);
    }

    /**
     * 查询用户
     *
     * @param user
     */
    @Override
    public ArrayList<User> getUserList(User user) {
        return mapper.getUserList(user);
    }

    /**
     * 查询用户
     *
     * @param user
     */
    @Override
    public User getUser(User user) {
        return mapper.getUser(user);
    }

    /**
     * 查询用户
     *
     * @param userName
     */
    @Override
    public User getUser(String userName) {

        User u = new User();
        u.setId(null);
        u.setUserName(userName);

        return this.getUser(u);
    }

}
