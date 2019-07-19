package com.example.rideswebsocket.testController;

import com.example.rideswebsocket.mq.RocketMQConsumer;
import com.example.rideswebsocket.mq.RocketMQProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MQController {
    @Autowired
    RocketMQProvider rocketMQProvider;

    @Autowired
    RocketMQConsumer rocketMQConsumer;

    @RequestMapping("/MQ")
    public String testMq() {
        rocketMQProvider.defaultMQProducer();
        return null;
    }

    @RequestMapping("MQQ")
    public String testMqq(){
        rocketMQConsumer.defaultMQPushConsumer();
        return null;
    }
}
