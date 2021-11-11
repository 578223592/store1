package com.swx.store1.entity;

import lombok.Data;

/**
 * Author: Admin
 * Date: 2021/11/11 22:02
 * FileName: CartVO
 * Description:
 */
@Data
public class CartVO {
    private Integer cid;
    private Integer uid;
    private Integer pid;
    private Long price;
    private Integer num;
    private String title;
    private String image;
    private Long realPrice;

}
