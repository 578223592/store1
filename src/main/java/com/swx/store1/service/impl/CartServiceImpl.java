package com.swx.store1.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.swx.store1.entity.Cart;
import com.swx.store1.mapper.CartMapper;
import com.swx.store1.service.CartService;

import com.swx.store1.service.ex.InsertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 *
 */
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart>
        implements CartService {

    @Autowired
    CartMapper cartMapper;

    @Override
    public boolean addToCart(Cart cart, String username) {
        int count = cartMapper.countByPidAndUid(cart.getPid(), cart.getUid());
        if (count!=0){
            System.out.println(count);
            return false;
        }
        cart.setCreatedTime(new Date());
        cart.setCreatedUser(username);
        cart.setModifiedUser(username);
        cart.setModifiedTime(new Date());
        int row = cartMapper.insert(cart);
        if (row != 1) {
            throw new InsertException("创建购物车数据异常");
        }
        return true;
    }

    @Override
    public List<Cart> showCartList(Integer uid) {
        //无异常抛出
        List<Cart> cartList = cartMapper.selectByUid(uid);
        for (Cart item : cartList) {
            item.setModifiedUser(null);
            item.setCreatedUser(null);
            item.setCreatedTime(null);
            item.setModifiedTime(null);
        }
        return cartList;
    }


}




