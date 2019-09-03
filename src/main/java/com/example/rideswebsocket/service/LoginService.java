package com.example.rideswebsocket.service;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;

public interface LoginService {

    JSONObject login(JSONObject data, HttpServletRequest request);
}
