package com.example.rideswebsocket;

//import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
//import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import com.example.rideswebsocket.util.RedisUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.core.env.Environment;

import java.util.List;

@EnableCaching
//@EnableDubboConfiguration
//@EnableDubbo
@SpringBootApplication
public class RideswebsocketApplication {
	private static Log log= LogFactory.getLog(RideswebsocketApplication.class);

//	public RideswebsocketApplication(RedisTemplate<String, String> redisTemplate){
//
//	}

//	@Autowired
	private static Environment env;

	@Autowired
	public void getEnv(Environment env){
		RideswebsocketApplication.env=env;
	}

	public static void main(String[] args) {
		log.info("--------------------程序开始运行--------------------");
		SpringApplication.run(RideswebsocketApplication.class, args);
		log.info("--------------------向redis添加本服务器--------------------");
//		ConfigurableApplicationContext context=SpringApplication.run(RideswebsocketApplication.class, args);
		RedisUtils redisUtil=new RedisUtils();
		String ip=env.getProperty("server.ip")+":"+env.getProperty("server.port");
		List<Object> objects=redisUtil.lGet("service",0,-1);
		boolean b=false;
		for (Object o:objects){
			if (ip.equals(String.valueOf(o))){
				b=true;
			}
		}
		if (b){
			log.info("--------------------服务器已存在redis--------------------");
		}else if (redisUtil.lSet("service",ip)){
			log.info("--------------------redis添加本服务器成功--------------------");
		}else {
			log.info("--------------------redis添加本服务器失败--------------------");
		}
	}


}
