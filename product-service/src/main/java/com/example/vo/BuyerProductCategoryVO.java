package com.example.vo;

import com.example.entity.ProductInfo;
import lombok.Data;

import java.util.List;

@Data
public class BuyerProductCategoryVO {
    private String name;
    private Integer type;
    private List<BuyerProductInfoVO> goods;

}
