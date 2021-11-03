package com.swx.store1.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.swx.store1.entity.User;
import com.swx.store1.mapper.UserMapper;
import com.swx.store1.service.UserService;
import com.swx.store1.service.ex.InsertException;
import com.swx.store1.service.ex.UserNameDuplicatedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.swing.plaf.metal.MetalIconFactory;
import java.util.Date;
import java.util.UUID;

/**
 * @author Admin
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Autowired
    private UserMapper userDao;

    @Override
    public void register(User user) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username",user.getUsername());
        User findUser = userDao.selectOne(userQueryWrapper);
        if (findUser != null) {
            System.out.println("此用户已经存在!");
            throw new UserNameDuplicatedException("此用户已经存在!!");
        }
        String salt = UUID.randomUUID().toString().toUpperCase();
        String password = user.getPassword();
        password =PasswordMD5(password,salt);
        user.setSalt(salt);
        user.setPassword(password);
        //填入四个基类数据,以及isDelete设置为0
        user.setCreatedUser("思无邪");
        user.setModifiedUser("思无邪");
        Date date = new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);
        user.setIsDelete(0);
        int i = userDao.insert(user);
        if (i==0){
            throw new InsertException("在插入过程中产生未知错误，插入失败");
        }
    }

    /**
     * @author admin
     * @Date 2021/11/1 11:36
     * @Description 对密码进行md5加密
     */

    public String PasswordMD5(String password,String salt) {

        ///写到这：需
        for (int i = 0; i < 3; i++) {
            //比视频少了一个转全大写
             password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        return password;
    }
}




