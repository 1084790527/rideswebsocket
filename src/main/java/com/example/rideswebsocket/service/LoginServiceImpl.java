package com.example.rideswebsocket.service;

import com.alibaba.fastjson.JSONObject;
import com.example.rideswebsocket.bean.PUserData;
import com.example.rideswebsocket.mapper.LoginMapper;
import com.example.rideswebsocket.util.DateUtils;
import com.example.rideswebsocket.util.IpUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class LoginServiceImpl implements LoginService {
    private Log log= LogFactory.getLog(LoginServiceImpl.class);

    @Autowired
    private LoginMapper loginMapper;

    @Override
    public JSONObject login(JSONObject data, HttpServletRequest request) {
        String ip= IpUtils.getIpAddr(request);
        Date date=new Date();
        log.info("用户登入-->"+data.getString("username")+"登入ip："+ip+"  登入时间："+ DateUtils.getFormateDate(date));
        //{"password":"425","username":"ter"}
        PUserData userData=loginMapper.selectPUserData(data.getString("username"));
        JSONObject retObject=new JSONObject();
        if (userData==null){
            //用户信息不存在
            retObject.put("status","fail");
            return retObject;
        }
        if (!data.getString("password").equals(userData.getLoginPassword())){
            //用户密码错误
            retObject.put("status","fail");
            return retObject;
        }
        retObject.put("status","success");
        JSONObject d=new JSONObject();
        d.put("authToken","1");
        d.put("userId",userData.getName());
        retObject.put("data",d);
        return retObject;
    }
}
