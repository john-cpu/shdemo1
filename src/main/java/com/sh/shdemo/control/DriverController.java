package com.sh.shdemo.control;

import com.alibaba.fastjson.JSONArray;
import com.aliyun.openservices.ons.api.*;
import com.sh.shdemo.Service.ServiceImpl.DriverServiceImpl;
import com.sh.shdemo.dao.SysUserRepository;
import com.sh.shdemo.entity.driverMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

@Controller
public class DriverController {
    @Autowired
    SysUserRepository sur;
    @Autowired
    DriverServiceImpl dsi;

    public static Properties getProperties(){
        Properties properties = new Properties();
        properties.setProperty(PropertyKeyConst.GROUP_ID,"GID_demo");
        properties.setProperty(PropertyKeyConst.AccessKey,"LTAIfIBNWUB1UmO6");
        properties.setProperty(PropertyKeyConst.SecretKey,"AiFTCFrlEc4zNb55x5C7aU6E9Fhvo9");
        properties.setProperty(PropertyKeyConst.NAMESRV_ADDR,"http://MQ_INST_1724210197420215_Ba8ykKvU.mq-internet-access.mq-internet.aliyuncs.com:80");
        properties.setProperty(PropertyKeyConst.SendMsgTimeoutMillis,"3000");
        return properties;
    }
    @ResponseBody
    @RequestMapping("saveDriver")
    public driverMsg saveDriver(@RequestBody driverMsg dm){
        return dsi.save(dm);
    }

    @ResponseBody
    @RequestMapping("uploadDriverFile")
    public String uploadFile(@RequestParam("uploadedfile") MultipartFile file) throws IOException {
        String filePath = file.getOriginalFilename();
        String path = "D:\\ImageFile\\driver\\" + filePath;
        File file2 = new File(path);
        file.transferTo(file2);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("updateDriver")
    public void updateDriver(@RequestBody driverMsg dm){
        dsi.save(dm);
    }

    @ResponseBody
    @RequestMapping("getDriver")
    public List<driverMsg> getDriver(){
        List<driverMsg> rlist = dsi.getAll();
        // 生产
        Producer producer = ONSFactory.createProducer(getProperties());
        producer.start();
        Message msg = new Message("demo","TagA","I am getDriver ing2".getBytes());
        msg.setKey("ORDERID_" + 111112);
        try{
            SendResult sr = producer.send(msg);
            if (sr != null) {
                System.out.println(new Date() + " Send mq message success. Topic is:" + msg.getTopic() + " msgId is: " + sr.getMessageId());
            }
        }catch (Exception e){
            System.err.println("Some error happen");
        }
        // 消费
        Consumer consumer = ONSFactory.createConsumer(getProperties());
        consumer.subscribe("demo","TagB",(Message ms,ConsumeContext cc) -> {
            System.out.println("driverController"+ ms);
            return Action.CommitMessage;
        });
        consumer.start();
        return rlist;
    }

    @ResponseBody
    @RequestMapping(value = "getDriverByNameTelStudy",method = RequestMethod.POST)
    public List<driverMsg> getDriverByBrandColorCtype(@RequestBody driverMsg dm){
        String nname = "%"+dm.getUsername()+"%";
        String ntel = "%"+dm.getTel()+"%";
        String nstudy = "%"+dm.getStudy()+"%";
        List<driverMsg> rlist = dsi.getAllByNameTelStudy(nname,ntel,nstudy);
        return rlist;
    }

    @ResponseBody
    @GetMapping(value = "getDriverById")
    public driverMsg getDriverById(@RequestParam("id")long id){
        driverMsg rm = this.dsi.getDriverById(id);
        return rm;
    }

    @ResponseBody
    @RequestMapping("upDriverStateStart")
    public void upDriverStateStart(@RequestBody String data){
        if(data != null && data != ""){
            JSONArray ja = JSONArray.parseArray(data);
            for(int i=0;i<ja.size();i++){
                Number num = (Number)ja.getJSONObject(i).get("id");
                this.dsi.upDriverStateStart(num.longValue());
            }
        }
    }
    @ResponseBody
    @RequestMapping("upDriverStateEnd")
    public void upDriverStateEnd(@RequestBody String data){
        if(data != null && data != ""){
            JSONArray ja = JSONArray.parseArray(data);
            for(int i=0;i<ja.size();i++){
                Number num = (Number)ja.getJSONObject(i).get("id");
                this.dsi.upDriverStateEnd(num.longValue());
            }
        }
    }
    @ResponseBody
    @RequestMapping("deleteDriver")
    public void delete(@RequestBody String data){
        if(data != null && data != ""){
            JSONArray ja = JSONArray.parseArray(data);
            for(int i=0;i<ja.size();i++) {
                Number num = (Number) ja.getJSONObject(i).get("id");
                this.dsi.del(num.longValue());
            }
        }
    }
}
