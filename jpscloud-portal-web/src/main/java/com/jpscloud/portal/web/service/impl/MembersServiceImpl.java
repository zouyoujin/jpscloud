package com.jpscloud.portal.web.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jpscloud.common.vo.ResponseService;
import com.jpscloud.portal.web.entity.Members;
import com.jpscloud.portal.web.mapper.MembersMapper;
import com.jpscloud.portal.web.service.MembersService;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author kitty
 * @since 2018-09-08
 */
@Service
public class MembersServiceImpl extends ServiceImpl<MembersMapper, Members> implements MembersService {
	
	/**
	 * 用户注册
	 */
	@Transactional
	@Override
	public ResponseService<Boolean> register(Members member) {
		ResponseService<Boolean> response = new ResponseService<Boolean>();
		this.insert(member);
		response.setData(true);
		response.setMessage("恭喜您，注册成功！");
		response.setAction("/");
		return response;
	}

}
