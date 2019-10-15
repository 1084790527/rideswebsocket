package com.example.rideswebsocket.testController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.dubbo_service_dome1.service.TestService;
import com.example.rideswebsocket.bean.PUserData;
import com.example.rideswebsocket.util.OkHttpUtils;
import com.example.rideswebsocket.util.RedisUtils;
import com.example.rideswebsocket.webSocket.WebSocket;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.rocketmq.common.utils.IOTinyUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.IOUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Controller
//@PropertySource("classpath:application.properties")
public class TestController {

    private Log log= LogFactory.getLog(TestController.class);

    @Autowired
    private RedisUtils redisUtil;

    @Value("${server.port}")
    private String port;

    @Value("${server.ip}")
    private String ip;

//    @Reference
//    TestService testService;

    @RequestMapping(value = "/" ,method = RequestMethod.GET)
    @ResponseBody
    public String hello(){
        log.info("testDubbo");

//        return testService.dubbo("000");
        return "dsdsd";
    }

    @RequestMapping(value = "webSocket")
    public String webSoket(Model model, HttpServletRequest request){
//        log.info("post:"+post);
        String name=request.getParameter("name");
//        log.info("ControllerName:"+name);
        model.addAttribute("socketUrl","ws://"+ip+":"+port+"/webSocket/"+name+"/"+ip+"/"+port);//ws://127.0.0.1:8080/webSocket/555/127.0.0.1/8080
        return "webSocket/webSocket";
    }

    @RequestMapping("redis")
    @ResponseBody
    public String redis(HttpServletRequest request){
        return String.valueOf(redisUtil.set(request.getParameter("k"),request.getParameter("v")));
    }

//    @Test
//    public void attt(){
//        JSONObject object=new JSONObject();
//        object.put("342","dasd");
//        object.put("etwertyre","dasd");
//        System.out.println(object.toString());
//
//        Map<String,String> map=new HashMap<>();
//        map.put("sfdasf","14234");
//        map.put("342","dasd");
//        System.out.println(map.toString());
//
//        System.out.println(JSONObject.toJSONString(map));
//    }


    @RequestMapping("redisL")
    @ResponseBody
    public String redisL(HttpServletRequest request){

        JSONObject jsonObject=new JSONObject();
        jsonObject.put("2","3");
        jsonObject.put("4","5");

        List<Object> list=new ArrayList();
//        list.add(request.getParameter("v"));
//        list.add(request.getParameter("v"));
//        list.add(request.getParameter("v"));
        list.add(jsonObject.toString());
        list.add(jsonObject.toString());
        list.add(jsonObject.toString());

        //实体类转JSONString
//        JSONObject.toJSONString()
        //实体类
//        javaBean = JSON.parseObject(json, 实体类.class);

        return String.valueOf(redisUtil.lSet(request.getParameter("k"),request.getParameter("v")));
    }


    @RequestMapping("getL")
    @ResponseBody
    public String getL(HttpServletRequest request){
        List<Object> objects=redisUtil.lGet(request.getParameter("k"),0,-1);
        for (Object o:objects){
            log.info(o);
            JSONObject jsonObject=JSONObject.parseObject(o.toString());
            log.info(jsonObject.toString());
        }
        return objects.toString();
    }

    @RequestMapping("send")
    @ResponseBody
    public String send(HttpServletRequest request){
        WebSocket.onSendMass(request.getParameter("v"));
        return "";
    }

    @RequestMapping("okHttpTest")
    @ResponseBody
    public String okHttpTest(){
        return OkHttpUtils.get("http://www.baidu.com");
    }

    @RequestMapping("test")
//    @ResponseBody
    public void test(HttpServletRequest request, HttpServletResponse response /*, @RequestBody String json*/) throws IOException {

//        log.info(json);
//        Map<String,String> map=new HashMap<>();


//        for (String s:json.split("&")) {
//            String[] t=s.split("=");
//            map.put(t[0],t[1]);
//        }
//        log.info(JSON.toJSONString(map));
//        Arrays.stream(json.split("&"))
//                .filter(kv -> kv.contains("="))
//                .map(kv -> kv.split("="))
//                .forEach(array -> map.put(array[0], array[1]));
//        log.info(JSON.toJSONString(map));

//        Map<String,String[]> map=request.getParameterMap();
//        log.info(JSON.toJSONString(map));

//        Map<String,String> stringMap=new HashMap<>();
//        for (Map.Entry<String, String[]> set:map.entrySet()){
//            stringMap.put(set.getKey(),set.getValue()[0]);
//        }
//        log.info(stringMap.toString());
//
//        Map<String,String> map1 = new HashMap<>();
//        for (String key:map.keySet()){
//            map1.put(key,map.get(key)[0]);
//        }
//        log.info(map1.toString());

//        Enumeration e1 = request.getHeaderNames();
//        while (e1.hasMoreElements()) {
//            String headerName = (String) e1.nextElement();
//            String headValue = request.getHeader(headerName);
//            log.info(headerName + "=" + headValue);
//        }
//        return "{\"t\":\"tt\"}";
//    }

    }

    @Test
    public void test(){
//        System.out.println(UUID.randomUUID().toString().replaceAll("-",""));
//        JSONObject jsonObject=new JSONObject();
//        jsonObject.put("data","666");
//        OkHttpUtils.postJsonParams("http://"+String.valueOf(o)+"/",jsonObject.toJSONString());
        System.out.println(new Date().getTime());

        LocalDate localDate = LocalDate.now();
        Timestamp timestamp= Timestamp.valueOf(LocalDateTime.now());
        System.out.println(timestamp.getTime());

        System.out.println(String.valueOf((int) (System.currentTimeMillis() / 1000)));
    }

//    更改使用RMQ
//    @RequestMapping(value = "websocketSend",method = RequestMethod.POST)
//    @ResponseBody
//    public String websocketSend(@RequestBody JSONObject jsonObject){
//
//        log.info("获取请求请求的数据："+jsonObject.toString());
//        WebSocket.onSendMass(jsonObject.getString("name")+"发送："+jsonObject.getString("message"));
//
//        Map<String ,String> map=new ConcurrentHashMap<>();
//        map.put("","");
//
//
//        return "";
//    }


    @Test
    public void tttt() throws InterruptedException {
//        for (int i=0;i<10;i++){
//            int finalI = i;
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
////                        tt tt1=new tt();
//////                        tt1.txt(finalI);
////                        tt.txt(finalI);
//                        synchronized (tt.class){
////                            tt tt1=new tt();
////                            tt1.txt(finalI);
//                            tt.txt(finalI);
//                        }
////                        tt.txt(finalI);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }).start();
//        }
//
//        Thread.sleep(10000000);
//        LocalDate today = LocalDate.now();
//        today = today.minusMonths(1);
//        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyyMM");
//        System.out.println(formatters.format(today));
        Map<String,String> map=new HashMap<>();
        map.put("4","d");
        System.out.println("dsds-----"+map.get("5"));

        JSONObject jsonObject=new JSONObject();
        jsonObject.put("x","x");
        jsonObject.put("v","2.908");
        System.out.println("dsdsd----"+jsonObject.getString("5"));
        System.out.println(jsonObject.getDouble("v"));
        System.out.println(jsonObject.getFloat("v"));
        System.out.println(jsonObject.getLong("v"));
        System.out.println(jsonObject.getInteger("v"));
    }

    public static void txt(int i) throws InterruptedException {
        System.out.println("----------"+i+"--------");
        Thread.sleep(1000);
        System.out.println(i);
        System.out.println("---------"+i+"---------");
    }

    @Test
    public void ttttt() throws Exception {
//        Map<String,String> map=new HashMap<>();
//        map.put("aioppdsf","a");
//        map.put("cpopsdoajof","c");
//        map.put("epkfmdksk","e");
//        map.put("odisnfiksni","o");
//        map.put("bsdsnciue","b");
//        map.put("gdfevomevf","g");
//        map.put("ddgregrvsw","d");
//        map.put("fgrbyrtd","f");
//        System.out.println("1-->"+map.toString());
//        Arrays.sort(map.keySet().toArray());
//        System.out.println("2-->"+map.toString());

//        String s=new BASE64Encoder().encode("asdabjhsbkd".getBytes("UTF-8"));
//        System.out.println(s);
//
//        String b=Base64.encodeBase64String("asdabjhsbkd".getBytes("UTF-8"));
//        System.out.println(b);
//
//        String u=java.util.Base64.getEncoder().encodeToString("asdabjhsbkd".getBytes("UTF-8"));
//        System.out.println(u);
//
//        System.out.println(new String(java.util.Base64.getDecoder().decode(u),"utf-8"));

        //     /** RSA算法要求有一个可信任的随机数源 */  
            //     SecureRandom secureRandom = new SecureRandom();  
//            /** 为RSA算法创建一个KeyPairGenerator对象 */
//        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
//
//        /** 利用上面的随机数据源初始化这个KeyPairGenerator对象 */
//        //     keyPairGenerator.initialize(KEYSIZE, secureRandom);  
//        keyPairGenerator.initialize(1024);
//
//        /** 生成密匙对 */
//        KeyPair keyPair = keyPairGenerator.generateKeyPair();
//
//        /** 得到公钥 */
//        Key publicKey = keyPair.getPublic();
//
//        /** 得到私钥 */
//        Key privateKey = keyPair.getPrivate();
//
//        System.out.println(new java.lang.String(java.util.Base64.getEncoder().encode(publicKey.getEncoded())));
//        System.out.println(new java.lang.String(java.util.Base64.getEncoder().encode(privateKey.getEncoded())));

//        int i= ((Double)(Double.valueOf("1.225")*100)).intValue();
//        System.out.println(i);
//        int x= (int)(Double.valueOf("1.225")*100);
//        System.out.println(x);
//        BigDecimal b=BigDecimal.valueOf(1.225).multiply(BigDecimal.valueOf(100));
//        System.out.println(b.intValue());

//        String s="<OrderFrom></OrderFrom>\n" +
//                "        <Description>Description</Description>\n" +
//                "        <OrderType?00</OrderType>\n" +
//                "        <OrderState>02</OrderState>\n";
//        String t=s.replaceAll("\\?",">");
//        System.out.println(t);

//        String s="\\";
//        System.out.println(s);
//        System.out.println(s.replaceAll("\\\\","--"));

//        PUserData pUserData=new PUserData();
//        pUserData.setLoginName("888");
//        pUserData.setLoginPassword("777");
//        System.out.println(pUserData.toString());
//        System.out.println(JSON.toJSONString(pUserData));

//        PUserData p1=new PUserData();
//        PUserData p2=null;
//        p1.setLoginName("22");
//        p2=p1;
//        System.out.println(p2.getLoginName());
//        p2.setLoginName("33");
//        System.out.println(p2.getLoginName());
//        System.out.println(p1.getLoginName());
//        p1.setLoginName("44");
//        System.out.println(p2.getLoginName());
//        System.out.println(p1.getLoginName());
//        tt(p2);
//        System.out.println(p2.getLoginName());
//        System.out.println(p1.getLoginName());

//        String s="   oo    pp   ";
//        System.out.println(s);
//        System.out.println(s.replaceAll(" ",""));

        int[] zz={9,7,4,6,8,1,3,2,5};
        for (int i:zz){
            System.out.print(i);
        }
        System.out.println();

        for (int i=1;i<zz.length;i++){
            int b=i;
            for (int t=i-1;t>=0;t--){
                if (zz[b]<zz[t]){
                    int x=zz[b];
                    zz[b]=zz[t];
                    zz[t]=x;
                    b--;
                }
            }
            for (int z:zz){
                System.out.print(z);
            }
            System.out.println();
        }

        for (int i:zz){
            System.out.print(i);
        }

        System.out.println();
    }

    public void tt(PUserData pUserData){
        pUserData.setLoginName("rrr");
    }

}
//class  tt{
//    public static void txt(int i) throws InterruptedException {
//        System.out.println("----------"+i+"--------");
//        Thread.sleep(1000);
//        System.out.println(i);
//        System.out.println("---------"+i+"---------");
//    }
//}