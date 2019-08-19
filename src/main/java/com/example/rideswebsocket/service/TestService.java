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
        TestUserData data=new TestUserData();
        data.setName("ppp");
        Integer i=testMapper.installUserData(data);
        log.info(i);
        log.info(data.toString()); //返回添加数据库的id  TestUserData{id='29', name='ppp'}
//        int a = 1 / 0; //模拟故障
//        userMap.put("name","ccc");
//        i=testMapper.installUserData(userMap);
//        log.info(i);
        return Integer.parseInt(data.getId());
    }

    public List<TestUserData> selectUserDataList() {
        return testMapper.selectUserDataList();
    }
}

















