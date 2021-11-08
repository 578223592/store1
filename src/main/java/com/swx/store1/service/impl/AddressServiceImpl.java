package com.swx.store1.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.swx.store1.entity.Address;
import com.swx.store1.entity.DictDistrict;
import com.swx.store1.mapper.AddressMapper;
import com.swx.store1.mapper.DictDistrictMapper;
import com.swx.store1.service.AddressService;
import com.swx.store1.service.ex.AddressCountLimitException;
import com.swx.store1.service.ex.InsertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 *
 */
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address>
        implements AddressService {

    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    DictDistrictMapper districtMapper;

    @Value("${user.address.maxCount:20}")
    int maxAddressCount;

    @Override
    public void addAddress(Address addressInfo, String username, Integer uid) {
        //查询地址数目，插入新的地址信息
        QueryWrapper<Address> queryWrapper1 = new QueryWrapper<Address>()
                .eq("uid", uid);
        Long addressCount = addressMapper.selectCount(queryWrapper1);

        if (addressCount >= maxAddressCount) {
            throw new AddressCountLimitException("地址数量已经达到设定的上限值");
        }
        addressInfo.setUid(uid);
        //aid为address主键id可以增，因此设置为null
        addressInfo.setAid(null);
        //1表示默认地址，0表示不是默认地址
        Integer isDefault = addressCount == 0 ? 1 : 0;
        addressInfo.setIsDefault(isDefault);

        /*
        前台传入后台的address的省市区只有code而没有name，因此调用district查询出省市区的名字填充到address里面
         */

        QueryWrapper<DictDistrict> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("code",addressInfo.getProvinceCode());
        DictDistrict district = districtMapper.selectOne(queryWrapper2);
        addressInfo.setProvinceName(district.getName());

        queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("code",addressInfo.getCityCode());
        district = districtMapper.selectOne(queryWrapper2);
        addressInfo.setCityName(district.getName());

        queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("code",addressInfo.getAreaCode());
        district = districtMapper.selectOne(queryWrapper2);
        addressInfo.setAreaName(district.getName());

        //四项基本信息
        addressInfo.setModifiedUser(username);
        addressInfo.setCreatedUser(username);
        addressInfo.setCreatedTime(new Date());
        addressInfo.setModifiedTime(new Date());
        int row = addressMapper.insert(addressInfo);
        if (row != 1) {
            throw new InsertException("插入地址时产生未知异常");
        }
    }
}




