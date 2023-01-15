package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.entity.OrderDetail;
import com.example.entity.OrderMaster;
import com.example.entity.ProductInfo;
import com.example.feign.ProductFeign;
import com.example.form.BuyerOrderDetailForm;
import com.example.form.BuyerOrderForm;
import com.example.mapper.OrderDetailMapper;
import com.example.mapper.OrderMasterMapper;
import com.example.service.OrderMasterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.vo.BuyerOrderDetailVO;
import com.example.vo.BuyerOrderMasterVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
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

    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Override
    @Transactional
    public String create(BuyerOrderForm buyerOrderForm) {
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
        for (BuyerOrderDetailForm item : items) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(orderMaster.getOrderId());
            orderDetail.setProductId(item.getProductId());
            orderDetail.setProductQuantity(item.getProductQuantity());
            ProductInfo productInfo = this.productFeign.findById(item.getProductId());
            BeanUtils.copyProperties(productInfo,orderDetail);
            this.orderDetailMapper.insert(orderDetail);
            //减库存
            this.productFeign.subStockById(item.getProductId(),item.getProductQuantity());

        }
        return orderMaster.getOrderId();
    }

    @Override
    public BuyerOrderMasterVO detail(Integer buyerId, String orderId) {
        QueryWrapper<OrderMaster> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("buyer_openid",buyerId);
        queryWrapper.eq("order_id",orderId);
        OrderMaster orderMaster = this.orderMasterMapper.selectOne(queryWrapper);
        BuyerOrderMasterVO orderMasterVO = new BuyerOrderMasterVO();
        BeanUtils.copyProperties(orderMaster,orderMasterVO);
        QueryWrapper<OrderDetail> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("order_id",orderId);
        List<OrderDetail> orderDetails = this.orderDetailMapper.selectList(queryWrapper1);
        List<BuyerOrderDetailVO> buyerOrderDetailVOList = new ArrayList<>();
        for (OrderDetail orderDetail : orderDetails) {
            BuyerOrderDetailVO detailVO = new BuyerOrderDetailVO();
            BeanUtils.copyProperties(orderDetail,detailVO);
            buyerOrderDetailVOList.add(detailVO);
        }
        orderMasterVO.setOrderDetailList(buyerOrderDetailVOList);
        return orderMasterVO;
    }

    @Override
    public boolean cancel(Integer buyerId, String orderId) {
        //加庫存
        QueryWrapper<OrderDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_id", orderId);
        List<OrderDetail> orderDetailList = this.orderDetailMapper.selectList(queryWrapper);
        for (OrderDetail orderDetail : orderDetailList) {
            this.productFeign.addStockById(orderDetail.getProductId(),orderDetail.getProductQuantity());
        }
        return this.orderMasterMapper.cancel(buyerId,orderId);
    }

    @Override
    public boolean finish(String orderId) {
        return this.orderMasterMapper.finish(orderId);
    }

    @Override
    public boolean pay(Integer buyerId, String orderId) {
        return this.orderMasterMapper.pay(buyerId, orderId);
    }
}
