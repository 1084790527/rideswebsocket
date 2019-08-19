package com.example.rideswebsocket.rocketMQ;


import java.util.List;

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

import javax.annotation.PostConstruct;
//import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
//import com.alibaba.rocketmq.client.producer.MessageQueueSelector;
//import com.alibaba.rocketmq.client.producer.SendResult;
//import com.alibaba.rocketmq.common.message.Message;
//import com.alibaba.rocketmq.common.message.MessageQueue;


@Service
public class RocketMQProvider {
//    private Log log= LogFactory.getLog(RocketMQProvider.class);
//    /**
//     * 生产者的组名
//     */
//    @Value("${apache.rocketmq.producer.producerGroup}")
//    private String producerGroup;
//
//    /**
//     * NameServer 地址
//     */
//    @Value("${apache.rocketmq.namesrvAddr}")
//    private String namesrvAddr;
////     @PostConstruct //@PostContruct是spring框架的注解，在方法上加该注解会在项目启动的时候执行该方法，也可以理解为在spring容器初始化的时候执行该方法。
//    public void defaultMQProducer() {
//        //生产者的组名
//        DefaultMQProducer producer = new DefaultMQProducer(producerGroup);
//        producer.setCreateTopicKey("AUTO_CREATE_TOPIC_KEY");
//
//        //指定NameServer地址，多个地址以 ; 隔开
//        producer.setNamesrvAddr(namesrvAddr);
//        try {
//            /**
//             * Producer对象在使用之前必须要调用start初始化，初始化一次即可
//             * 注意：切记不可以在每次发送消息时，都调用start方法
//             */
//            producer.start();
//
//            //创建一个消息实例，包含 topic、tag 和 消息体
//            //如下：topic 为 "TopicTest"，tag 为 "push"
////            Message message = new Message("TopicTest", "push", "发送消息----xxxxx-----".getBytes());
//
//            StopWatch stop = new StopWatch();
//            stop.start();
//
//            for (int i = 0; i < 10; i++) {
//                String tags="发送消息----"+i+"-----";
//                Message message = new Message("WebSocketTopic", "push",String.valueOf(i), tags.getBytes());
//                SendResult result = producer.send(message,new MessageQueueSelector() {
//                    @Override
//                    public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
//                        Integer id = (Integer) arg;
//                        int index = id % mqs.size();
//                        return mqs.get(index);
//                    }
//                },1);
////                System.out.println("发送响应：MsgId:" + result.getMsgId() + "，发送状态:" + result.getSendStatus());
//                log.info("发送响应：MsgId:" + result.getMsgId() + "，发送状态:" + result.getSendStatus()+"，tags:"+tags);
//                /**
//                 * 发送响应：MsgId:C0A82B68874C18B4AAC25EA1914C0000，发送状态:SEND_OK
//                 * 发送响应：MsgId:C0A82B68874C18B4AAC25EA1914C0000，发送状态:SEND_OK
//                 * 发送响应：MsgId:C0A82B68874C18B4AAC25EA1914C0000，发送状态:SEND_OK
//                 * 发送响应：MsgId:C0A82B68874C18B4AAC25EA1914C0000，发送状态:SEND_OK
//                 * 发送响应：MsgId:C0A82B68874C18B4AAC25EA1914C0000，发送状态:SEND_OK
//                 * 发送响应：MsgId:C0A82B68874C18B4AAC25EA1914C0000，发送状态:SEND_OK
//                 * 发送响应：MsgId:C0A82B68874C18B4AAC25EA1914C0000，发送状态:SEND_OK
//                 * 发送响应：MsgId:C0A82B68874C18B4AAC25EA1914C0000，发送状态:SEND_OK
//                 * 发送响应：MsgId:C0A82B68874C18B4AAC25EA1914C0000，发送状态:SEND_OK
//                 * 发送响应：MsgId:C0A82B68874C18B4AAC25EA1914C0000，发送状态:SEND_OK
//                 * ----------------发送十条消息耗时：429
//                 */
//                /**
//                 * result.getSendStatus()
//                 * SEND_OK：消息发送成功
//                 * FLUSH_DISK_TIMEOUT：消息发送成功，但是服务器刷盘超时，消息已经进入服务器队列，只有此时服务器宕机，消息才会丢失
//                 * FLUSH_SLAVE_TIMEOUT：消息发送成功，但是服务器同步到 Slave 时超时，消息已经进入服务器队列，只有此时服务器宕机，消息才会丢失
//                 * SLAVE_NOT_AVAILABLE：消息发送成功，但是此时 slave 不可用，消息已经进入服务器队列，只有此时服务器宕机，消息才会丢失。
//                 *        对于精确发送顺序消息的应用，由于顺序消息的局限性，可能会涉及到主备自动切换问题，所以如果sendresult 中的 status 字段不等于 SEND_OK，就应该尝试重试。对于其他应用，则没有必要这样
//                 * */
//            }
//
//            stop.stop();
////            System.out.println("----------------发送十条消息耗时：" + stop.getTotalTimeMillis());
//            log.info("发送十条消息耗时：" + stop.getTotalTimeMillis());
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            producer.shutdown();
//        }
//    }
}
