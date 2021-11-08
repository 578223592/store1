package com.swx.store1.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.swx.store1.entity.DictDistrict;
import com.swx.store1.service.impl.DictDistrictServiceImpl;
import com.swx.store1.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Author: Admin
 * Date: 2021/11/7 21:12
 * FileName: DistrictController
 * Description:
 */
@RestController()
@RequestMapping("/districts")
public class DistrictController extends BaseController {

    @Autowired
    DictDistrictServiceImpl districtService;

    @RequestMapping({"/", ""})
    public JsonResult<List<DictDistrict>> getDistricts(String parent) {
        JsonResult<List<DictDistrict>> jsonResult = new JsonResult<>();
        QueryWrapper<DictDistrict> queryWrapper = new QueryWrapper<DictDistrict>().eq("parent", parent);
        List<DictDistrict> list = districtService.list(queryWrapper);
        jsonResult.setState("200");
        jsonResult.setData(list);
        return jsonResult;
    }
}
