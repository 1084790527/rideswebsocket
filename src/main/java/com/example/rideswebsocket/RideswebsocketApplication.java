package com.example.rideswebsocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication
public class RideswebsocketApplication {

//	public RideswebsocketApplication(RedisTemplate<String, String> redisTemplate){
//
//	}

	public static void main(String[] args) {
		SpringApplication.run(RideswebsocketApplication.class, args);
	}

}
