package com.swx.store1.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.swx.store1.entity.Product;
/**
 * @Entity generator.entity.Product
 */
@Mapper
public interface ProductMapper extends BaseMapper<Product> {

    List<Product> selectHotList();
}




