package com.example.rideswebsocket.mapper;

import com.example.rideswebsocket.bean.PUserData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LoginMapper {

    PUserData selectPUserData(@Param("username") String username);

}
