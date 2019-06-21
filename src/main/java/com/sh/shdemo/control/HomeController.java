package com.sh.shdemo.control;

import com.sh.shdemo.dao.RegMsgRepository;
import com.sh.shdemo.dao.SysUserRepository;
import com.sh.shdemo.entity.Msg;
import com.sh.shdemo.entity.RegMsg;
import com.sh.shdemo.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;


@Controller
public class HomeController {
    @Autowired
    SysUserRepository sur;

    @RequestMapping("/")
    public String index(Model model, SecurityContextHolderAwareRequestWrapper request) {
        Msg msg = new Msg("测试标题", "测试内容", "额外信息，只对管理员显示");
        model.addAttribute("msg", msg);
        SysUser user = sur.findByUsername("lisi");
        model.addAttribute("user",user.getUsername()+user.getRoles());
        return "index";
    }
    @RequestMapping("admin")
    public String admin(){
        return "redirect:/AdminPage/a";
    }
}
