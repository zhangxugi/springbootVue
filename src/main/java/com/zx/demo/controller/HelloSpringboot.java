package com.zx.demo.controller;

import com.zx.demo.pojo.Emp;
import com.zx.demo.server.Empservice;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;


@Controller
//处理跨域问题的注解
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/v1")
public class HelloSpringboot {
    @Autowired
    private Empservice userService;

    @RequestMapping("/hello/login")
    @ResponseBody
    public String login(@RequestBody Emp emp) {
        int i = userService.login(emp);
        if (i > 0) {
            return "success";
        }
        return "error";
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
        System.out.println(list.size());
        return list;
    }


    @RequestMapping("/hello/insert")
    @ResponseBody
    public boolean inserttable(@RequestBody Emp emp) {


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

}
