package com.swx.store1.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.swx.store1.entity.Cart;
import com.swx.store1.entity.CartVO;

import java.util.List;


/**
 *
 */
public interface CartService extends IService<Cart> {
        boolean addToCart(Cart cart,String username);
        List<CartVO> showCartList(Integer uid);
}
