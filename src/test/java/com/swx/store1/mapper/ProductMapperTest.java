package com.swx.store1.mapper;

import com.swx.store1.entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Author: Admin
 * Date: 2021/10/31 21:56
 * FileName: UserMapperTest
 * Description:
 */
//表名当前类是一个测试类，不会被打包
@SpringBootTest
//表明启动这个单元测试类，单元测试类是不能单独运行的，必须传递一个参数，必须是SpringRunner的实例类型
@RunWith(SpringRunner.class)
public class ProductMapperTest {
    @Autowired
    ProductMapper productMapper;
    @Test
    public void contextLoads1() {
        List<Product> productList = productMapper.selectHotList();
        System.out.println(productList);
        System.err.println(productList.size());
    }

}
