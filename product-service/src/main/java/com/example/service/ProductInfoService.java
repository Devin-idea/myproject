package com.example.service;

import com.example.entity.ProductInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author devin
 * @since 2023-01-05
 */
public interface ProductInfoService extends IService<ProductInfo> {
    public BigDecimal findPriceById(Integer id);

}
