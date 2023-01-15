package com.example.service.impl;

import com.example.entity.Admin;
import com.example.mapper.AdminMapper;
import com.example.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author devin
 * @since 2023-01-15
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

}
