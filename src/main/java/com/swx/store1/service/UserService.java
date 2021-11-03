package com.swx.store1.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.swx.store1.entity.User;


/**
 *
 */
public interface UserService extends IService<User> {

    void register(User user);

    User login(User user);
}
