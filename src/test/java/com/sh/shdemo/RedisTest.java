package com.sh.shdemo;

import com.sh.shdemo.Service.ServiceImpl.CarServiceImpl;
import com.sh.shdemo.Service.ServiceImpl.DriverServiceImpl;
import com.sh.shdemo.dao.CarMsgRepository;
import com.sh.shdemo.dao.DriverMsgRepository;
import com.sh.shdemo.entity.RegMsg;
import com.sh.shdemo.entity.driverMsg;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class RedisTest {
    @Resource
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    CarMsgRepository rmr;
    @Autowired
    DriverMsgRepository dmr;
    @Autowired
    DriverServiceImpl dsi;
    @Autowired
    CarServiceImpl csi;
    @Test
    public void testSet() {
        System.out.println("".isEmpty());
        System.out.println(Base64.getUrlEncoder().encodeToString(("lisi" + ":" + "123").getBytes()));
    }
    @Test
    public void testCache(){
        RegMsg rm = csi.getCarById(5);
        System.out.println(rm.getColor()+rm.getBrand());
    }
}
