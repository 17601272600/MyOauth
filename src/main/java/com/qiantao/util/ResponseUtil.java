package com.qiantao.util;


/**
 * result 1 成功
 * 		 -1 服务器问题
 *  	 0  失败
 *  msg:记录一些信息
 *  data：如果成功则返回信息
 * @author qiantao
 *
 */
public class ResponseUtil{
	
	public static String createResponse(int result,String msg,String data) {
		return "{\"isSuc\":"+result+",\"msg\":\""+msg+"\""+ResponseUtil.generateData(data)+"}";
	};
	
	public static String generateData(String data) {
		if(data==null)
			return "";
		
		return ",\"data:\""+data.toString()+"\"";
	}

	public static String createResponse(int result, String msg) {
		return ResponseUtil.createResponse(result, msg, null);
	};
	
	
	
}
