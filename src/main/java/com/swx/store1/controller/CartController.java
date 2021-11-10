package com.swx.store1.controller;

import com.swx.store1.entity.Cart;
import com.swx.store1.service.impl.CartServiceImpl;
import com.swx.store1.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Author: Admin
 * Date: 2021/11/10 17:05
 * FileName: CartController
 * Description:
 */
@RestController
@RequestMapping("/cart")
public class CartController extends BaseController {

    @Autowired
    CartServiceImpl cartService;

    @RequestMapping("add_cart")
    public JsonResult<Void> addCart(Cart cart, HttpSession session) {
        //如果session过期了那么是进不来这个页面的，发送不了这个请求
        JsonResult<Void> jsonResult = new JsonResult<>();
        Integer uid = getUidInSession(session);
        String username = getUsernameInSession(session);
        cart.setUid(uid);
        cart.setNum(1);
        boolean add = cartService.addToCart(cart, username);
        jsonResult.setState("200");
        if (add) {
            jsonResult.setMessage("加入购物车成功");
        } else {
            jsonResult.setMessage("已加入购物车，请勿重复添加");
        }
        return jsonResult;
    }
    @RequestMapping("/show_cart_list")
    public JsonResult<List<Cart>> showCartList(HttpSession session){
        JsonResult<List<Cart>> jsonResult = new JsonResult<>();
        Integer uid = getUidInSession(session);
        List<Cart> cartList = cartService.showCartList(uid);
        jsonResult.setState("200");
        jsonResult.setData(cartList);
        return jsonResult;
    }
}
