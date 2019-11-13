package com.qiantao;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
@SpringBootApplication
@EnableAutoConfiguration
public class OauthApp {
	public static void main(String[] arg0) {
		SpringApplication.run(OauthApp.class, arg0);
	}
	
	
    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
    /**
     * redis连接
     * @return
     */
    @Bean
    public JedisPool redisPoolFactory(@Value("${spring.redis.host}")String host,
    		  						@Value("${spring.redis.port}")int port,
    		  						 @Value("${spring.redis.timeout}")int timeout,
    		  						@Value("${spring.redis.password}")String password) {	
    	JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(8);//连接池默认最大空闲连接为8
        jedisPoolConfig.setJmxEnabled(true);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password);
        return jedisPool;
    }

  
    
}
