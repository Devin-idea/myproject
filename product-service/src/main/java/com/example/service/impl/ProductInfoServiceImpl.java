package com.example.service.impl;

import com.example.entity.ProductInfo;
import com.example.exception.ShopException;
import com.example.mapper.ProductInfoMapper;
import com.example.result.ResponseEnum;
import com.example.service.ProductInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.awt.Mutex;

import java.math.BigDecimal;
import java.util.concurrent.locks.ReentrantLock;

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
    private ReentrantLock reentrantLock = new ReentrantLock();
    @Override
    public BigDecimal findPriceById(Integer id) {
        return this.productInfoMapper.findPriceById(id);
    }

    @Override
    public Boolean subStockById(Integer id, Integer quantity) {
//        确定库存
        Integer stock = this.productInfoMapper.findStockById(id);
        if (stock < quantity){
            //抛出异常
            throw new ShopException(ResponseEnum.STOCK_ERROR.getMsg());
        }
        //加同步锁
        reentrantLock.lock();
        Integer result = stock - quantity;
        this.productInfoMapper.updateStock(id, result);
        reentrantLock.unlock();
        return true;
    }

    @Override
    public Boolean addStock(Integer id, Integer quantity) {
        this.productInfoMapper.addStock(id,quantity);
        return true;
    }
}
