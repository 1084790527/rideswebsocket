package com.example.rideswebsocket.testController;

import com.alibaba.fastjson.JSONObject;
import com.example.rideswebsocket.util.RedisUtil;
import com.example.rideswebsocket.webSocket.WebSocket;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@Controller
public class TestController {

    private Log log= LogFactory.getLog(TestController.class);

    @Autowired
    private RedisUtil redisUtil;

    @Value("${server.port}")
    private String post;

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
        model.addAttribute("socketUrl","ws://127.0.0.1:"+post+"/webSocket/"+name+"/"+ip+"/"+post);
        return "webSoket";
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

}
