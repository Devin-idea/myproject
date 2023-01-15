package com.example.service;

import com.example.entity.OrderMaster;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.form.BuyerOrderForm;
import com.example.vo.BuyerOrderMasterVO;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author devin
 * @since 2023-01-05
 */
public interface OrderMasterService extends IService<OrderMaster> {
    public String create(BuyerOrderForm buyerOrderForm);
    public BuyerOrderMasterVO detail(Integer buyerId,String orderId);
    public boolean cancel (Integer buyerId,String orderId);
    public boolean finish (String orderId);
    public boolean pay (Integer buyerId,String orderId);


}
