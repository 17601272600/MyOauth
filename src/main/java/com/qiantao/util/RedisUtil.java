package com.qiantao.util;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

/**
 * redicache 工具类
 * 
 * 存在两种命名空 
 * model:对象永久保存
 * cache:非永久保存,默认7s
 */
@SuppressWarnings("unchecked")
@Component
public class RedisUtil {
	@SuppressWarnings("rawtypes")
	@Autowired
	private RedisTemplate redisTemplate;

	public static String modelNameSpace="model:";
	public static String cacheNameSpace="cache:";
	/**
	 *     删除key
	 * @param key
	 */
	public void removeModel(final String key) {
		remove(modelNameSpace+key);
	}
	public void removeCache(final String key) {
		remove(cacheNameSpace+key);
	}
	private void remove(String key) {
		if (exists(key)) {
			redisTemplate.delete(key);
		}
	};
	

	/**
	 * 判断缓存中是否有对应的value
	 * @param key
	 * @return
	 */
	public boolean existsInModel(String key) {
		return exists(modelNameSpace+key);
	}
	public boolean existsInCache(String key) {
		return	exists(cacheNameSpace+key);
	}
	
	private boolean exists(final String key) {
		return redisTemplate.hasKey(key);
	}

	/**
	 * 读取缓存
	 * @return
	 */
	public Object getModel(final String key) {
		return get(modelNameSpace+key);
	}
	public Object getCache(final String key) {
		return get(cacheNameSpace+key);
	}
	private Object get(final String key) {
		Object result = null;
		ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
		result = operations.get(key);
		return result;
	}

	/**
	 * 写入缓存
	 * @return
	 */
	public boolean setModel(String key, Object value) {
		return set(modelNameSpace+key, value,0l);
	}
	public boolean setCache(String key, Object value) {
		return set(cacheNameSpace+key, value,7l);
	}
	public boolean setCache(String key, Object value,long time) {
		return set(cacheNameSpace+key, value,time);
	}
	
	
	private boolean set(final String key, Object value, Long expireTime) {
		boolean result = false;
		try {
			ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
			operations.set(key, value);
			if(expireTime!=null||expireTime<=0)
				redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}    
	
}
