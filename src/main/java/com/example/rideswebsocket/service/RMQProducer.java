package com.example.rideswebsocket.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.List;

@Service
public class RMQProducer{

    private static Log log= LogFactory.getLog(RMQProducer.class);

    /**
     * 生产者的组名
     */
    private static String producerGroup;
    @Value("${apache.rocketmq.producer.producerGroup}")
    private void setProducerGroup(String producerGroup){
        this.producerGroup=producerGroup;
    }

    /**
     * NameServer 地址
     */
    private static String namesrvAddr;
    @Value("${apache.rocketmq.namesrvAddr}")
    private void setNamesrvAddr(String namesrvAddr){
        this.namesrvAddr=namesrvAddr;
    }


    //发布socket消息
//    @PostConstruct //@PostContruct是spring框架的注解，在方法上加该注解会在项目启动的时候执行该方法，也可以理解为在spring容器初始化的时候执行该方法。
    public static boolean sendMQProducer(String createTopicKey , String topic,String service,String tags,String key,String body) {
//        log.info("----------------"+producerGroup+"---------------"+namesrvAddr);
        DefaultMQProducer producer= new DefaultMQProducer(producerGroup);
        //指定NameServer地址，多个地址以 ; 隔开
        producer.setNamesrvAddr(namesrvAddr);
        //生产者的组名
        producer.setCreateTopicKey(createTopicKey);
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
            Message message = new Message(topic,tags+service,key, body.getBytes());
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
        return true;
    }
}
