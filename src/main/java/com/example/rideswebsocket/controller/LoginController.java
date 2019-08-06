package com.example.rideswebsocket.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.rideswebsocket.bean.LoginUserInfo;
import com.example.rideswebsocket.bean.PUserData;
import com.example.rideswebsocket.util.GenerateAccountUtils;
import com.example.rideswebsocket.util.IpUtils;
import com.example.rideswebsocket.util.OkHttpUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.authc.UsernamePasswordToken;
//import org.apache.shiro.session.Session;
//import org.apache.shiro.subject.Subject;
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
        pUserData.setLoginName(resJson.getString("phone"));
        pUserData.setLoginPassword(DigestUtils.md5DigestAsHex(resJson.getString("password").getBytes()));

        log.info("注册信息:"+pUserData.toString());

        JSONObject jsonObject=new JSONObject();
        jsonObject.put("status","0000");
        jsonObject.put("msg","注册成功");
        return jsonObject.toString();
    }

    @RequestMapping("index")
    public String index(/*@RequestBody JSONObject resJson, */HttpServletResponse response, HttpServletRequest request){
        String ip= IpUtils.getIpAddr(request);
//        JSONObject resJson=OkHttpUtils.getRequestParams(request);
//        log.info("登入请求:"+resJson.toString()+">>登入IP:"+ip);
//        Subject subject= SecurityUtils.getSubject();
//        Session session=subject.getSession();
//        session.stop();
//        UsernamePasswordToken token=new UsernamePasswordToken("admin","123");
//        try {
//            subject.login(token);
//
//        }catch (Exception e){
//            log.info("登入错误");
//        }
//        PUserData pUserData=new PUserData();
//        pUserData.setLoginName("111");
//        pUserData.setPhone("222");
//        LoginUserInfo loginUserInfo=new LoginUserInfo(pUserData);
//        session.setAttribute("LOGIN_USER_INFO",loginUserInfo);

        return "index";
    }

}
