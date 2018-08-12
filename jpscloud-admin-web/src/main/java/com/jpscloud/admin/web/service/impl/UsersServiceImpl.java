package com.jpscloud.admin.web.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jpscloud.admin.web.mapper.UsersMapper;
import com.jpscloud.admin.web.service.UsersService;
import com.jpscloud.common.entity.Users;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author kitty
 * @since 2018-07-02
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {

}
