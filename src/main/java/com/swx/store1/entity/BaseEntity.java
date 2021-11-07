package com.swx.store1.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Author: Admin
 * Date: 2021/10/31 20:50
 * FileName: BaseEntity
 * Description:因为所有表都有创建，修改 时间（人） ，因此创建基类
 * 实现serializable似懂非懂
 */
@Data
public class BaseEntity implements Serializable {
 protected String  createdUser;
    protected Date createdTime ;
    protected String modifiedUser;
    protected Date modifiedTime;
}
