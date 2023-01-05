package com.example.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseEnum {

    STOCK_ERROR(300,"库存不足");

    private Integer code;
    private String msg;

}
