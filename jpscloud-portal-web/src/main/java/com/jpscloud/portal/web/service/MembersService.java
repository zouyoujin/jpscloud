package com.jpscloud.portal.web.service;

import com.baomidou.mybatisplus.service.IService;
import com.jpscloud.common.vo.ResponseService;
import com.jpscloud.portal.web.entity.Members;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author kitty
 * @since 2018-09-08
 */
public interface MembersService extends IService<Members> {
	
	/**
	 * 用户注册处理
	 * @param registerVO
	 * @return
	 */
	public ResponseService<Boolean> register(Members member);
}
