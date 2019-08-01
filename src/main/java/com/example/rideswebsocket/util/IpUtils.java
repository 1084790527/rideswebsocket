package com.example.rideswebsocket.util;

import javax.servlet.http.HttpServletRequest;

public class IpUtils {
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip) || ip.equals("127.0.0.1")) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip) || ip.equals("127.0.0.1")) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip) || ip.equals("127.0.0.1")) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip) || ip.equals("127.0.0.1")) {
            ip = request.getRemoteAddr();
        }
        if (ip.indexOf("0:") != -1) {
            ip = "127.0.0.1";
        }
        return ip;
    }
}
