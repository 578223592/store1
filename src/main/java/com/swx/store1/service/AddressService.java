package com.swx.store1.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.swx.store1.entity.Address;


/**
 *
 */
public interface AddressService extends IService<Address> {

   void addAddress(Address addressInfo,String username,Integer uid);
}
