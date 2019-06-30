package com.sh.shdemo.control;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sh.shdemo.Util.pageUtil;
import com.sh.shdemo.dao.RegMsgRepository;
import com.sh.shdemo.dao.driverMsgRepository;
import com.sh.shdemo.entity.RegMsg;
import com.sh.shdemo.entity.driverMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("user")
public class RegisterController {
    int oneCount = 11;
    @Autowired
    RegMsgRepository rmr;
    @Autowired
    driverMsgRepository dmr;

    @RequestMapping("cartRegister")
    public String register(RegMsg rm, HttpServletRequest request, @RequestParam(value="imgfile",required = true) MultipartFile upfile,ModelMap mm) throws IOException {
        if(upfile.isEmpty()){
            return "carManage";
        }
        String path = "D:\\ImageFile\\car\\" ;
        String path2 = path + upfile.getOriginalFilename();
        File file1 = new File(path);
        if(!file1.exists()){
            file1.mkdir();
        }
        File file2 = new File(path2);
        rm.setImgPath(path2);
        rm.setState("启用");
        if(rmr.save(rm) != null){
            upfile.transferTo(file2);
        }
        List<RegMsg> rlist = rmr.findAll();
        mm.addAttribute("rlist",rlist);
        return "/user/cartMsg";
    }
    @RequestMapping("driverRegister")
    public String driverRegister(driverMsg dm, HttpServletRequest request, @RequestParam(value="imgfile",required = true) MultipartFile upfile) throws IOException {
        if(upfile.isEmpty()){
            return "driverManage.ftl";
        }
        String path = "D:\\ImageFile\\driver\\" ;
        String path2 = path + upfile.getOriginalFilename();
        File file1 = new File(path);
        if(!file1.exists()){
            file1.mkdir();
        }
        File file2 = new File(path2);
        dm.setImagePath(path2);
        if(dmr.save(dm)!=null){
            upfile.transferTo(file2);
        }
        return "/user/driverMsg";
    }
    @RequestMapping("free")
    public String free(@RequestParam(value="page",required = true,defaultValue = "0")String pg, ModelMap modelMap,HttpServletRequest request){

        int page = Integer.parseInt(pg);
        List<RegMsg> rlist = rmr.getAll();
        int countPage = pageUtil.countPage(rlist.size(),oneCount);
        if(page == 0){
            page = 1;
        }
        if(page>countPage){
            page = countPage;
        }
        List<RegMsg> list2 = rmr.getPage(page*oneCount-oneCount,oneCount);
        modelMap.addAttribute("rlist",list2);
        modelMap.addAttribute("cur",page);
        modelMap.addAttribute("countPage",countPage);
        return "/user/cartMsg";

    }

    @RequestMapping("cm")
    public String cm(){
        return "/user/carMsg";
    }

    @RequestMapping("dm")
    public String dm(){
        return "/user/driverMsg";
    }

    @RequestMapping(value = "select",method = RequestMethod.POST)
    public String select(@RequestParam(value="page",required = true,defaultValue = "0")String pg, @RequestParam("brand")String brand, @RequestParam("color")String color, @RequestParam("ctype")String ctype, ModelMap mm, HttpServletRequest request){
        int page = Integer.parseInt(pg);
        List<RegMsg> list = rmr.select(brand,color,ctype);
        /*int countPage = pageUtil.countPage(list.size(),oneCount);
        if(page == 0){
            page = 1;
        }
        if(page>countPage){
            page = countPage;
        }
        List<RegMsg> rlist = rmr.selectLimit(brand,color,ctype,page*11-11,11);*/
        mm.addAttribute("rlist",list);
        /*mm.addAttribute("cur",1);
        mm.addAttribute("countPage",countPage);*/
        return "/user/cartMsg";
    }
    @RequestMapping(value = "upStateStart"/*,method = RequestMethod.POST*/)
    @ResponseBody
    public String upStateStart(@RequestParam("needId")String str, ModelMap mm){
        List<RegMsg> rlist = rmr.getAll();
        int countPage = pageUtil.countPage(rlist.size(),11);
        String[] str2 = str.split("#");
        for(String num : str2){
            Integer id = Integer.parseInt(num);
            rmr.upStateStart(id);
        }
        List<RegMsg> list2 = rmr.getPage(1,11);
        mm.addAttribute("rlist",list2);
        mm.addAttribute("cur",1);
        mm.addAttribute("countPage",countPage);
        return "/user/cartMsg";
    }
    /*@RequestMapping(value = "upStateEnd",method = RequestMethod.POST)
    public String upStateEnd(@RequestParam(value="needId",required = true,defaultValue = "0")String str, ModelMap mm){
        List<RegMsg> rlist = rmr.getAll();
        int countPage = pageUtil.countPage(rlist.size(),11);
        String[] str2 = str.split("#");
        for(String num : str2){
            Integer id = Integer.parseInt(num);
            rmr.upStateEnd(id);
        }
        List<RegMsg> list2 = rmr.getPage(1,11);
        mm.addAttribute("rlist",list2);
        mm.addAttribute("cur",1);
        mm.addAttribute("countPage",countPage);
        return "/user/cartMsg";
    }*/
    @RequestMapping(value = "delById",method = RequestMethod.POST)
    public String delById(@RequestParam(value="needId",required = true,defaultValue = "0")String str, ModelMap mm){
        List<RegMsg> rlist = rmr.getAll();
        int countPage = pageUtil.countPage(rlist.size(),11);
        String[] str2 = str.split("#");
        for(String num : str2){
           Integer id = Integer.parseInt(num);
           rmr.del(id);
        }
        List<RegMsg> list2 = rmr.getPage(1,11);
        mm.addAttribute("rlist",list2);
        mm.addAttribute("cur",1);
        mm.addAttribute("countPage",countPage);
        return "/user/cartMsg";
    }
    @RequestMapping(value = "dmo")
    @ResponseBody
    public List<RegMsg> dmo(@RequestParam("page")String pg,ModelMap mm){
        int page = Integer.parseInt(pg);
        System.out.println(page);
        List<RegMsg> rlist = rmr.getPage(2,page);
        return rlist;
    }

    @RequestMapping(value = "dmsg")
    @ResponseBody
    public String dmsg(@RequestParam("page")int page,@RequestParam("limit")int limit,@RequestParam(value="username",required = false,defaultValue = "")String name,@RequestParam(value="tel",required = false,defaultValue = "")String tel,@RequestParam(value="study",required = false,defaultValue = "")String study){
        List<driverMsg> dlist = new ArrayList<>();
        List<driverMsg> list = new ArrayList<>();
        if(name.isEmpty()&&tel.isEmpty()&&study.isEmpty()){//都为空
            dlist = dmr.getPage(page*limit-limit,limit);
            list = dmr.getAll();
        }else if(!name.isEmpty()&&tel.isEmpty()&&study.isEmpty()){//name不为空
            dlist = dmr.getPageByName(name,page*limit-limit,limit);
            list = dmr.getPageByNameAll(name);
        }
        else if(name.isEmpty()&&!tel.isEmpty()&&study.isEmpty()){//tel不为空
            dlist = dmr.getPageByTel(tel,page*limit-limit,limit);
            list = dmr.getPageByTelAll(tel);
        }
        else if(name.isEmpty()&&tel.isEmpty()&&!study.isEmpty()){//study不为空
            dlist = dmr.getPageByStudy(study,page*limit-limit,limit);
            list = dmr.getPageByStudyAll(study);
        }
        else if(!name.isEmpty()&&!tel.isEmpty()&&study.isEmpty()){//name 和 tel 不为空
            dlist = dmr.getPageByNameTel(name,tel,page*limit-limit,limit);
            list = dmr.getPageByNameTelAll(name,tel);
        }else if(!name.isEmpty()&&tel.isEmpty()&&!study.isEmpty()) {//name 和 study不为空
            dlist = dmr.getPageByNameStudy(name, study, page * limit - limit, limit);
            list = dmr.getPageByNameStudyAll(name, study);
        } else if(name.isEmpty()&&!tel.isEmpty()&&!study.isEmpty()) {//name 和 study不为空
            dlist = dmr.getPageByTelStudy(tel, study, page * limit - limit, limit);
            list = dmr.getPageByTelStudyAll(tel, study);
        }else if(!name.isEmpty()&&!tel.isEmpty()&&!study.isEmpty()){//都不为空
            dlist = dmr.getPageByNameTelStudy(name,tel,study,page*limit-limit,limit);
            list = dmr.getPageByNameTelStudyAll(name,tel,study);
        }
        JSONObject jo = new JSONObject();
        jo.put("code",0);
        jo.put("msg","");
        jo.put("count",list.size());
        jo.put("data",dlist);
        return jo.toJSONString();
    }

    @RequestMapping(value = "cmsg")
    @ResponseBody
    public String cmsg(@RequestParam("page")int page,@RequestParam("limit")int limit,@RequestParam(value="brand",required = false,defaultValue = "")String brand,@RequestParam(value="color",required = false,defaultValue = "")String color,@RequestParam(value="ctype",required = false,defaultValue = "")String ctype){
        List<RegMsg> clist = new ArrayList<>();
        List<RegMsg> list = new ArrayList<>();
        if(brand.isEmpty()&&color.isEmpty()&&ctype.isEmpty()){
            clist = rmr.getPage(page*limit-limit,limit);
            list = rmr.getAll();
        }else{
            clist = rmr.getPageByBrandColorCtype(brand,color,ctype,page*limit-limit,limit);
            list = rmr.getPageByBrandColorCtypeAll(brand,color,ctype);
        }
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
                int id = (int)ja.getJSONObject(i).get("id");
                dmr.upStateEnd(id);
            }
        }
        return "ok";
    }
    @RequestMapping("upDmStateStart")
    @ResponseBody
    public String upStateStart(@RequestBody String data){
        if(data != null && data != ""){
            JSONArray ja = JSONArray.parseArray(data);
            for(int i=0;i<ja.size();i++){
                int id = (int)ja.getJSONObject(i).get("id");
                dmr.upStateStart(id);
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
                int id = (int)ja.getJSONObject(i).get("id");
                dmr.del(id);
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
                int id = (int)ja.getJSONObject(i).get("id");
                rmr.upStateEnd(id);
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
                int id = (int)ja.getJSONObject(i).get("id");
                rmr.upStateStart(id);
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
                int id = (int)ja.getJSONObject(i).get("id");
                rmr.del(id);
            }
        }
        return "ok";
    }
    @RequestMapping("rg")
    @ResponseBody
    public String rg(){
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
        return 0;//此处可否用"redirct:/user/edit"代替
    }
    @RequestMapping("getDriverMsg")
    public String getDriverMsg(@RequestParam("id") int id,ModelMap mm){
        Long id2 = (long)id;
        driverMsg dm = dmr.getOne(id2);
        mm.addAttribute("dm",dm);
        return "/user/driverManage";
    }
    @RequestMapping("getCarMsg")
    public String getCarMsg(@RequestParam("id") int id,ModelMap mm){
        Long id2 = (long)id;
        RegMsg rm = rmr.getOne(id2);
        mm.addAttribute("cm",rm);
        return "/user/carManage";
    }
    @RequestMapping("upDriverMsg")
    public String getDriverMsg(driverMsg dm, Model model,ModelMap mmp,@RequestParam(value="imgfile",required = true) MultipartFile upfile)throws IOException{
        if(upfile.isEmpty()){
            return "/user/driverManage";
        }
        String path = "D:\\ImageFile\\driver\\" ;
        String path2 = path + upfile.getOriginalFilename();
        File file1 = new File(path);
        if(!file1.exists()){
            file1.mkdir();
        }
        File file2 = new File(path2);
        dm.setImagePath(path2);
        if(dmr.save(dm)!=null && !file2.exists()){
            upfile.transferTo(file2);
            model.addAttribute("msg","修改成功");
            return "redirect:/user/dm";
        }else if(file2.exists()){
            model.addAttribute("msg","修改成功");
            return "redirect:/user/dm";
        }
        long id = dm.getId();
        driverMsg dm2 = dmr.getOne(id);
        mmp.addAttribute("dm",dm2);
        return "redirect:/user/dm";
    }
    @RequestMapping("upCarMsg")
    public String upCarMsg(RegMsg rm, Model model,ModelMap mmp,@RequestParam(value="imgfile",required = true) MultipartFile upfile)throws IOException{
        if(upfile.isEmpty()){
            return "/user/carManage";
        }
        String path = "D:\\ImageFile\\car\\" ;
        String path2 = path + upfile.getOriginalFilename();
        File file1 = new File(path);
        if(!file1.exists()){
            file1.mkdir();
        }
        File file2 = new File(path2);
        rm.setImgPath(path2);
        if(rmr.save(rm)!=null && !file2.exists()){
            upfile.transferTo(file2);
            model.addAttribute("msg","修改成功");
            return "redirect:/user/cm";
        }else if(file2.exists()){
            model.addAttribute("msg","修改成功");
            return "redirect:/user/cm";
        }
        long id = rm.getId();
        RegMsg rm2 = rmr.getOne(id);
        mmp.addAttribute("cm",rm2);
        return "redirect:/user/cm";
    }
}
