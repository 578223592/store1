package com.swx.store1.controller;

import com.swx.store1.entity.Product;
import com.swx.store1.service.impl.ProductServiceImpl;
import com.swx.store1.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Author: Admin
 * Date: 2021/11/10 11:45
 * FileName: ProductController
 * Description:@RestController()==@ResponseBody+@Controller
 *
 */

@RestController()
@RequestMapping("/products")
public class ProductController extends BaseController{

    @Autowired
    ProductServiceImpl productService;

    @RequestMapping("/hot_list")
    public JsonResult<List<Product>> hotList(){
        JsonResult<List<Product>> jsonResult = new JsonResult<>();
        List<Product> productList = productService.showHotList();
        jsonResult.setData(productList);
        jsonResult.setState("200");
        return jsonResult;
    }

    @RequestMapping("/{id}")
    public JsonResult<Product> getProductById(@PathVariable("id") Integer id){
        JsonResult<Product> jsonResult = new JsonResult<>();
        Product product = productService.findProductById(id);
        jsonResult.setState("200");
        jsonResult.setData(product);
        return jsonResult;
    }
}
