package com.example.controller;


import com.example.form.BuyerOrderForm;
import com.example.vo.ResultVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 订单详情表 前端控制器
 * </p>
 *
 * @author devin
 * @since 2023-01-05
 */
@RestController
@RequestMapping("/buyer/order")
public class BuyerOrderController {
    @PostMapping("/create")
    public ResultVO create(@RequestBody BuyerOrderForm buyerOrderForm){
        System.out.println(buyerOrderForm);
        return null;


    }

}
