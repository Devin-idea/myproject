package com.example.service.impl;

import com.example.entity.ProductInfo;
import com.example.mapper.ProductInfoMapper;
import com.example.service.ProductInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author devin
 * @since 2023-01-05
 */
@Service
public class ProductInfoServiceImpl extends ServiceImpl<ProductInfoMapper, ProductInfo> implements ProductInfoService {
    @Autowired
    private ProductInfoMapper productInfoMapper;
    @Override
    public BigDecimal findPriceById(Integer id) {
        return this.productInfoMapper.findPriceById(id);
    }
}
