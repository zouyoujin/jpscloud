package com.jpscloud.admin.web.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jpscloud.admin.web.mapper.UsersMapper;
import com.jpscloud.admin.web.service.UsersService;
import com.jpscloud.common.dataSource.DBTypeEnum;
import com.jpscloud.common.dataSource.DataSourceSwitch;
import com.jpscloud.common.entity.Users;

import java.util.List;

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

	@DataSourceSwitch(DBTypeEnum.master)
	@Override
	public List<Users> getAllUser() {
		return this.selectList(null);
	}
	
	@DataSourceSwitch(DBTypeEnum.save)
	@Override
	public List<Users> getAllUserFromSaveDB() {
		return this.selectList(null);
	}
}
