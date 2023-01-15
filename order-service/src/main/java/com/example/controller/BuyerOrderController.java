package com.example.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.entity.OrderMaster;
import com.example.form.BuyerOrderForm;
import com.example.service.OrderMasterService;
import com.example.util.ResultVOUtil;
import com.example.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
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
    @GetMapping("/list/{buyerId}/{page}/{size}  ")
    public ResultVO list(
            @PathVariable("buyerId") Integer buyerId,
            @PathVariable ("page") Integer page,
            @PathVariable("size") Integer size){

        Page<OrderMaster> orderMasterPage = new Page<>(page,size);
        Page<OrderMaster> resultPage = this.orderMasterService.page(orderMasterPage);
        List<OrderMaster> records = resultPage.getRecords();
        return ResultVOUtil.success(records);
    }

    @GetMapping("detail/{buyerId}/{orderId}")
    public ResultVO detail(
            @PathVariable("buyerId") Integer buyerId,
            @PathVariable("orderId") String orderId){
        return ResultVOUtil.success(this.orderMasterService.detail(buyerId, orderId));

    }
    @PutMapping("/cancel/{buyerId}/{orderId}")
    public ResultVO cancel(@PathVariable("buyerId") Integer buyerId,
                           @PathVariable("orderId") String orderId){
        this.orderMasterService.cancel(buyerId, orderId);
        return ResultVOUtil.success(null);

    }

    @PutMapping("/finish/{orderId}")
    public ResultVO finish (@PathVariable("orderId") String orderId){
        this.orderMasterService.finish(orderId);
        return ResultVOUtil.success(null);
    }

    @PutMapping("/pay/{buyerId}/{orderId}")
    public ResultVO pay(@PathVariable("buyerId") Integer buyerId,
                           @PathVariable("orderId") String orderId){
        this.orderMasterService.pay(buyerId, orderId);
        return ResultVOUtil.success(null);

    }

}

