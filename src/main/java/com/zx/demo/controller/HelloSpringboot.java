package com.zx.demo.controller;

import com.zx.demo.Config.Json;
import com.zx.demo.Config.PhoneCode;
import com.zx.demo.pojo.*;
import com.zx.demo.server.Empservice;
import com.zx.demo.server.redisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
//处理跨域问题的注解
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/v1")
public class HelloSpringboot {

    public  String codes;


    @Autowired
    private Empservice userService;
    @Autowired
    private redisService redisservice;

    @RequestMapping("/hello/login")
    @ResponseBody
    public String login(@RequestBody Emp emp) {
        int i = userService.login(emp);
        if (i > 0) {
            return "success";
        }
        return "登录失败";
    }

//    @RequestMapping("/hello/fenye")
//    @ResponseBody
//    public Map<String, Object> helloWorld(@RequestBody(required = false) Map<String, Object> all) {
//
//        Integer pageNum = (Integer) all.get("currentPage");
//        Integer pageSize = (Integer) all.get("pageSize");
//        System.out.println("pageNum:" + pageNum + "pageSize:" + pageSize);
//        Map<String, Object> map = userService.getfenye(pageNum, pageSize);
//        return map;
//    }

    @RequestMapping("/hello/select")
    @ResponseBody
    public List select() {
        List list = userService.select();
        System.out.println(userService.select());
        return list;
    }


    @RequestMapping("/hello/insert")
    @ResponseBody
    public boolean inserttable(@RequestBody Emp emp) {

System.err.print(emp.getEaddress()+"swda");
        int i = userService.insert(emp);
        System.out.println("添加了" + i + "条数据");
        if (i == 1) {
            return true;
        } else {
            return false;
        }
    }

    @RequestMapping("/hello/delete")
    @ResponseBody
    public boolean delete(@RequestParam(name = "id") Integer eid) {
        System.out.println(eid+"swww");
        int i = userService.delete(eid);
        if (i > 0) {
            return true;
        } else {
            return false;
        }
    }



    @RequestMapping("/hello/selectbyid")
    @ResponseBody
    public Emp selectbyid(@RequestParam(name = "ids") Integer eid ){
        System.out.println(eid+"swww");
        return userService.selectbyid(eid);

    }

    @RequestMapping("/hello/update")
    @ResponseBody
    public String update(@RequestBody Emp emp ){
        System.out.println(emp.getEpassword()+"密码");
        int i = userService.update(emp);
        if(i>0){
            return "success";
        }
        return "error";

    }


    /**
     * 首先获取全部的省份
     * @param
     * @return
     */
    @RequestMapping(value="/hello/selecctprovince")
    public List<Province> allProvince(){
        return  userService.selecctProvince();
    }

    /**
     * 根据省份获取城市
     * @param id
     * @return
     */
    @RequestMapping(value = "/hello/getCityByProvinceId")
    @ResponseBody
    public Json getCityByProvinceId(@RequestParam("id") String id){
        List<City> cityList = userService.getCityByProvinceId(id);
        if (cityList!=null){
            return new Json(true,"success",cityList);
        } else {
            return new Json(false,"fail",null);
        }
    }

    /**
     * 根据城市获取区域
     * @param id
     * @return
     */
    @RequestMapping(value = "/hello/getAreaByCityId")
    @ResponseBody
    public Json getAreaByCityId(@RequestParam("id") String id){
        List<Area> areaList = userService.getAreaByCityId(id);
        if (areaList!=null){
            System.err.println(areaList+"ssssssssss");
            return new Json(true,"success",areaList);
        } else {
            return new Json(false,"fail",null);
        }
    }

    /*
    查找消息
     */
    @RequestMapping("/hello/selectmessages")
    @ResponseBody
    public List selectmessages() {
        List list = userService.selectmessages();
        System.out.println(userService.selectmessages());
        return list;
    }

    @RequestMapping("/hello/insertmessages")
    @ResponseBody
    public boolean insertmessages(@RequestBody Messages messages) {
        int i = userService.insertmessages(messages);
        System.out.println("添加了" + i + "条数据");
        if (i == 1) {
            return true;
        } else {
            return false;
        }
    }
    @RequestMapping("/hello/selectmessagesbyid")
    @ResponseBody
    public Messages selectmessagesbyid(@RequestParam(name = "id") int mid ){
        System.out.println(mid+"swww");
        return userService.selectmessagesbyid(mid);

    }

    @RequestMapping("/hello/updatemessages")
    @ResponseBody
    public String updatemessages(@RequestBody Messages messages ){
        int i = userService.updatemessages(messages);
        if(i>0){
            return "success";
        }
        return "error";

    }

/*
手机号发送验证码
 */
    @RequestMapping("/hello/yanzhengma")
    @ResponseBody
    public String yanzhengma(@RequestParam(name = "phone") String phone,HttpServletRequest request){
        String s= PhoneCode.getPhonemsg(phone,PhoneCode.vcode());
        //从session中取出来
      //  codes=request.getSession().getAttribute("vcode").toString();
        //从session中取出来放到redis中
        redisservice.setCode("code",request.getSession().getAttribute("vcode").toString());

        // System.out.println(request.getSession().getAttribute("vcode")+"验证码获得"+codes);

        return s;
    }



    //手机号验证码登录
    @RequestMapping("/hello/phonelogin")
    @ResponseBody
    public String login(@RequestParam(name="phone") String phone,@RequestParam(name="code")String code) {
       System.err.println(redisservice.getCode("code")+"redis中验证码储存");
        String  phones = userService.phone(phone);
        //判断手机号为不为空
        if (phones!=null &&!phones.isEmpty()) {
            System.err.println("手机号 "+phones);
            //判断页面上传过来的验证码和redis中是否一致，切不为空
            if(code.equals(redisservice.getCode("code"))&&!redisservice.getCode("code").isEmpty()){
                System.err.println("验证码"+code);
                int i= userService.phonelogin(phone);
                if(i>0){
                    System.err.println("登录成功"+"验证码"+code);
                  return  "success";
                }else {
                    System.err.println("登录失败");
                }
                return "success";
            }else {
                System.err.println("验证码错误");
            }
            return "验证码错误";
        }else{
            System.out.println(phone+"该手机号未注册");
            return "该手机号未注册";
        }


    }

}
