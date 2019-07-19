package com.example.rideswebsocket.testController;

import com.example.rideswebsocket.mapper.TestMapper;
import com.example.rideswebsocket.service.TestService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class MybiatsController {
    private Log log= LogFactory.getLog(MybiatsController.class);


    @Autowired
    private TestService testService;

    @RequestMapping("myS")
    @ResponseBody
    public String testMybits(){
        return testService.selectUserDataList().toString();
    }

    @RequestMapping("myAdd")
    @ResponseBody
    public String myAdd(){
        return String.valueOf(testService.add());
    }


}
