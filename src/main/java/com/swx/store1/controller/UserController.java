package com.swx.store1.controller;

import com.swx.store1.entity.User;
import com.swx.store1.service.ex.InsertException;
import com.swx.store1.service.ex.UserNameDuplicatedException;
import com.swx.store1.service.impl.UserServiceImpl;
import com.swx.store1.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Author: Admin
 * Date: 2021/11/3 10:09
 * FileName: UserController
 * Description:
 */
@Controller
@RequestMapping("/users")
@ResponseBody
public class UserController extends UserBaseController {

    @Autowired
    UserServiceImpl userService;

    @RequestMapping("/register")
    public JsonResult<Void> register(User user) {
        //更新UserBaseController之后就不用再关注异常的捕获了，美滋滋
        JsonResult<Void> jsonResult = new JsonResult<>();
        userService.register(user);
        jsonResult.setState("200");
        jsonResult.setMessage("插入成功");
        return jsonResult;
    }
    /*@RequestMapping("/register")
    public JsonResult<Void> register(User user){
        JsonResult<Void> jsonResult = new JsonResult<>();
        try {
            userService.register(user);
            jsonResult.setState("200");
            jsonResult.setMessage("插入成功");
        }catch (UserNameDuplicatedException e){
            jsonResult.setState("5000");
            jsonResult.setMessage("用户名重复");
        }catch (InsertException e){
            jsonResult.setState("4000");
            jsonResult.setMessage("插入发生未知错误");
        }
        return jsonResult;
    }*/
}
