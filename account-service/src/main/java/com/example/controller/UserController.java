package com.example.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.entity.User;
import com.example.exception.ShopException;
import com.example.form.UserForm;
import com.example.result.ResponseEnum;
import com.example.service.UserService;
import com.example.util.MD5Util;
import com.example.util.RegexValidateUtil;
import com.example.util.ResultVOUtil;
import com.example.vo.ResultVO;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

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
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public ResultVO register(@RequestBody UserForm userForm){
        //验证手机号
        boolean b = RegexValidateUtil.checkMobile(userForm.getMobile());
        if (!b){
            throw new ShopException(ResponseEnum.MOBILE_ERROR.getMsg() );

        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile",userForm.getMobile());
        User one = this.userService.getOne(queryWrapper);
        if (one != null){
            throw new ShopException(ResponseEnum.MOBILE_EXIST.getMsg());

        }
        //校验
        User user = new User();
        user.setMobile(userForm.getMobile());
        user.setPassword(MD5Util.getSaltMD5(userForm.getPassword()));
        this.userService.save(user);
        return ResultVOUtil.success(null);

    }

    @GetMapping("/login")
    public ResultVO login(UserForm userForm){
        boolean b = RegexValidateUtil.checkMobile(userForm.getMobile());
        if (!b){
            throw new ShopException(ResponseEnum.MOBILE_ERROR.getMsg() );

        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile",userForm.getMobile());
        User one = this.userService.getOne(queryWrapper);
        if (one == null){
            throw new ShopException(ResponseEnum.MOBILE_IS_NULL.getMsg());
        }
        //密码验证
        if (! MD5Util.getSaltverifyMD5(userForm.getPassword(),one.getPassword())){
            throw new ShopException(ResponseEnum.PASSWORD_ERROR.getMsg());

        }
        return ResultVOUtil.success(null);
    }

}

