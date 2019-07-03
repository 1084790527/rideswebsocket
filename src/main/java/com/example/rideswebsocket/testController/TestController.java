package com.example.rideswebsocket.testController;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestController {

    private Log log= LogFactory.getLog(TestController.class);


    @Value("${server.port}")
    private String post;

    @RequestMapping(value = "/" ,method = RequestMethod.GET)
    public String hello(){
        return "hello";
    }

    @RequestMapping(value = "webSocket")
    public String webSoket(Model model){
//        log.info("post:"+post);
        model.addAttribute("socketUrl","ws://127.0.0.1:"+post+"/webSocket");
        return "webSoket";
    }

}
