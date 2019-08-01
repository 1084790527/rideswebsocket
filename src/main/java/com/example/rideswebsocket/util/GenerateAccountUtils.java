package com.example.rideswebsocket.util;

public class GenerateAccountUtils {

    public static String getAccount(){
        String account="";
        for (int i=0;i<10;i++){
            account+=(int)(Math.random()*10);
        }
        return account;
    }
}
