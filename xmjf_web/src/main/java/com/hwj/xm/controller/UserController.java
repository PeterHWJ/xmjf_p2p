package com.hwj.xm.controller;

import com.hwj.xm.model.ResultInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("user")
public class UserController extends BaseController{

    @RequestMapping("toLogin")
    public String toLogin( ){
        return "login";
    }

    @RequestMapping("toRegister")
    public String toRegister( ){
        return "register";
    }

    @RequestMapping("register")
    @ResponseBody
    public ResultInfo userRegister(String phone,String picVerifyCode,String phoneVerifyCode,String password){


        return new ResultInfo();
    }

    @RequestMapping("login")
    @ResponseBody
    public ResultInfo userLogin( ){
        return new ResultInfo();
    }

}
