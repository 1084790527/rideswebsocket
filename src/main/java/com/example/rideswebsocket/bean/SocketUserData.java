package com.example.rideswebsocket.bean;

public class SocketUserData {
    private String serviceIp;
    private String servicePost;
    private String name;

    public SocketUserData(){

    }

    public SocketUserData(String serviceIp,String servicePost,String name){
        this.serviceIp=serviceIp;
        this.servicePost=servicePost;
        this.name=name;
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
