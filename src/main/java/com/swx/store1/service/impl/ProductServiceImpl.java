package com.swx.store1.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.swx.store1.entity.Product;
import com.swx.store1.mapper.ProductMapper;
import com.swx.store1.service.ProductService;
import com.swx.store1.service.ex.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product>
        implements ProductService {

    @Autowired
    ProductMapper productMapper;

    @Override
    public List<Product> showHotList() {
        List<Product> hotList = productMapper.selectHotList();
        if (hotList ==null){
            throw new ProductNotFoundException("查找热销商品失败");
        }
        for (Product item : hotList) {
            item.setPriority(null);
            item.setCreatedTime(null);
            item.setModifiedTime(null);
            item.setModifiedUser(null);
            item.setModifiedUser(null);
        }
        return hotList;
    }

    @Override
    public Product findProductById(Integer id) {
        Product product = productMapper.selectById(id);
        Integer status=1;
        if (product==null||!status.equals(product.getStatus())){
            throw new ProductNotFoundException("findProductById找不到对应商品");
        }

        product.setCreatedTime(null);
        product.setModifiedTime(null);
        product.setCreatedUser(null);
        product.setModifiedUser(null);

        return product;
    }
}




