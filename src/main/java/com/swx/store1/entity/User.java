package com.swx.store1.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Author: Admin
 * Date: 2021/10/31 21:05
 * FileName: User
 * Description:
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value ="store1.t_user")
public class User extends BaseEntity implements Serializable {
    /**
     * 用户id
     */
    @TableId(value="uid",type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 盐值
     */
    private String salt;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 性别:0-女，1-男
     */
    private Integer gender;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 是否删除：0-未删除，1-已删除
     */
    private Integer isDelete;

    public User() {
    }

    public User(Integer id, String username, String password, String salt, String phone, String email, Integer gender, String avatar, Integer isDelete) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.avatar = avatar;
        this.isDelete = isDelete;
    }

}
