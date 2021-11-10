package com.swx.store1.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.swx.store1.entity.Address;
import com.swx.store1.entity.DictDistrict;
import com.swx.store1.mapper.AddressMapper;
import com.swx.store1.mapper.DictDistrictMapper;
import com.swx.store1.service.AddressService;
import com.swx.store1.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
        queryWrapper2.eq("code", addressInfo.getProvinceCode());
        DictDistrict district = districtMapper.selectOne(queryWrapper2);
        addressInfo.setProvinceName(district.getName());

        queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("code", addressInfo.getCityCode());
        district = districtMapper.selectOne(queryWrapper2);
        addressInfo.setCityName(district.getName());

        queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("code", addressInfo.getAreaCode());
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

    @Override
    public List<Address> showAddress(Integer uid) {
        QueryWrapper<Address> queryWrapper = new QueryWrapper<Address>()
                .eq("uid", uid)
                .orderByDesc("is_default", "modified_time");
        List<Address> addressList = list(queryWrapper);
        //前台只用到地址类型，收货人姓名，详细地址，联系电话，是否默认 字段，减小消耗等等将
        // 总之将用不到的字段设置为null
        for (Address item : addressList) {
//            item.setAid(null);
//            item.setUid(null);

            item.setModifiedTime(null);
            item.setCreatedTime(null);
            item.setCreatedUser(null);
            item.setModifiedUser(null);

            item.setProvinceCode(null);
            item.setAreaCode(null);
            item.setCityCode(null);
        }
        return addressList;
    }

    /**
     * @param aid
     * @param uid
     * @param modifiedUserName 修改者姓名
     * @return
     * @author admin
     * @Date 2021/11/8 17:22
     * @Description 业务逻辑为：查询此条数据是否存在/是否属于当前uid用户，将此uid用户的所有地址变为非默认，将此uid用户的aid的地址变为默认
     */

    @Override
    public void setDefaultAddress(Integer aid, Integer uid, String modifiedUserName) {
        QueryWrapper<Address> queryWrapper = new QueryWrapper<Address>()
                .eq("aid", aid);
        Address address = addressMapper.selectOne(queryWrapper);
        if (address == null) {
            throw new AddressNotFoundException("地址数据不存在");
        }
        //Integer数据类型比较用equals
        if (!uid.equals(address.getUid())) {
            throw new AddressDeniedException("非法数据访问");
        }
        UpdateWrapper<Address> updateWrapper = new UpdateWrapper<Address>()
                .set("is_default", 0)
                .eq("uid", uid);

        boolean update = update(updateWrapper);
        if (!update) {
            throw new UpdateException("更新用户默认地址时发生未知异常");
        }
        updateWrapper = new UpdateWrapper<Address>()
                .set("is_default", 1)
                .set("modified_time", new Date())
                .set("modified_user","思无邪-修改默认地址")
                .eq("aid", aid);
        update = update(updateWrapper);
        if (!update) {
            throw new UpdateException("更新用户默认地址时发生未知异常");
        }
    }

    @Override
    public void deleteAddress(Integer aid, Integer uid, String username) {
        QueryWrapper<Address> queryWrapper = new QueryWrapper<Address>()
                .eq("aid", aid);
        //aid正好是主键
        Address address = addressMapper.selectById(aid);
        if (address==null){
            throw new AddressNotFoundException("数据未找到");
        }
        if (!address.getUid().equals(uid)){
            throw new AddressDeniedException("非法数据删除");
        }
        int row = addressMapper.deleteByAid(aid);
        if (row!=1){
            throw new DeleteException("删除地址失败");
        }
        if (address.getIsDefault()==1){
            //如果删除的地址是默认地址的话，那么还需要选择一个默认地址
            IPage<Address> addressIPage = new Page<Address>(1, 1);;
            List<Address> list = addressMapper.selectPagedByUidOrderByModifiedTimeDesc(addressIPage, uid);
            if (list.size()==0){
                return;
            }
            Integer aid1 = list.get(0).getAid();
            UpdateWrapper<Address> updateWrapper = new UpdateWrapper<Address>()
                    .set("is_default", 1)
                    .set("modified_user", username)
                    .set("modified_time", new Date())
                    .eq("aid",aid1);
            boolean update = update(updateWrapper);
            if (!update){
                throw new UpdateException("设置新的默认地址失败");
            }
        }

    }


}




