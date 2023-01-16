package com.example.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseEnum {

    STOCK_ERROR(300,"库存不足"),
    MOBILE_ERROR(301, "手机格式有误"),
    MOBILE_EXIST(302,"手机号已存在"),
    MOBILE_IS_NULL(303,"手机号未注册"),
    PASSWORD_ERROR(304,"密码错误"),
    TOKEN_ERROR(305,"Token Error");

    private Integer code;
    private String msg;

}
