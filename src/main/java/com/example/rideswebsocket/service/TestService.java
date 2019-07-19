package com.example.rideswebsocket.service;

import com.example.rideswebsocket.bean.TestUserData;
import com.example.rideswebsocket.mapper.TestMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TestService {

    private Log log= LogFactory.getLog(TestService.class);

    @Autowired
    private TestMapper testMapper;

    @Transactional   //事务管理
    public int add(){
        Map<String,String> userMap=new HashMap<>();
        userMap.put("name","mmm");
        int i=testMapper.installUserData(userMap);
        log.info(i);
        int a = 1 / 0; //模拟故障
        userMap.put("name","ccc");
        i=testMapper.installUserData(userMap);
        log.info(i);
        return 1;
    }

    public List<TestUserData> selectUserDataList() {
        return testMapper.selectUserDataList();
    }
}
