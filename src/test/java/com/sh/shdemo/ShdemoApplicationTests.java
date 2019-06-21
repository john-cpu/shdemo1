package com.sh.shdemo;

import com.sh.shdemo.dao.SysUserRepository;
import com.sh.shdemo.entity.SysUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShdemoApplicationTests {

    @Autowired
    SysUserRepository sur;

    @Test
    public void contextLoads() {
    }

    @Test
    public void test() throws IOException {
        /*SysUser user = sur.findByUsername("zhangsan");
        System.out.println(user.toString());*/
        Resource resource = new ClassPathResource("static/img");
        String Path = resource.getFile().getPath();
        System.out.println(Path);
    }
}
