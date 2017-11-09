package com.hwj.xm.service.impl;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.hwj.xm.constant.P2PConstant;
import com.hwj.xm.service.IBasUserService;
import com.hwj.xm.service.ISmsService;
import com.hwj.xm.util.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmsServiceImpl implements ISmsService {
    @Autowired
    private IBasUserService basUserService;

    @Override
    public void sendVerifyCodeSms(String mobile,String code) throws Exception {
        /**
         *  1.   参数校验
         *  2.   手机号唯一性校验
         */
        AssertUtil.isTrue(StringUtils.isBlank(mobile),"手机号不能为空!");
        AssertUtil.isTrue(StringUtils.isBlank(code),"验证码不能为空!");
        AssertUtil.isTrue(null!=basUserService.queryBasUserByMobile(mobile),"该手机号已注册!");


        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", P2PConstant.SMS_ACCESS_KEY_ID, P2PConstant.SMS_ACCESS_KEY_SECRET);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", P2PConstant.SMS_PRODUCT,P2PConstant.SMS_DOMAIN);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(mobile);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName(P2PConstant.SMS_SIGN_NAME);
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(P2PConstant.SMS_TEMPLATE_CODE);
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam("{\"code\":\""+code+"\"}");

        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("");

        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);


    }
}
