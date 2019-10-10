package com.example.rideswebsocket.dubbo;

//import com.alibaba.dubbo.config.annotation.Service;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

//@Service(version = "1.0.0",timeout = 3000)
//@Component
public class TestServiceImp implements TestService {
    private Log log= LogFactory.getLog(TestService.class);
    @Override
    public void testDubbo() {
        log.info("dubbo----------------");
    }
}
