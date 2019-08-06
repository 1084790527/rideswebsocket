package com.example.rideswebsocket.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.rideswebsocket.bean.LoginUserInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    private Log log= LogFactory.getLog(IndexController.class);

//    @RequestMapping(value = "/403")
//    public String hello(){
//        return "403";
//    }

    @RequestMapping("ttt")
    @ResponseBody
    public String ttt(){
        log.info("---------ttt----------");
        log.info("getLoginName-----------"+LoginUserInfo.getLoginName());
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("login","login");
        return jsonObject.toString();
    }
}
