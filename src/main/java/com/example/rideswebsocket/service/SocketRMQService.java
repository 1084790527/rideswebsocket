package com.example.rideswebsocket.service;

import com.example.rideswebsocket.webSocket.WebSocket;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class SocketRMQService {
    private static Log log = LogFactory.getLog(SocketRMQService.class);

    private static String port;

    @Value("${server.port}")
    private void setPort(String port){
        this.port=port;
    }

    private static String ip;
    @Value("${server.ip}")
    private void setIP(String ip){
        this.ip=ip;
    }
    /**
     * NameServer 地址
     */
    private static String namesrvAddr;
    @Value("${apache.rocketmq.namesrvAddr}")
    private void setNamesrvAddr(String namesrvAddr){
        this.namesrvAddr=namesrvAddr;
    }

    /**
     * 生产者的组名
     */
    private static String producerGroup;
    @Value("${apache.rocketmq.producer.producerGroup}")
    private void setProducerGroup(String producerGroup){
        this.producerGroup=producerGroup;
    }

    /**
     * 消费者的组名
     */
    private static String consumerGroup;
    @Value("${apache.rocketmq.consumer.PushConsumer}")
    private void setConsumerGroup(String consumerGroup){
        this.consumerGroup=consumerGroup;
    }

    //订阅socket消息
    @PostConstruct //@PostContruct是spring框架的注解，在方法上加该注解会在项目启动的时候执行该方法，也可以理解为在spring容器初始化的时候执行该方法。
    public static void defaultMQPushConsumer() {
        log.info("----------------"+producerGroup+"---------------"+namesrvAddr);
        //消费者的组名
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(consumerGroup);

        //指定NameServer地址，多个地址以 ; 隔开
        consumer.setNamesrvAddr(namesrvAddr);
        try {
            //订阅PushTopic下Tag为push的消息
            consumer.subscribe("SocketRMQ", "service"+ip+":"+port);
            log.info(ip+"--------------------"+port);
            //设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费
            //如果非第一次启动，那么按照上次消费的位置继续消费
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            consumer.registerMessageListener((MessageListenerConcurrently) (list, context) -> {//->为Java8的lambda表达式,就是匿名函数,具体可以参考该文章https://segmentfault.com/q/1010000007518474。
                try {
                    for (MessageExt messageExt : list) {

//                        System.out.println("messageExt: " + messageExt);//输出消息内容
//                        log.info("messageExt: " + messageExt);

                        String messageBody = new String(messageExt.getBody());

//                        System.out.println("消费响应：msgId : " + messageExt.getMsgId() + ",  msgBody : " + messageBody);//输出消息内容
                        log.info("消费响应：msgId : " + messageExt.getMsgId() +", key: "+messageExt.getKeys()+ ",  msgBody : " + messageBody);
                        WebSocket.onSend(messageBody);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER; //稍后再试        30s后重复接收
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS; //消费成功
            });
            consumer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //发布socket消息
//    @PostConstruct //@PostContruct是spring框架的注解，在方法上加该注解会在项目启动的时候执行该方法，也可以理解为在spring容器初始化的时候执行该方法。
    public static void defaultMQProducer(String service,String key,String body) {
        log.info("----------------"+producerGroup+"---------------"+namesrvAddr);
        DefaultMQProducer producer= new DefaultMQProducer(producerGroup);
        //指定NameServer地址，多个地址以 ; 隔开
        producer.setNamesrvAddr(namesrvAddr);
        //生产者的组名
        producer.setCreateTopicKey("AUTO_CREATE_TOPIC_KEY");
        try {
            /**
             * Producer对象在使用之前必须要调用start初始化，初始化一次即可
             * 注意：切记不可以在每次发送消息时，都调用start方法
             */
            producer.start();

            //创建一个消息实例，包含 topic、tag 和 消息体
            //如下：topic 为 "TopicTest"，tag 为 "push"
//            Message message = new Message("TopicTest", "push", "发送消息----xxxxx-----".getBytes());

            StopWatch stop = new StopWatch();
            stop.start();
            Message message = new Message("SocketRMQ","service"+service,key, body.getBytes());
            SendResult result = producer.send(message,new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                    Integer id = (Integer) arg;
                    int index = id % mqs.size();
                    return mqs.get(index);
                }
            },1);
            log.info("发送响应：MsgId:" + result.getMsgId() + "，发送状态:" + result.getSendStatus()+"，key:"+key+"，tags:"+body);
            stop.stop();
//            System.out.println("----------------发送十条消息耗时：" + stop.getTotalTimeMillis());
//            log.info("发送十条消息耗时：" + stop.getTotalTimeMillis());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.shutdown();
        }
    }


}
