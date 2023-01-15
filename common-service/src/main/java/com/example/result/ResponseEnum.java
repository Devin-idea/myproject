package com.example.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseEnum {

    STOCK_ERROR(300,"库存不足"),
    MOBILE_ERROR(301, "手机格式有误");

    private Integer code;
    private String msg;

}
