package com.example.rideswebsocket.mapper;


import com.example.rideswebsocket.bean.TestUserData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TestMapper {

    public List<TestUserData> selectUserDataList();

    public int installUserData(Map<String,String> userMap);
}
