package com.example.rideswebsocket.testController;

import com.example.rideswebsocket.rocketMQ.RocketMQConsumer;
import com.example.rideswebsocket.rocketMQ.RocketMQProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * MQ测试
 */
@RestController
public class MQController {
    @Autowired
    RocketMQProvider rocketMQProvider;

    @Autowired
    RocketMQConsumer rocketMQConsumer;

//    @RequestMapping("/MQ")
//    public String testMq() {
//        rocketMQProvider.defaultMQProducer();
//        return null;
//    }
//
//    @RequestMapping("MQQ")
//    public String testMqq(){
//        rocketMQConsumer.defaultMQPushConsumer();
//        return null;
//    }
}
