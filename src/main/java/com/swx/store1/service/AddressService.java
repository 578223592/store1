package com.swx.store1.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.swx.store1.entity.Address;

import java.util.List;


/**
 *
 */
public interface AddressService extends IService<Address> {

   void addAddress(Address addressInfo,String username,Integer uid);
   List<Address> showAddress(Integer uid);
   void setDefaultAddress(Integer aid,Integer uid,String modifiedUserName);
   void deleteAddress(Integer aid,Integer uid,String username);
}
