package com.example.rideswebsocket.rocketMQ;


import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
//import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
//import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
//import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
//import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
//import com.alibaba.rocketmq.common.message.MessageExt;

@Service
public class RocketMQConsumer {
    private Log log= LogFactory.getLog(RocketMQConsumer.class);
    /**
     * 消费者的组名
     */
    @Value("${apache.rocketmq.consumer.PushConsumer}")
    private String consumerGroup;

    /**
     * NameServer 地址
     */
    @Value("${apache.rocketmq.namesrvAddr}")
    private String namesrvAddr;

     @PostConstruct //@PostContruct是spring框架的注解，在方法上加该注解会在项目启动的时候执行该方法，也可以理解为在spring容器初始化的时候执行该方法。
    public void defaultMQPushConsumer() {
        //消费者的组名
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(consumerGroup);

        //指定NameServer地址，多个地址以 ; 隔开
        consumer.setNamesrvAddr(namesrvAddr);
        try {
            //订阅PushTopic下Tag为push的消息
            consumer.subscribe("WebSocketTopic", "push");

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
                    }
                    /**
                     * messageExt: MessageExt [queueId=1, storeSize=191, queueOffset=1, sysFlag=0, bornTimestamp=1563497718090, bornHost=/192.168.169.1:62866, storeTimestamp=1563497718091, storeHost=/192.168.169.1:10911, msgId=C0A8A90100002A9F00000000000000BF, commitLogOffset=191, bodyCRC=476929171, reconsumeTimes=0, preparedTransactionOffset=0, toString()=Message{topic='TopicTest', flag=0, properties={MIN_OFFSET=0, MAX_OFFSET=10, CONSUME_START_TIME=1563497859316, UNIQ_KEY=C0A82B688A5018B4AAC25E9C8CFD0000, WAIT=true, TAGS=push}, body=[-27, -113, -111, -23, -128, -127, -26, -74, -120, -26, -127, -81, 45, 45, 45, 45, 122, 104, 105, 115, 104, 101, 110, 103, 45, 45, 45, 45, 45], transactionId='null'}]
                     * messageExt: MessageExt [queueId=1, storeSize=191, queueOffset=5, sysFlag=0, bornTimestamp=1563497718098, bornHost=/192.168.169.1:62866, storeTimestamp=1563497718099, storeHost=/192.168.169.1:10911, msgId=C0A8A90100002A9F00000000000003BB, commitLogOffset=955, bodyCRC=476929171, reconsumeTimes=0, preparedTransactionOffset=0, toString()=Message{topic='TopicTest', flag=0, properties={MIN_OFFSET=0, MAX_OFFSET=10, CONSUME_START_TIME=1563497859316, UNIQ_KEY=C0A82B688A5018B4AAC25E9C8CFD0000, WAIT=true, TAGS=push}, body=[-27, -113, -111, -23, -128, -127, -26, -74, -120, -26, -127, -81, 45, 45, 45, 45, 122, 104, 105, 115, 104, 101, 110, 103, 45, 45, 45, 45, 45], transactionId='null'}]
                     * messageExt: MessageExt [queueId=1, storeSize=191, queueOffset=4, sysFlag=0, bornTimestamp=1563497718096, bornHost=/192.168.169.1:62866, storeTimestamp=1563497718098, storeHost=/192.168.169.1:10911, msgId=C0A8A90100002A9F00000000000002FC, commitLogOffset=764, bodyCRC=476929171, reconsumeTimes=0, preparedTransactionOffset=0, toString()=Message{topic='TopicTest', flag=0, properties={MIN_OFFSET=0, MAX_OFFSET=10, CONSUME_START_TIME=1563497859316, UNIQ_KEY=C0A82B688A5018B4AAC25E9C8CFD0000, WAIT=true, TAGS=push}, body=[-27, -113, -111, -23, -128, -127, -26, -74, -120, -26, -127, -81, 45, 45, 45, 45, 122, 104, 105, 115, 104, 101, 110, 103, 45, 45, 45, 45, 45], transactionId='null'}]
                     * messageExt: MessageExt [queueId=1, storeSize=191, queueOffset=3, sysFlag=0, bornTimestamp=1563497718094, bornHost=/192.168.169.1:62866, storeTimestamp=1563497718096, storeHost=/192.168.169.1:10911, msgId=C0A8A90100002A9F000000000000023D, commitLogOffset=573, bodyCRC=476929171, reconsumeTimes=0, preparedTransactionOffset=0, toString()=Message{topic='TopicTest', flag=0, properties={MIN_OFFSET=0, MAX_OFFSET=10, CONSUME_START_TIME=1563497859316, UNIQ_KEY=C0A82B688A5018B4AAC25E9C8CFD0000, WAIT=true, TAGS=push}, body=[-27, -113, -111, -23, -128, -127, -26, -74, -120, -26, -127, -81, 45, 45, 45, 45, 122, 104, 105, 115, 104, 101, 110, 103, 45, 45, 45, 45, 45], transactionId='null'}]
                     * messageExt: MessageExt [queueId=1, storeSize=191, queueOffset=0, sysFlag=0, bornTimestamp=1563497718014, bornHost=/192.168.169.1:62866, storeTimestamp=1563497718076, storeHost=/192.168.169.1:10911, msgId=C0A8A90100002A9F0000000000000000, commitLogOffset=0, bodyCRC=476929171, reconsumeTimes=0, preparedTransactionOffset=0, toString()=Message{topic='TopicTest', flag=0, properties={MIN_OFFSET=0, MAX_OFFSET=10, CONSUME_START_TIME=1563497859316, UNIQ_KEY=C0A82B688A5018B4AAC25E9C8CFD0000, WAIT=true, TAGS=push}, body=[-27, -113, -111, -23, -128, -127, -26, -74, -120, -26, -127, -81, 45, 45, 45, 45, 122, 104, 105, 115, 104, 101, 110, 103, 45, 45, 45, 45, 45], transactionId='null'}]
                     * messageExt: MessageExt [queueId=1, storeSize=191, queueOffset=8, sysFlag=0, bornTimestamp=1563497718104, bornHost=/192.168.169.1:62866, storeTimestamp=1563497718105, storeHost=/192.168.169.1:10911, msgId=C0A8A90100002A9F00000000000005F8, commitLogOffset=1528, bodyCRC=476929171, reconsumeTimes=0, preparedTransactionOffset=0, toString()=Message{topic='TopicTest', flag=0, properties={MIN_OFFSET=0, MAX_OFFSET=10, CONSUME_START_TIME=1563497859316, UNIQ_KEY=C0A82B688A5018B4AAC25E9C8CFD0000, WAIT=true, TAGS=push}, body=[-27, -113, -111, -23, -128, -127, -26, -74, -120, -26, -127, -81, 45, 45, 45, 45, 122, 104, 105, 115, 104, 101, 110, 103, 45, 45, 45, 45, 45], transactionId='null'}]
                     * messageExt: MessageExt [queueId=1, storeSize=191, queueOffset=7, sysFlag=0, bornTimestamp=1563497718102, bornHost=/192.168.169.1:62866, storeTimestamp=1563497718103, storeHost=/192.168.169.1:10911, msgId=C0A8A90100002A9F0000000000000539, commitLogOffset=1337, bodyCRC=476929171, reconsumeTimes=0, preparedTransactionOffset=0, toString()=Message{topic='TopicTest', flag=0, properties={MIN_OFFSET=0, MAX_OFFSET=10, CONSUME_START_TIME=1563497859316, UNIQ_KEY=C0A82B688A5018B4AAC25E9C8CFD0000, WAIT=true, TAGS=push}, body=[-27, -113, -111, -23, -128, -127, -26, -74, -120, -26, -127, -81, 45, 45, 45, 45, 122, 104, 105, 115, 104, 101, 110, 103, 45, 45, 45, 45, 45], transactionId='null'}]
                     * messageExt: MessageExt [queueId=1, storeSize=191, queueOffset=6, sysFlag=0, bornTimestamp=1563497718100, bornHost=/192.168.169.1:62866, storeTimestamp=1563497718101, storeHost=/192.168.169.1:10911, msgId=C0A8A90100002A9F000000000000047A, commitLogOffset=1146, bodyCRC=476929171, reconsumeTimes=0, preparedTransactionOffset=0, toString()=Message{topic='TopicTest', flag=0, properties={MIN_OFFSET=0, MAX_OFFSET=10, CONSUME_START_TIME=1563497859316, UNIQ_KEY=C0A82B688A5018B4AAC25E9C8CFD0000, WAIT=true, TAGS=push}, body=[-27, -113, -111, -23, -128, -127, -26, -74, -120, -26, -127, -81, 45, 45, 45, 45, 122, 104, 105, 115, 104, 101, 110, 103, 45, 45, 45, 45, 45], transactionId='null'}]
                     * messageExt: MessageExt [queueId=1, storeSize=191, queueOffset=2, sysFlag=0, bornTimestamp=1563497718092, bornHost=/192.168.169.1:62866, storeTimestamp=1563497718093, storeHost=/192.168.169.1:10911, msgId=C0A8A90100002A9F000000000000017E, commitLogOffset=382, bodyCRC=476929171, reconsumeTimes=0, preparedTransactionOffset=0, toString()=Message{topic='TopicTest', flag=0, properties={MIN_OFFSET=0, MAX_OFFSET=10, CONSUME_START_TIME=1563497859316, UNIQ_KEY=C0A82B688A5018B4AAC25E9C8CFD0000, WAIT=true, TAGS=push}, body=[-27, -113, -111, -23, -128, -127, -26, -74, -120, -26, -127, -81, 45, 45, 45, 45, 122, 104, 105, 115, 104, 101, 110, 103, 45, 45, 45, 45, 45], transactionId='null'}]
                     * messageExt: MessageExt [queueId=1, storeSize=191, queueOffset=9, sysFlag=0, bornTimestamp=1563497718106, bornHost=/192.168.169.1:62866, storeTimestamp=1563497718107, storeHost=/192.168.169.1:10911, msgId=C0A8A90100002A9F00000000000006B7, commitLogOffset=1719, bodyCRC=476929171, reconsumeTimes=0, preparedTransactionOffset=0, toString()=Message{topic='TopicTest', flag=0, properties={MIN_OFFSET=0, MAX_OFFSET=10, CONSUME_START_TIME=1563497859316, UNIQ_KEY=C0A82B688A5018B4AAC25E9C8CFD0000, WAIT=true, TAGS=push}, body=[-27, -113, -111, -23, -128, -127, -26, -74, -120, -26, -127, -81, 45, 45, 45, 45, 122, 104, 105, 115, 104, 101, 110, 103, 45, 45, 45, 45, 45], transactionId='null'}]
                     * 消费响应：msgId : C0A82B688A5018B4AAC25E9C8CFD0000,  msgBody : 发送消息----zhisheng-----
                     * 消费响应：msgId : C0A82B688A5018B4AAC25E9C8CFD0000,  msgBody : 发送消息----zhisheng-----
                     * 消费响应：msgId : C0A82B688A5018B4AAC25E9C8CFD0000,  msgBody : 发送消息----zhisheng-----
                     * 消费响应：msgId : C0A82B688A5018B4AAC25E9C8CFD0000,  msgBody : 发送消息----zhisheng-----
                     * 消费响应：msgId : C0A82B688A5018B4AAC25E9C8CFD0000,  msgBody : 发送消息----zhisheng-----
                     * 消费响应：msgId : C0A82B688A5018B4AAC25E9C8CFD0000,  msgBody : 发送消息----zhisheng-----
                     * 消费响应：msgId : C0A82B688A5018B4AAC25E9C8CFD0000,  msgBody : 发送消息----zhisheng-----
                     * 消费响应：msgId : C0A82B688A5018B4AAC25E9C8CFD0000,  msgBody : 发送消息----zhisheng-----
                     * 消费响应：msgId : C0A82B688A5018B4AAC25E9C8CFD0000,  msgBody : 发送消息----zhisheng-----
                     * 消费响应：msgId : C0A82B688A5018B4AAC25E9C8CFD0000,  msgBody : 发送消息----zhisheng-----
                     */
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

}
