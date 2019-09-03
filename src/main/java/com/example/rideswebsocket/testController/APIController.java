package com.example.rideswebsocket.testController;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("api")
public class APIController {
//    private Log log= LogFactory.getLog(APIController.class);
//
//    @RequestMapping(value = "login",method = RequestMethod.POST)
//    @ResponseBody
//    public String login(HttpServletRequest request,@RequestBody JSONObject object){
//        //{"password":"425","username":"ter"}
//        log.info("login："+object.toString());
//
//        JSONObject jsonObject=new JSONObject();
//        jsonObject.put("status","success");
//        JSONObject data=new JSONObject();
//        data.put("authToken","1");
//        data.put("userId",object.getString("username"));
//        jsonObject.put("data",data);
//        return jsonObject.toString();
//    }

}
