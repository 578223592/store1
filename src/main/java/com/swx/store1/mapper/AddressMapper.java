package com.swx.store1.mapper;
import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.swx.store1.entity.Address;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Entity generator.entity.Address
 */
@Mapper
public interface AddressMapper extends BaseMapper<Address> {

    int deleteByAid(@Param("aid") Integer aid);

    List<Address> selectPagedByUidOrderByModifiedTimeDesc(IPage<Address> page, @Param("uid") Integer uid);
}




