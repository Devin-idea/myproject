package com.example.service.impl;

import com.example.entity.OrderMaster;
import com.example.feign.ProductFeign;
import com.example.form.BuyerOrderDetailForm;
import com.example.form.BuyerOrderForm;
import com.example.mapper.OrderMasterMapper;
import com.example.service.OrderMasterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author devin
 * @since 2023-01-05
 */
@Service
public class OrderMasterServiceImpl extends ServiceImpl<OrderMasterMapper, OrderMaster> implements OrderMasterService {
    @Autowired
    private ProductFeign productFeign;
    @Autowired
    private OrderMasterMapper orderMasterMapper;
    @Override
    public boolean create(BuyerOrderForm buyerOrderForm) {
//        存主表
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setBuyerName(buyerOrderForm.getName());
        orderMaster.setBuyerPhone(buyerOrderForm.getPhone());
        orderMaster.setBuyerAddress(buyerOrderForm.getAddress());
        orderMaster.setBuyerOpenid(buyerOrderForm.getId());
        List<BuyerOrderDetailForm> items = buyerOrderForm.getItems();
        BigDecimal orderAmount = new BigDecimal(0);
        for (BuyerOrderDetailForm item : items) {
            Integer productId = item.getProductId();
            Integer productQuantity = item.getProductQuantity();
            //通过ID查询价格
            BigDecimal price = this.productFeign.findPriceById(productId);
            BigDecimal amount = price.multiply(new BigDecimal(productQuantity));
            orderAmount = orderAmount.add(amount);
        }
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setPayStatus(0);
        orderMaster.setOrderStatus(0);
        this.orderMasterMapper.insert(orderMaster);

//        从表

        return false;
    }
}
