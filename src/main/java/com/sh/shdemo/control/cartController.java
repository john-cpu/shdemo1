package com.sh.shdemo.control;

import com.sh.shdemo.dao.RegMsgRepository;
import com.sh.shdemo.entity.RegMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("user")
public class cartController {
    @Autowired
    RegMsgRepository rmr;

    @RequestMapping("doRegister")
    public String register(RegMsg rm, HttpServletRequest request, @RequestParam(value="imgfile",required = true) MultipartFile file) throws IOException {
        if(file.isEmpty()){
            return "myerror";
        }
        String uuid = UUID.randomUUID().toString().substring(1,8);
        String path = "D:\\ImageFile\\"+rm.getUsername()+"-"+uuid;
        file.transferTo(new File(path));
        rm.setImgPath(path);
        rmr.save(rm);
        return "ojbk";
    }
}
