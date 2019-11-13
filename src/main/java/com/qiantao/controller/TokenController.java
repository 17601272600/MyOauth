package com.qiantao.controller;

import java.util.UUID;

import javax.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qiantao.mapper.LoginMapper;
import com.qiantao.util.RedisUtil;
import com.qiantao.util.ResponseUtil;

import io.lettuce.core.dynamic.annotation.Param;

@Controller
@RequestMapping("/oauth/token")
public class TokenController {
	@Resource
	public LoginMapper loginMapper;

	@Resource
	public RedisUtil redisUtil;
	
	
	/**
	 * 根据token去刷新token的有效期 
	 */
	@ResponseBody
	@RequestMapping("update")
	public String update(@Param("token")String token) {
		Object str=redisUtil.getCache(token);
		if(str==null)
			return ResponseUtil.createResponse(0,"当前token已经过期,请重新申请");
		String token2= UUID.randomUUID().toString().replaceAll("-","");
		redisUtil.removeCache(token);
		redisUtil.setCache(token2, str);
		return ResponseUtil.createResponse(1,"更新成功",str.toString());
	}

	
	
	/**
	 * 根据token获取openId
	 */
	public String getOpenId(@Param("token")String token) {
		Object str=redisUtil.getCache(token);
		if(str==null)
			return ResponseUtil.createResponse(0,"当前token已经过期,请重新申请");
		return ResponseUtil.createResponse(1,"获取数据成功",str.toString());
	}
	
	
	/**
	 *  注销token
	 */
	@ResponseBody
	@RequestMapping("delete")
	public String delete(@Param("token")String token) {
		redisUtil.removeCache(token);
		return ResponseUtil.createResponse(1,"注销成功");
	}
	
	/**
	 * 验证token
	 */
	@ResponseBody
	@RequestMapping("check")
	public String check(@Param("token")String token) {
		Object str=redisUtil.getCache(token);
		if(str==null)
			return ResponseUtil.createResponse(0,"当前token已经过期,请重新申请");
		return ResponseUtil.createResponse(1,"注销成功");
	}
}
