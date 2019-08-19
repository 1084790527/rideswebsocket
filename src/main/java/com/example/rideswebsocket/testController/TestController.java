package com.example.rideswebsocket.testController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.rideswebsocket.bean.TestUserData;
import com.example.rideswebsocket.util.OkHttpUtils;
import com.example.rideswebsocket.util.RedisUtils;
import com.example.rideswebsocket.webSocket.WebSocket;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


@Controller
//@PropertySource("classpath:application.properties")
public class TestController {

    private Log log= LogFactory.getLog(TestController.class);

    @Autowired
    private RedisUtils redisUtil;

    @Value("${server.port}")
    private String port;

    @Value("${server.ip}")
    private String ip;

    @RequestMapping(value = "/" ,method = RequestMethod.GET)
    public String hello(){
        return "hello";
    }

    @RequestMapping(value = "webSocket")
    public String webSoket(Model model, HttpServletRequest request){
//        log.info("post:"+post);
        String name=request.getParameter("name");
//        log.info("ControllerName:"+name);
        model.addAttribute("socketUrl","ws://"+ip+":"+port+"/webSocket/"+name+"/"+ip+"/"+port);
        return "webSocket/webSocket";
    }

    @RequestMapping("redis")
    @ResponseBody
    public String redis(HttpServletRequest request){
        return String.valueOf(redisUtil.set(request.getParameter("k"),request.getParameter("v")));
    }


    @RequestMapping("redisL")
    @ResponseBody
    public String redisL(HttpServletRequest request){

        JSONObject jsonObject=new JSONObject();
        jsonObject.put("2","3");
        jsonObject.put("4","5");

        List<Object> list=new ArrayList();
//        list.add(request.getParameter("v"));
//        list.add(request.getParameter("v"));
//        list.add(request.getParameter("v"));
        list.add(jsonObject.toString());
        list.add(jsonObject.toString());
        list.add(jsonObject.toString());

        //实体类转JSONString
//        JSONObject.toJSONString()
        //实体类
//        javaBean = JSON.parseObject(json, 实体类.class);

        return String.valueOf(redisUtil.lSet(request.getParameter("k"),request.getParameter("v")));
    }


    @RequestMapping("getL")
    @ResponseBody
    public String getL(HttpServletRequest request){
        List<Object> objects=redisUtil.lGet(request.getParameter("k"),0,-1);
        for (Object o:objects){
            log.info(o);
            JSONObject jsonObject=JSONObject.parseObject(o.toString());
            log.info(jsonObject.toString());
        }
        return objects.toString();
    }

    @RequestMapping("send")
    @ResponseBody
    public String send(HttpServletRequest request){
        WebSocket.onSend(request.getParameter("v"));
        return "";
    }

    @RequestMapping("okHttpTest")
    @ResponseBody
    public String okHttpTest(){
        return OkHttpUtils.get("http://www.baidu.com");
    }


//    @Test
//    public void test(){
//        System.out.println(UUID.randomUUID().toString().replaceAll("-",""));
//        JSONObject jsonObject=new JSONObject();
//        jsonObject.put("data","666");
//        OkHttpUtils.postJsonParams("http://"+String.valueOf(o)+"/",jsonObject.toJSONString());
//    }

//    更改使用RMQ
//    @RequestMapping(value = "websocketSend",method = RequestMethod.POST)
//    @ResponseBody
//    public String websocketSend(@RequestBody JSONObject jsonObject){
//
//        log.info("获取请求请求的数据："+jsonObject.toString());
//        WebSocket.onSend(jsonObject.getString("name")+"发送："+jsonObject.getString("message"));
//
//        Map<String ,String> map=new ConcurrentHashMap<>();
//        map.put("","");
//
//
//        return "";
//    }


    @Test
    public void tttt() throws InterruptedException {
        for (int i=0;i<10;i++){
            int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
//                        tt tt1=new tt();
////                        tt1.txt(finalI);
//                        tt.txt(finalI);
                        synchronized (tt.class){
//                            tt tt1=new tt();
//                            tt1.txt(finalI);
                            tt.txt(finalI);
                        }
//                        tt.txt(finalI);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        Thread.sleep(10000000);
    }

    public static void txt(int i) throws InterruptedException {
        System.out.println("----------"+i+"--------");
        Thread.sleep(1000);
        System.out.println(i);
        System.out.println("---------"+i+"---------");
    }


}
class  tt{
    public static void txt(int i) throws InterruptedException {
        System.out.println("----------"+i+"--------");
        Thread.sleep(1000);
        System.out.println(i);
        System.out.println("---------"+i+"---------");
    }
}