package com.swx.store1.utils;

import lombok.Data;

import java.io.Serializable;

/**
 * Author: Admin
 * Date: 2021/11/3 10:03
 * FileName: JsonResult
 * Description:
 */
@Data
public class JsonResult<E> implements Serializable {
    private String state;
    private String message;
    private E Data;
}
