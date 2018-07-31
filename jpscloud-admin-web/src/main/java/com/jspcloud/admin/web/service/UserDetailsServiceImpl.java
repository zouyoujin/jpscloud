package com.jspcloud.admin.web.service;

import java.util.Collection;
import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jpscloud.common.entity.CustomUserDetails;
import com.jpscloud.common.entity.Users;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("用户的用户名: {}", username);
        // TODO 根据用户名，查找到对应的密码，与权限

        // 封装用户信息，并返回。参数分别是：用户名，密码，用户权限
        Users user = new Users();
        user.setId(100000L);
        user.setUserName("admin");
        user.setPassword(passwordEncoder.encode("123456"));
        //权限默认admin没有进行细分
  		Collection<GrantedAuthority> authorities = new HashSet<>();
  		authorities.add(new SimpleGrantedAuthority("admin"));
        return new CustomUserDetails(user, true, true, true, true, authorities);
	}
}
