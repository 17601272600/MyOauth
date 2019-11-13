package com.qiantao.controller;

import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qiantao.mapper.LoginMapper;
import com.qiantao.util.RedisUtil;
import com.qiantao.util.ResponseUtil;

import io.lettuce.core.dynamic.annotation.Param;
@RequestMapping("/oauth/account")
@RestController
public class AccountController {
	
	
	@Resource
	public RedisUtil redisUtil;

	@Resource
	public LoginMapper loginMapper;

	public static String getRamdom() {
		return UUID.randomUUID().toString().replaceAll("-","");
	}
	
	@RequestMapping("/login")
	public String login(@Param("loginId")String loginId,@Param("password")String pwd) {
		String openId=loginMapper.getOpenIdbyPassword(loginId, pwd);
		if(StringUtils.isEmpty(openId)) {
			return ResponseUtil.createResponse(0,"用户名或者密码不正确");
		}
		String tokenID=getRamdom();
		redisUtil.setCache("tokenID:"+tokenID,"openId:"+openId);
		return ResponseUtil.createResponse(1,"登录成功",tokenID);
	}
	
	/**
	 *  注册新用户
	 */
	@RequestMapping("/regeist")
	public String regeist(@Param("loginId")String loginId,@Param("password")String pwd) {
		String openId=loginMapper.getOpenIdbyLoginId(loginId);
		if(StringUtils.isEmpty(openId)) {
			return ResponseUtil.createResponse(0,"当前登录名已存在");
		}
		openId=getRamdom();
		String tokenID=getRamdom();
		redisUtil.setCache("tokenID:"+tokenID,"openId:"+openId);
		loginMapper.saveAccount(loginId,pwd,openId);
		return ResponseUtil.createResponse(1,"登录成功",tokenID);
	}
	
	/**
	 * 修改密码
	 * @param loginId
	 */
	@RequestMapping("/updatePwd")
	public String updatePwd(@Param("loginId")String loginId,@Param("password")String pwd) {
		loginMapper.updatePwd(loginId,pwd);
		return ResponseUtil.createResponse(1,"登录成功");
	}
	
}
