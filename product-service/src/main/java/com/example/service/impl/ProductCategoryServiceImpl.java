package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.entity.ProductCategory;
import com.example.entity.ProductInfo;
import com.example.mapper.ProductCategoryMapper;
import com.example.mapper.ProductInfoMapper;
import com.example.service.ProductCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.vo.BuyerProductCategoryVO;
import com.example.vo.BuyerProductInfoVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 类目表 服务实现类
 * </p>
 *
 * @author devin
 * @since 2023-01-05
 */
@Service
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory> implements ProductCategoryService {
    @Autowired
    private ProductCategoryMapper productCategoryMapper;
    @Autowired
    private ProductInfoMapper productInfoMapper;
    @Override
    public List<BuyerProductCategoryVO> buyerList() {
        List<ProductCategory> productCategoryList = this.productCategoryMapper.selectList(null);
        List<BuyerProductCategoryVO> result = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {
            BuyerProductCategoryVO vo = new BuyerProductCategoryVO();
            vo.setName(productCategory.getCategoryName());
            vo.setType(productCategory.getCategoryType());
            QueryWrapper<ProductInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("category_type",productCategory.getCategoryType());
            List<ProductInfo> productInfoList = this.productInfoMapper.selectList(queryWrapper);
            List<BuyerProductInfoVO> productInfoVOS = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                BuyerProductInfoVO vo1 = new BuyerProductInfoVO();
                BeanUtils.copyProperties(productInfo,vo1);
                productInfoVOS.add(vo1);
            }
            vo.setGoods(productInfoVOS);
            result.add(vo);
        }
        return result;
    }
}
