package com.swx.store1.controller;

import com.swx.store1.service.ex.InsertException;
import com.swx.store1.service.ex.ServiceException;
import com.swx.store1.service.ex.UserNameDuplicatedException;
import com.swx.store1.utils.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Author: Admin
 * Date: 2021/11/3 10:49
 * FileName: UserBaseController
 * Description:由于UserController中每个方法都有可能会产生异常，因此创建此基类来抽取处理异常
 */
public class UserBaseController {

 /*   @ExceptionHandler注解用于统一处理方法抛出的异常。当我们使用这个注解时，需要定义一个异常的处理方法，再给这个方法加上
 *  @ExceptionHandler注解，这个方法就会处理类中其他方法（被@RequestMapping注解）抛出的异常。
            *  @ExceptionHandler注解中可以添加参数，参数是某个异常类的class，代表这个方法专门处理该类异常。*/
    @ExceptionHandler(ServiceException.class)
    public JsonResult<Void> exceptionHand(Exception e){
        JsonResult<Void> jsonResult = new JsonResult<>();
        if (e instanceof UserNameDuplicatedException){
            jsonResult.setState("5000");
            jsonResult.setMessage("用户名重复");
        }else if (e instanceof InsertException){
            jsonResult.setState("4000");
            jsonResult.setMessage("插入发生未知错误");
        }
        return jsonResult;
    }
}
