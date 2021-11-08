package com.swx.store1.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.swx.store1.entity.Address;
import com.swx.store1.entity.DictDistrict;
import com.swx.store1.entity.User;
import com.swx.store1.service.impl.AddressServiceImpl;
import com.swx.store1.service.impl.DictDistrictServiceImpl;
import com.swx.store1.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;
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
public class AddressServiceTest {
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
    AddressServiceImpl addressService;

    @Autowired
    DictDistrictServiceImpl dictDistrictService;
    @Test
    public void contextLoads1() {
        Address address = new Address();
        address.setAddress("宇宙-中国");
        addressService.addAddress(address,"测试name",33);


    }



    @Test
    public void contextLoads2() {
        QueryWrapper<DictDistrict> queryWrapper = new QueryWrapper<DictDistrict>().eq("parent", "653100");
        List<DictDistrict> list = dictDistrictService.list(queryWrapper);
        System.out.println(list);
    }

}
