package com.example.mapper;

import com.example.entity.ProductInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.math.BigDecimal;

/**
 * <p>
 * 商品表 Mapper 接口
 * </p>
 *
 * @author devin
 * @since 2023-01-05
 */
public interface ProductInfoMapper extends BaseMapper<ProductInfo> {
    public BigDecimal findPriceById(Integer id);
}
