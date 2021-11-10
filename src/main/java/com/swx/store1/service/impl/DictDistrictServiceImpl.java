package com.swx.store1.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.swx.store1.entity.DictDistrict;
import com.swx.store1.mapper.DictDistrictMapper;
import com.swx.store1.service.DictDistrictService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 因为前台不用使用id值，以及本来就是根据parent来查询的，所以前台不要这两个属性了，设置为null减少资源消耗,只保留code和name属性
 *   其实这个可以写在controller里面的，弄在这是为了练习
 */
@Service
public class DictDistrictServiceImpl extends ServiceImpl<DictDistrictMapper, DictDistrict>
        implements DictDistrictService {


    @Override
    public List<DictDistrict> list(Wrapper<DictDistrict> queryWrapper) {

        List<DictDistrict> list = super.list(queryWrapper);
        for (DictDistrict items : list) {
            items.setId(null);
            items.setParent(null);
        }
        return list;
    }
}




