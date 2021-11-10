package com.swx.store1.controller;

import com.swx.store1.controller.ex.*;
import com.swx.store1.entity.User;
import com.swx.store1.service.impl.UserServiceImpl;
import com.swx.store1.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Author: Admin
 * Date: 2021/11/3 10:09
 * FileName: UserController
 * Description:
 */
@Controller
@RequestMapping("/users")
@ResponseBody
public class UserController extends BaseController {

    @Autowired
    UserServiceImpl userService;


    //SpringBoot将传入的参数与pojo类中的变量名（set方法）匹配，匹配成功就把参数传入pojo类中的字段
    @RequestMapping("/register")
    public JsonResult<Void> register(User user) {
        //更新UserBaseController之后就不用再关注异常的捕获了，美滋滋
        JsonResult<Void> jsonResult = new JsonResult<>();
        userService.register(user);
        jsonResult.setState("200");
        jsonResult.setMessage("插入成功");
        return jsonResult;
    }

    //SpringBoot将传入的参数与pojo类中的变量名（set方法）匹配，匹配成功就把参数传入pojo类中的字段
    @RequestMapping("/login")
    public JsonResult<User> login(User user, HttpSession session) {
        JsonResult<User> jsonResult = new JsonResult<>();
        user = userService.login(user);
        jsonResult.setState("200");
        jsonResult.setData(user);
        setUidInSession(session, user.getId());
        setUsernameInSession(session, user.getUsername());
        return jsonResult;
    }


    @RequestMapping("/change_password")
    public JsonResult<Void> changePassword(@RequestParam(required = false, value = "oldPassword") String oldPassword
            , @RequestParam(required = false, value = "newPassword") String newPassword
            , HttpSession session) {
        JsonResult<Void> jsonResult = new JsonResult<>();
        Integer uid = getUidInSession(session);
        userService.updatePassword(uid, oldPassword, newPassword);
        jsonResult.setState("200");
        return jsonResult;
    }

    /*
    修改资料：Dao：根据uid更新数据sql
     */

    @RequestMapping("/get_user_part_info_by_uid")
    public JsonResult<User> getUserPartInfo(HttpSession session) {
        JsonResult<User> jsonResult = new JsonResult<>();
        jsonResult.setState("200");
        Integer uid = getUidInSession(session);
        User userPartInfo = userService.getUserPartInfoById(uid);
        jsonResult.setData(userPartInfo);
        return jsonResult;
    }

    @RequestMapping(value = "/change_info", method = RequestMethod.POST)
    public JsonResult<Void> changeInfo(String phone, String email, Integer gender, HttpSession session) {
        Integer uid = getUidInSession(session);
        userService.updateInfo(uid, phone, email, gender);
        JsonResult<Void> jsonResult = new JsonResult<>();
        jsonResult.setState("200");
        return jsonResult;
    }


    /**
     * @author admin
     * @Date 2021/11/5 17:57
     * @Description
     * @return com.swx.store1.utils.JsonResult<java.lang.Void>
     * @param [session,
     * MultipartFile file:在springmvc中如果实现文件的上传下载需要开启组件bean什么的，
     * 但sb中就直接传入MultipartFile类的对象就可以了
     */
    static int AvatarMax = 10 * 1024 * 1024;  //10Mb,因为file.getSize()返回的单位是byte
    static ArrayList<String> AvatarTypes = new ArrayList<String>();

    static {
        AvatarTypes.add("image/jpeg");
        AvatarTypes.add("image/png");
        AvatarTypes.add("image/bmp");
        AvatarTypes.add("image/gif");
    }

    /*
    因为图片是上传到服务器的，所以在服务器这端才会涉及到RealPath~！！！
     */
    @RequestMapping("/change_avatar")
    public JsonResult<String> change_avatar(HttpSession session, MultipartFile file) {
            /*
            对上传的头像文件需要有几个判断：
            判断文件类型，判断文件大小（非空以及不能超过最大限制），
                 */
        if (file.isEmpty()) {
            throw new FileEmptyException("文件为空");
        }
        if (file.getSize() > AvatarMax) {
            throw new FileSizeException("文件大小超过最大值");
        }
        if (!AvatarTypes.contains(file.getContentType())) {
            throw new FileTypeException("上传头像的文件类型不匹配");
        }

        String path = session.getServletContext().getRealPath("/upload");
        File loadPathFile = new File(path);
        if (!loadPathFile.exists()) {
            boolean mkdirs = loadPathFile.mkdirs();
            if (!mkdirs) {
                System.out.println("上传头像的保存文件夹创建失败！！！");
            }
        }
        StringBuilder fileNewName = new StringBuilder(UUID.randomUUID().toString().toUpperCase());
        String originalFilename = file.getOriginalFilename();
        assert originalFilename != null;
        fileNewName.append(originalFilename.substring(originalFilename.lastIndexOf(".")));
        File newFile = new File(loadPathFile, fileNewName.toString());
        try {
            newFile.createNewFile();
            file.transferTo(newFile);
            System.out.println(newFile);
        } catch (IOException e) {
            throw new FileUploadIOException("头像上传读写失败" + e);
        } catch (FileStateException e) {
            throw new FileStateException("文件状态异常");
        }

        userService.updateAvatar(getUidInSession(session), "/upload/" + fileNewName);
        JsonResult<String> jsonResult = new JsonResult<>();
        jsonResult.setState("200");
        jsonResult.setData("/upload/" + fileNewName);
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
