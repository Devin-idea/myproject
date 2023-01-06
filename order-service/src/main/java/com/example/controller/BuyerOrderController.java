package com.example.controller;


import com.example.form.BuyerOrderForm;
import com.example.service.OrderMasterService;
import com.example.util.ResultVOUtil;
import com.example.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

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
    @Autowired
    private OrderMasterService orderMasterService;
    @PostMapping("/create")
    public ResultVO create(@RequestBody BuyerOrderForm buyerOrderForm){
        String orderId = this.orderMasterService.create(buyerOrderForm);
        Map<String,String> map = new HashMap<>();
        map.put("orderId",orderId);
        return ResultVOUtil.success(map);


    }

}

