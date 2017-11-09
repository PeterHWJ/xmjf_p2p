package com.hwj.xm.service;

public interface ISmsService {

    public void sendVerifyCodeSms(String mobile,String code) throws Exception;
}
