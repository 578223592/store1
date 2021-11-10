package com.swx.store1.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.swx.store1.entity.Product;

import java.util.List;

/**
 *
 */
public interface ProductService extends IService<Product> {
    List<Product> showHotList();

    Product findProductById(Integer id);
}
