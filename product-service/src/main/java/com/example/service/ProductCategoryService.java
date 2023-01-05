package com.example.service;

import com.example.entity.ProductCategory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.vo.BuyerProductCategoryVO;

import java.util.List;

/**
 * <p>
 * 类目表 服务类
 * </p>
 *
 * @author devin
 * @since 2023-01-05
 */
public interface ProductCategoryService extends IService<ProductCategory> {
    public List<BuyerProductCategoryVO> buyerList();

}
