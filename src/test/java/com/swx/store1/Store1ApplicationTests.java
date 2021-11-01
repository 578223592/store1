package com.swx.store1;

import com.swx.store1.entity.User;
import com.swx.store1.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
class Store1ApplicationTests {

    @Autowired
    private DataSource dataSource;
    /*
    * datasource测试
    * */
    @Test
    void contextLoads() throws SQLException {
        System.out.println(dataSource.getConnection());
    }

}
