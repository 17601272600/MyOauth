package com.qiantao.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import io.lettuce.core.dynamic.annotation.Param;

@Mapper
public interface LoginMapper {
	@Select({"select loginId from Account where loginId=#{loginId} and password=#{pwd}"})
	String getOpenIdbyPassword(@Param("loginId")String loginId, @Param("pwd")String pwd);
	
	@Select({"select loginId from Account where loginId=#{loginId}"})
	String getOpenIdbyLoginId(String loginId);
	
	@Insert({"insert into Account(loginId,password,openId)values(#{loginId},#{pwd},#{openId})"})
	void saveAccount(@Param("loginId")String loginId, @Param("pwd")String pwd, @Param("openId")String openId);
	
	@Update({"update Account set password=#{pwd} where loginId=#{loginId}"})
	void updatePwd(@Param("loginId")String loginId, @Param("pwd")String pwd);

}
