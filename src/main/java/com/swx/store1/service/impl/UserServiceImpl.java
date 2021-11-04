package com.swx.store1.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.swx.store1.entity.User;
import com.swx.store1.mapper.UserMapper;
import com.swx.store1.service.UserService;
import com.swx.store1.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;


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


    public boolean findUserIsExistByUsername(String username) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username", username);
        User findUser = userDao.selectOne(userQueryWrapper);
        return findUser != null;
    }

    @Override
    public void register(User user) {
        /*
        待解决bug：用户不输入账号和密码也可以注册成功

         */

        if (findUserIsExistByUsername(user.getUsername())) {
            System.out.println("此用户已经存在!");
            throw new UserNameDuplicatedException("此用户已经存在!!");
        }
        String salt = UUID.randomUUID().toString().toUpperCase();
        String password = user.getPassword();
        password = PasswordMD5(password, salt);
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
        if (i == 0) {
            throw new InsertException("在插入过程中产生未知错误，插入失败");
        }
    }

    @Override
    public User login(User user) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        //注意判断一个用户存在需要满足两个条件：1.用户的名字匹配 2、用户的is_delete字段的值为0
        userQueryWrapper.eq("username", user.getUsername()).eq("is_delete", 0);
        User findUser = userDao.selectOne(userQueryWrapper);
        if (findUser == null) {
            throw new UserNotExistException("根据用户名查询用户，用户不存在，请检查");
        }
        String passwordAfterMD5 = PasswordMD5(user.getPassword(), findUser.getSalt());
        if (!passwordAfterMD5.equals(findUser.getPassword())) {
            //登录失败....
            throw new PasswordNotMatchException("密码错误，请检查");
        }
//到这里已经获取了User对象，有两点需要注意：
// 1.mysql中select *和查询某一个字段：两者差别几乎可忽略。所以查询所有字段（或者大多数字段）的时候，大可select *来操作。如果某些不需要的字段数据量特别大，还是写清楚字段比较好，因为这样可以减少网络传输
//  2.尽管查出了整个User，但是下面创建一个新的User并不是废话，而是调优，因为User是用来保存登录信息的，因此User中很多如：修改时间等字段是用不到的，少一些字段可以减少后端层与层的调用，传到前端之后也可以减少相应的体量
        User resultUser = new User();
        resultUser.setId(findUser.getId());
        resultUser.setUsername(findUser.getUsername());
        resultUser.setAvatar(findUser.getAvatar());
        return resultUser;
    }
/**
 * @author admin
 * @Date 2021/11/4 20:02
 * @Description 虽然已经可以保证修改密码的时候用户已经登录，但是在正式开始修改密码的时候还是验证一下用户是否存在，
 *              因为可能登录之后，修改密码之前账户就被管理员删除了，虽然几率很小，但是还是需要检查，在各种地方避免错误
 */

    @Override
    public void updatePassword(Integer uid, String oldPassword, String newPassword) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getId,uid)
                .eq(User::getIsDelete,0);
        User user = userDao.selectOne(queryWrapper);
        if (user==null) {
            throw new UserNotExistException("修改密码时发现此用户已不存在");
        }
        if (!user.getPassword().equals(PasswordMD5(oldPassword,user.getSalt()))){
            throw new PasswordNotMatchException("修改密码时,"+uid+"用户输入的旧密码不匹配");
        }
        //注意update方法使用userDao.update(user, updateWrapper);的时候会在传入的user对象的值和updateWrapper中设置的值取
        //并集--》即同时采取两个的值都进行修改，因此传入的user要把不修改的值设置为null（mp不修改该字段），
        // 需要在数据库字段里面设置为null的值要在updateWrapper中设置（mp会将该字段设置为null）
        // 因此如果只修改某些字段的话最好传入一个新的user对象或者使用其他方式更新
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<User>();
        updateWrapper.set("password",PasswordMD5(newPassword,user.getSalt()))
                .set("modified_time",new Date())
                .set("modified_user","思无邪-修改密码")
                .eq("uid",user.getId());
        int update = userDao.update(new User(), updateWrapper);
        if (update!=1){
            throw new UpdateException("修改密码时发生未知错误，修改失败");
        }

    }


    /**
     * @author admin
     * @Date 2021/11/1 11:36
     * @Description 对密码进行md5加密
     */

    public String PasswordMD5(String password, String salt) {

        ///写到这：需
        for (int i = 0; i < 3; i++) {
            //比视频少了一个转全大写
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        return password;
    }
}




