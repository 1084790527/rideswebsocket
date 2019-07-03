package com.example.rideswebsocket.webSocket;

import com.alibaba.fastjson.JSONObject;
import com.example.rideswebsocket.bean.SocketUserData;
import com.example.rideswebsocket.util.RedisUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @ServerEndpoint 注解是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务器端,
 * 注解的值将被用于监听用户连接的终端访问URL地址,客户端可以通过这个URL来连接到WebSocket服务器端
 */
@Component
@ServerEndpoint(value = "/webSocket/{name}/{ip}/{post}")
public class WebSocket {
    private static Log log= LogFactory.getLog(WebSocket.class);

//    @Autowired
    private static RedisUtil redisUtil=new RedisUtil();

//    private static RedisUtil(RedisUtil redisUtil){
//        WebSocket.redisUtil=redisUtil;
//    }

    /**
     * concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
     * 若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
     */
    private static CopyOnWriteArraySet<WebSocket> webSocketSet=new CopyOnWriteArraySet<>();


    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
//    private static int onlineCount  = 0;


    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    private String name;

    private SocketUserData socketUserData;



    /**
     * 连接建立成功调用的方法
     * @param session  可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("name") String name,@PathParam("ip") String ip,@PathParam("post") String post) {
//        log.info("webSocketName:"+name);
        this.session = session;
        this.name=name;
        webSocketSet.add(this); //加入set中
//        addOnlineCount(); //在线数加1
//        log.info("ip:"+ip+",post:"+post);

        //判断是否以存在
        List<Object> list=redisUtil.lGet("webSocket",0,-1);
        for (Object o:list){
//            log.info(jsonObject.toString());
            SocketUserData userData=JSONObject.parseObject(String.valueOf(o),SocketUserData.class);
            if (name.equals(userData.getName())){
//                log.info(JSONObject.toJSONString(userData));
                redisUtil.lRemove("webSocket",1,JSONObject.toJSONString(userData));
            }
        }

        socketUserData=new SocketUserData(ip,post,name);
        redisUtil.lSet("webSocket",JSONObject.toJSONString(socketUserData));
        log.info(name+"连接加入！当前在线人数为" + getOnlineCount());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this); //从set中删除
//        subOnlineCount(); //在线数减1
        log.info(JSONObject.toJSONString(socketUserData));
        redisUtil.lRemove("webSocket",-1,JSONObject.toJSONString(socketUserData));
        log.info(name+"关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     * @param message 客户端发送过来的消息
     * @param session 可选的参数
     */
    @OnMessage
    public void onMessage(String message, Session session) {
//        message="来自"+name+"的消息:" + message;
//        log.info(message);
//        //群发消息
//        for (WebSocket item : webSocketSet) {
//            try {
//                item.sendMessage(message);
//            } catch (IOException e) {
//                e.printStackTrace();
//                continue;
//            }
//        }
    }

    public static synchronized void onSend(String message){
        for (WebSocket item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }
    }

    /**
     * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException {
        String respMsg = message;
        //同步
        this.session.getBasicRemote().sendText(respMsg);
        //异步
        //this.session.getAsyncRemote().sendText(message);
    }

    /**
     * 发生错误时调用
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        redisUtil.lRemove("webSocket",-1,JSONObject.toJSONString(socketUserData));
        log.debug("发生错误");
        error.printStackTrace();
    }


    public static synchronized int getOnlineCount() {
        return (int)redisUtil.lGetListSize("webSocket");
    }

//    public static synchronized void addOnlineCount() {
//        onlineCount++;
//    }
//
//    public static synchronized void subOnlineCount() {
//        onlineCount--;
//    }


}
