package com.hwj.xm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserController extends BaseController{

    @RequestMapping("login")
    public String userLogin( ){
        return "login";
    }

    @RequestMapping("toRegister")
    public String userRegister( ){
        return "register";
    }


}
