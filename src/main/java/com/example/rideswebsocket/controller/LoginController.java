package com.example.rideswebsocket.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.rideswebsocket.bean.PUserData;
import com.example.rideswebsocket.util.IpUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("login")
public class LoginController {

    private Log log= LogFactory.getLog(LoginController.class);

    @RequestMapping("/")
    public String index(){
        return "login/index";
    }

    @RequestMapping("registered")
    @ResponseBody
    public String registered(@RequestBody JSONObject resJson, HttpServletResponse response, HttpServletRequest request){
        //{"password":"7","phone":"7","name":"7"}
        String ip= IpUtils.getIpAddr(request);
        log.info("注册请求:"+resJson.toString()+">>注册IP:"+ip);
        PUserData pUserData=new PUserData();
        pUserData.setPhone(resJson.getString("phone"));
        pUserData.setName(resJson.getString("name"));

        pUserData.setLoginName("");
        pUserData.setLoginPassword(DigestUtils.md5DigestAsHex(resJson.getString("password").getBytes()));


        JSONObject jsonObject=new JSONObject();
        jsonObject.put("11","22");
        return jsonObject.toString();
    }
}
