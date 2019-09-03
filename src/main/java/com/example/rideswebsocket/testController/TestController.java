package com.example.rideswebsocket.testController;

import com.alibaba.fastjson.JSONObject;
import com.example.rideswebsocket.util.OkHttpUtils;
import com.example.rideswebsocket.util.RedisUtils;
import com.example.rideswebsocket.webSocket.WebSocket;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
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
        model.addAttribute("socketUrl","ws://"+ip+":"+port+"/webSocket/"+name+"/"+ip+"/"+port);//ws://127.0.0.1:8080/webSocket/555/127.0.0.1/8080
        return "webSocket/webSocket";
    }

    @RequestMapping("redis")
    @ResponseBody
    public String redis(HttpServletRequest request){
        return String.valueOf(redisUtil.set(request.getParameter("k"),request.getParameter("v")));
    }

//    @Test
//    public void attt(){
//        JSONObject object=new JSONObject();
//        object.put("342","dasd");
//        object.put("etwertyre","dasd");
//        System.out.println(object.toString());
//
//        Map<String,String> map=new HashMap<>();
//        map.put("sfdasf","14234");
//        map.put("342","dasd");
//        System.out.println(map.toString());
//
//        System.out.println(JSONObject.toJSONString(map));
//    }


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
        WebSocket.onSendMass(request.getParameter("v"));
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
//        WebSocket.onSendMass(jsonObject.getString("name")+"发送："+jsonObject.getString("message"));
//
//        Map<String ,String> map=new ConcurrentHashMap<>();
//        map.put("","");
//
//
//        return "";
//    }


    @Test
    public void tttt() throws InterruptedException {
//        for (int i=0;i<10;i++){
//            int finalI = i;
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
////                        tt tt1=new tt();
//////                        tt1.txt(finalI);
////                        tt.txt(finalI);
//                        synchronized (tt.class){
////                            tt tt1=new tt();
////                            tt1.txt(finalI);
//                            tt.txt(finalI);
//                        }
////                        tt.txt(finalI);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }).start();
//        }
//
//        Thread.sleep(10000000);
    }

    public static void txt(int i) throws InterruptedException {
        System.out.println("----------"+i+"--------");
        Thread.sleep(1000);
        System.out.println(i);
        System.out.println("---------"+i+"---------");
    }

    @Test
    public void ttttt() throws UnsupportedEncodingException {
//        Map<String,String> map=new HashMap<>();
//        map.put("aioppdsf","a");
//        map.put("cpopsdoajof","c");
//        map.put("epkfmdksk","e");
//        map.put("odisnfiksni","o");
//        map.put("bsdsnciue","b");
//        map.put("gdfevomevf","g");
//        map.put("ddgregrvsw","d");
//        map.put("fgrbyrtd","f");
//        System.out.println("1-->"+map.toString());
//        Arrays.sort(map.keySet().toArray());
//        System.out.println("2-->"+map.toString());

        String s=new BASE64Encoder().encode("asdabjhsbkd".getBytes("UTF-8"));
        System.out.println(s);

        String b=Base64.encodeBase64String("asdabjhsbkd".getBytes("UTF-8"));
        System.out.println(b);

        String u=java.util.Base64.getEncoder().encodeToString("asdabjhsbkd".getBytes("UTF-8"));
        System.out.println(u);

        System.out.println(new String(java.util.Base64.getDecoder().decode(u),"utf-8"));
    }

}
//class  tt{
//    public static void txt(int i) throws InterruptedException {
//        System.out.println("----------"+i+"--------");
//        Thread.sleep(1000);
//        System.out.println(i);
//        System.out.println("---------"+i+"---------");
//    }
}