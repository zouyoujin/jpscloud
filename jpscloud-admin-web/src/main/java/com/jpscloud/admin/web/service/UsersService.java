package com.jpscloud.admin.web.service;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.jpscloud.common.entity.Users;
import com.jpscloud.common.vo.PageList;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author kitty
 * @since 2018-07-02
 */
public interface UsersService extends IService<Users> {

	public PageList<Users> getUserPage(Page<Users> page);

	public List<Users> getAllUser();

	public List<Users> getAllUserFromSaveDB();
}
