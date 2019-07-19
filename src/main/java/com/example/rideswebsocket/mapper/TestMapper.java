package com.example.rideswebsocket.mapper;


import com.example.rideswebsocket.bean.TestUserData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectKey;

import java.util.List;
import java.util.Map;

@Mapper
public interface TestMapper {

    public List<TestUserData> selectUserDataList();

    public Integer installUserData(@Param("testUserData") TestUserData testUserData);
}
