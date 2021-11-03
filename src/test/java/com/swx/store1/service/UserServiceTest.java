package com.swx.store1.service;

import com.swx.store1.entity.User;
import com.swx.store1.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

/**
 * Author: Admin
 * Date: 2021/10/31 21:56
 * FileName: UserMapperTest
 * Description:
 */
//表名当前类是一个测试类，不会被打包
//表明启动这个单元测试类，单元测试类是不能单独运行的，必须传递一个参数，必须是SpringRunner的实例类型
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {
    /**
     * @author admin
     * @Date 2021/10/31 21:59
     * @Description ：1.必须呗@Test注解修饰
     *                  2.不能有返回值
     *                  3.方法的参数列表不指定任何类型


     */
    /**
     * @author admin
     * @Date 2021/10/31 21:29
     * @Description

    测试UserDao功能：1根据表中字段uid查询返回User对象 2.传入User类，在数据库中生成数据（主键自增）
     */
    @Autowired
    UserServiceImpl userService;

    @Test
    public void contextLoads1() {
//        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));

        User user = new User(null, "name6", "123", "333", "123", "email", 1, "avator",0 );

//        int i = userDao.insert(user);
        userService.register(user);


        //检查一下为啥不会抛异常了！~！！！！！！！！！！！！！！！！！！！！！！！！！！！！
    }

    @Test
    public void contextLoads2() {
        String salt = UUID.randomUUID().toString();
        System.out.println(salt);
    }


    @Test
    public void contextLoads3() {
        User user = new User(null, "name6", "1231", "333", "123", "email", 1, "avator",0 );
        userService.login(user);


    }

}
