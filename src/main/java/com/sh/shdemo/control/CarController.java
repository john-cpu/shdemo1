package com.sh.shdemo.control;

import com.alibaba.fastjson.JSONArray;
import com.aliyun.openservices.ons.api.*;
import com.sh.shdemo.Service.ServiceImpl.CarServiceImpl;
import com.sh.shdemo.dao.SysUserRepository;
import com.sh.shdemo.entity.RegMsg;
import com.sh.shdemo.entity.SysUser;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
public class CarController {

    @Autowired
    SysUserRepository sur;
    @Autowired
    CarServiceImpl csi;

    @RequestMapping("/")
    public String index(Model model, SecurityContextHolderAwareRequestWrapper request) {
        SysUser user = sur.findByUsername("lisi");
        model.addAttribute("user",user.getUsername()+user.getRoles());
        return "index";
    }

    @ResponseBody
    @RequestMapping("saveCar")
    public RegMsg saveCar(@RequestBody RegMsg cm){
        return csi.save(cm);
    }

    @ResponseBody
    @RequestMapping("/uploadCarFile")
    public String uploadFile(@RequestParam("uploadedfile") MultipartFile file) throws IOException {
        String filePath = file.getOriginalFilename();
        String path = "D:\\ImageFile\\car\\" + filePath;
        File file2 = new File(path);
        file.transferTo(file2);
        return "客户资料上传成功";
    }

    @ResponseBody
    @RequestMapping("updateCar")
    public void updateCar(@RequestBody RegMsg cm){
        csi.save(cm);
    }

    @ResponseBody
    @RequestMapping("getCar")
    public List<RegMsg> getCar(){
        Producer producer = ONSFactory.createProducer(DriverController.getProperties());
        producer.start();
        Message msg = new Message("demo","TagB","CarDriver sending message2".getBytes());
        msg.setKey("ORDERID"+100001);
        try{
            SendResult sr = producer.send(msg);
            if(sr != null){
                System.err.println(new Date() + " Send mq message success. Topic is:" + msg.getTopic() + " msgId is: " + sr.getMessageId());
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.err.println("error");
        }
        Consumer consumer = ONSFactory.createConsumer(DriverController.getProperties());
        consumer.subscribe("demo", "TagA", (Message message, ConsumeContext consumeContext) -> {
                        System.out.println("ReceiveDriver's message: " + message);
                        return Action.CommitMessage;
                    });
        consumer.start();
        List < RegMsg > rlist = csi.getAll();
        return rlist;
    }

    @ResponseBody
    @RequestMapping(value = "getCarByBrandColorCtype",method = RequestMethod.POST)
    public List<RegMsg> getCarByBrandColorCtype(@RequestBody RegMsg rm){
        String nbrand = "%"+rm.getBrand()+"%";
        String ncolor = "%"+rm.getColor()+"%";
        String nctype = "%"+rm.getCtype()+"%";
        List<RegMsg> rlist = csi.getAllLikeBrandColorCtype(nbrand,ncolor,nctype);
        return rlist;
    }

    @ResponseBody
    @GetMapping(value = "getCarById")
    public RegMsg getCarById(@RequestParam("id")long id){
        RegMsg rm = this.csi.getCarById(id);
        return rm;
    }

    @ResponseBody
    @RequestMapping("upCarStateStart")
    public void upCarStateStart(@RequestBody String data){
        if(data != null && data != ""){
            JSONArray ja = JSONArray.parseArray(data);
            for(int i=0;i<ja.size();i++){
                Number num = (Number)ja.getJSONObject(i).get("id");
                this.csi.upStateStart(num.longValue());
            }
        }
    }
    @ResponseBody
    @RequestMapping("upCarStateEnd")
    public void upCarStateEnd(@RequestBody String data){
        if(data != null && data != ""){
            JSONArray ja = JSONArray.parseArray(data);
            for(int i=0;i<ja.size();i++){
                Number num = (Number)ja.getJSONObject(i).get("id");
                this.csi.upStateEnd(num.longValue());
            }
        }
    }
    @ResponseBody
    @RequestMapping("/delete")
    public void delete(@RequestBody String data){
        if(data != null && data != ""){
            JSONArray ja = JSONArray.parseArray(data);
            for(int i=0;i<ja.size();i++) {
                Number num = (Number) ja.getJSONObject(i).get("id");
                this.csi.del(num.longValue());
            }
        }
    }
}
