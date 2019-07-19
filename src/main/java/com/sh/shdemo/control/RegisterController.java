package com.sh.shdemo.control;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sh.shdemo.Service.ServiceImpl.DriverServiceImpl;
import com.sh.shdemo.Service.ServiceImpl.CarServiceImpl;
import com.sh.shdemo.entity.RegMsg;
import com.sh.shdemo.entity.driverMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("user")
public class RegisterController {
    @Autowired
    DriverServiceImpl dsi;
    @Autowired
    CarServiceImpl csi;

    @RequestMapping("carRegister")
    public String register(@Validated RegMsg cm, BindingResult bindingResult, @RequestParam(value="imgfile",required = true) MultipartFile upfile,ModelMap mm) throws IOException {
        Map<String,Object> map = new HashMap<>();
        if(bindingResult.hasErrors()){
           /* List<FieldError> errors = bindingResult.getFieldErrors();
            for(FieldError fe:errors){
                map.put(fe.getField(),fe.getDefaultMessage());
            }*/
            mm.addAttribute("error","所有信息都不能为空");
            return "/user/carManage";
        }
        String path = "D:\\ImageFile\\car\\" ;
        String path2 = path + upfile.getOriginalFilename();
        File file2 = new File(path2);
        cm.setImgPath(path2);
        cm.setState("启用");
        if(csi.save(cm) != null){
            upfile.transferTo(file2);
        }
        return "redirect:/user/cm";
    }
    @RequestMapping("driverRegister")
    public String driverRegister(driverMsg dm,@RequestParam(value="imgfile",required = true) MultipartFile upfile) throws IOException {
        String path = "D:\\ImageFile\\driver\\" ;
        String path2 = path + upfile.getOriginalFilename();
        File file2 = new File(path2);
        dm.setImagePath(path2);
        dm.setState("启用");
        if(dsi.save(dm)!=null){
            upfile.transferTo(file2);
        }
        return "redirect:/user/dm";
    }
    @RequestMapping("cm")
    public String cm(){
        return "/user/carMsg";
    }

    @RequestMapping("dm")
    public String dm(){
        return "/user/driverMsg";
    }

    @RequestMapping(value = "dmsg")
    @ResponseBody
    public String dmsg(@RequestParam("page")int page,@RequestParam("limit")int limit,@RequestParam(value="username",required = false,defaultValue = "")String name,@RequestParam(value="tel",required = false,defaultValue = "")String tel,@RequestParam(value="study",required = false,defaultValue = "")String study){
        String nname = '%'+name+'%';
        String ntel = '%'+tel+'%';
        String nstudy = '%'+study+'%';
        List<driverMsg> dlist = dsi.getPageByPageLimitNameTelStudy(nname,ntel,nstudy,page*limit-limit,limit);
        List<driverMsg> list = dsi.getAllByNameTelStudy(nname,ntel,nstudy);
        JSONObject jo = new JSONObject();
        jo.put("code",0);
        jo.put("msg","");
        jo.put("count",list.size());
        jo.put("data",dlist);
        return jo.toJSONString();
    }

    @RequestMapping(value = "cmsg")
    @ResponseBody
    public String cmsg(@RequestParam(value = "page")int page,@RequestParam(value = "limit")int limit,@RequestParam(value="brand",required = false,defaultValue = "")String brand,@RequestParam(value="color",required = false,defaultValue = "")String color,@RequestParam(value="ctype",required = false,defaultValue = "")String ctype){
        List<RegMsg> clist = csi.getPageByBrandColorCtype(brand,color,ctype,page*limit-limit,limit);
        List<RegMsg> list = csi.getPageByBrandColorCtypeAll(brand,color,ctype);
        JSONObject jo = new JSONObject();
        jo.put("code",0);
        jo.put("msg","");
        jo.put("count",list.size());
        jo.put("data",clist);
        return jo.toJSONString();
    }
    @RequestMapping("upDmStateEnd")
    @ResponseBody
    public String upDmStateEnd(@RequestBody String data){
        if(data != null && data != ""){
            JSONArray ja = JSONArray.parseArray(data);
            for(int i=0;i<ja.size();i++){
                Number num = (Number)ja.getJSONObject(i).get("id");
                driverMsg dm = dsi.getDriverById(num.longValue());
                dm.setState("禁用");
                dsi.save(dm);
            }
        }
        return "ok";
    }
    @RequestMapping("upDmStateStart")
    @ResponseBody
    public String upDmStateStart(@RequestBody String data){
        if(data != null && data != ""){
            JSONArray ja = JSONArray.parseArray(data);
            for(int i=0;i<ja.size();i++){
                Number num = (Number)ja.getJSONObject(i).get("id");
                driverMsg dm = dsi.getDriverById(num.longValue());
                dm.setState("启用");
                dsi.save(dm);
            }
        }
        return "ok";
    }
    @RequestMapping("delDm")
    @ResponseBody
    public String delDm(@RequestBody String data){
        if(data != null && data != ""){
            JSONArray ja = JSONArray.parseArray(data);
            for(int i=0;i<ja.size();i++){
                Number num = (Number)ja.getJSONObject(i).get("id");
                dsi.del(num.longValue());
            }
        }
        return "ok";
    }

    @RequestMapping("upCmStateEnd")
    @ResponseBody
    public String upCmStateEnd(@RequestBody String data){
        if(data != null && data != ""){
            JSONArray ja = JSONArray.parseArray(data);
            for(int i=0;i<ja.size();i++){
                Number num = (Number)ja.getJSONObject(i).get("id");
                RegMsg cm = csi.getCarById(num.longValue());
                cm.setState("禁用");
                csi.save(cm);
            }
        }
        return "ok";
    }
    @RequestMapping("upCmStateStart")
    @ResponseBody
    public String upCmStateStart(@RequestBody String data){
        if(data != null && data != ""){
            JSONArray ja = JSONArray.parseArray(data);
            for(int i=0;i<ja.size();i++){
                Number num = (Number)ja.getJSONObject(i).get("id");
                RegMsg cm = csi.getCarById(num.longValue());
                cm.setState("启用");
                csi.save(cm);
            }
        }
        return "ok";
    }
    @RequestMapping("delCm")
    @ResponseBody
    public String delCm(@RequestBody String data){
        if(data != null && data != ""){
            JSONArray ja = JSONArray.parseArray(data);
            for(int i=0;i<ja.size();i++){
                Number num = (Number)ja.getJSONObject(i).get("id");
                csi.del(num.longValue());
            }
        }
        return "ok";
    }

    @RequestMapping("edit")
    @ResponseBody
    public int edit(@RequestBody String data){
        if(data != null && data != ""){
            JSONArray ja = JSONArray.parseArray(data);
            if(ja.size()==1){
                int id = (int)ja.getJSONObject(0).get("id");
                return id;
            }
        }
        return 0;
    }

    @RequestMapping("carEdit")
    @ResponseBody
    public int carEdit(@RequestBody String data){
        if(data != null && data != ""){
            JSONArray ja = JSONArray.parseArray(data);
            if(ja.size()==1){
                int id = (int)ja.getJSONObject(0).get("id");
                return id;
            }
        }
        return 0;
    }
    @RequestMapping("getDriverMsg")
    public String getDriverMsg(@RequestParam("id") int id,ModelMap mm){
        Long id2 = (long)id;
        driverMsg dm = dsi.getDriverById(id2);
        mm.addAttribute("dm",dm);
        return "/user/driverManage";
    }

    @RequestMapping("upDriverMsg")
    public String upDriverMsg(driverMsg dm, Model model, ModelMap mmp, @RequestParam(value="imgfile",required = true) MultipartFile upfile)throws IOException{
        String path = "D:\\ImageFile\\driver\\" ;
        String path2 = path + upfile.getOriginalFilename();
/*        File file1 = new File(path);*/
        File file2 = new File(path2);
        dm.setImagePath(path2);
        if(dsi.save(dm)!=null && !file2.exists()){
            upfile.transferTo(file2);
            return "redirect:/user/dm";
        }else if(file2.exists()){
            return "redirect:/user/dm";
        }
        long id = dm.getId();
        driverMsg dm2 = dsi.getDriverById(id);
        mmp.addAttribute("dm",dm2);
        return "redirect:/user/dm";
    }

    @RequestMapping("getCarMsg")
    public String getCarMsg(@RequestParam("id") int id,ModelMap mm){
        Long id2 = (long)id;
        RegMsg cm = csi.getCarById(id2);
        mm.addAttribute("cm",cm);
        return "/user/carManage";
    }
    @RequestMapping("upCarMsg")
    public String upCarMsg(@Valid RegMsg cm, Model model, ModelMap mmp, @RequestParam(value="imgfile",required = true) MultipartFile upfile)throws IOException{
        String path = "D:\\ImageFile\\car\\" ;
        String path2 = path + upfile.getOriginalFilename();
        File file2 = new File(path2);
        cm.setImgPath(path2);
        if(csi.save(cm)!=null && !file2.exists()){
            upfile.transferTo(file2);
            return "redirect:/user/cm";
        }else if(file2.exists()){
            return "redirect:/user/cm";
        }
        long id = cm.getId();
        RegMsg cm2 = csi.getCarById(id);
        mmp.addAttribute("cm",cm2);
        return "redirect:/user/cm";
    }
}
