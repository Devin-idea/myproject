package com.example.mapper;

import com.example.entity.OrderMaster;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author devin
 * @since 2023-01-05
 */
public interface OrderMasterMapper extends BaseMapper<OrderMaster> {
    public boolean cancel (Integer buyerId,String orderId);
    public boolean finish (String orderId);
    public boolean pay (Integer buyerId,String orderId);

}
