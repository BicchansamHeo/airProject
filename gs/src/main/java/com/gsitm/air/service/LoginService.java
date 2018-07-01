package com.gsitm.air.service;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.Resource;
import javax.websocket.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.gsitm.air.controller.LoginController;
import com.gsitm.air.dao.AirDao;

public class LoginService implements UserDetailsService {
	
	@Resource(name="airDao")
	private AirDao airDao;
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//dao에서 user 검증
		return airDao.loadUserByUsername(username);
	}
}