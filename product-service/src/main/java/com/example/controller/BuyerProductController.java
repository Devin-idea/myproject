package com.example.controller;


import com.example.entity.ProductCategory;
import com.example.entity.ProductInfo;
import com.example.service.ProductCategoryService;
import com.example.service.ProductInfoService;
import com.example.util.ResultVOUtil;
import com.example.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.swing.text.StyledEditorKit;
import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 类目表 前端控制器
 * </p>
 *
 * @author devin
 * @since 2023-01-05
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private ProductInfoService productInfoService;

    @GetMapping("/list")
    public ResultVO list() {

        return ResultVOUtil.success(this.productCategoryService.buyerList());

    }

    @GetMapping("/findPriceById/{id}")
    public BigDecimal findPriceById(@PathVariable("id") Integer id) {
//        ProductInfo productInfo = this.productInfoService.getById(id);
        return this.productInfoService.findPriceById(id);

    }

    @GetMapping("/findById/{id}")
    public ProductInfo findById(@PathVariable("id") Integer id) {
        return this.productInfoService.getById(id);
    }

    @PutMapping("/subStockById/{id}/{quantity}")
    public Boolean subStockById(@PathVariable("id") Integer id
            , @PathVariable("quantity") Integer quantity) {
        return this.productInfoService.subStockById(id, quantity);
    }

    @PutMapping("/addStockById/{id}/{quantity}")
    public Boolean addStockById(@PathVariable("id") Integer id
            , @PathVariable("quantity") Integer quantity) {
        return this.productInfoService.addStock(id, quantity);


    }
}

