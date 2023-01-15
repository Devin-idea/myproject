package com.example.controller;


import com.example.exception.ShopException;
import com.example.form.UserForm;
import com.example.result.ResponseEnum;
import com.example.util.RegexValidateUtil;
import com.example.util.ResultVOUtil;
import com.example.vo.ResultVO;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author devin
 * @since 2023-01-15
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @PostMapping("/register")
    public ResultVO register(@RequestBody UserForm userForm){
        boolean b = RegexValidateUtil.checkMobile(userForm.getMobile());
        if (!b){
            throw new ShopException(ResponseEnum.MOBILE_ERROR.getMsg() );

        }
        return ResultVOUtil.success(null);

    }

}

