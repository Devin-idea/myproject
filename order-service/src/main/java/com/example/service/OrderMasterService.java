package com.example.service;

import com.example.entity.OrderMaster;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.form.BuyerOrderForm;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author devin
 * @since 2023-01-05
 */
public interface OrderMasterService extends IService<OrderMaster> {
    public boolean create(BuyerOrderForm buyerOrderForm);

}
