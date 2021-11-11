package com.swx.store1.mapper;
import java.util.List;

import com.swx.store1.entity.CartVO;
import org.apache.ibatis.annotations.Param;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.swx.store1.entity.Cart;


/**
 * @Entity entity.Cart
 */
@Mapper()
public interface CartMapper extends BaseMapper<Cart> {
    int countByPidAndUid(@Param("pid") Integer pid, @Param("uid") Integer uid);

    List<Cart> selectByUid(@Param("uid") Integer uid);

    List<CartVO> selectCartVOByUid(@Param("uid") Integer uid);
}




