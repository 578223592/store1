package com.swx.store1.controller;

import com.swx.store1.service.ex.*;
import com.swx.store1.utils.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

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

        if (e instanceof UserNotExistException){
            jsonResult.setState("3000");
            jsonResult.setMessage("用户不存在");
        }else if (e instanceof PasswordNotMatchException){
            jsonResult.setState("2000");
            jsonResult.setMessage("用户密码输入错误");
        }
        return jsonResult;
    }

    /*
    每个controller都有可能往session里面存放数据，因此把session存储的方法给抽离到BaseController里面
     */

    /**
     * @author admin
     * @Date 2021/11/3 17:18
     * @Description
     */

    protected void setUidInSession(HttpSession session,Integer uid){
        session.setAttribute("uid",uid);
    }
    protected void setUsernameInSession(HttpSession session,String username){
        session.setAttribute("username",username);
    }
    protected Integer getUidInSession(HttpSession session){
       return (Integer)session.getAttribute("uid");
    }
    protected String getUsernameInSession(HttpSession session){
       return (String)session.getAttribute("username");
    }
}
