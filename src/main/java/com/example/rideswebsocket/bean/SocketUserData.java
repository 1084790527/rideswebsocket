package com.example.rideswebsocket.bean;

//import lombok.*;
//
//@ToString
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class SocketUserData {
    private String serviceIp;
    private String servicePost;
    private String name;

    public SocketUserData() {
    }

    public SocketUserData(String ip, String post, String name) {
        this.servicePost=post;
        this.name=name;
        this.serviceIp=ip;
    }

    @Override
    public String toString() {
        return "SocketUserData{" +
                "serviceIp='" + serviceIp + '\'' +
                ", servicePost='" + servicePost + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getServiceIp() {
        return serviceIp;
    }

    public void setServiceIp(String serviceIp) {
        this.serviceIp = serviceIp;
    }

    public String getServicePost() {
        return servicePost;
    }

    public void setServicePost(String servicePost) {
        this.servicePost = servicePost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
