package com.hwj.xm.controller;

import com.hwj.xm.constant.P2PConstant;
import com.hwj.xm.exception.ParamsException;
import com.hwj.xm.model.ResultInfo;
import com.hwj.xm.service.ISmsService;
import com.hwj.xm.util.RandomCodesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("sms")
public class SMSController extends BaseController {
    @Autowired
    private ISmsService smsService;

    @RequestMapping("sendMobileVerifyCode")
    @ResponseBody
    public ResultInfo sendMobileVerifyCode(String mobile, String kaptchaCode, HttpSession session){
        String sessionKaptchaCode=(String) session.getAttribute(P2PConstant.PICTURE_VERIFY_KEY);

        if (null==sessionKaptchaCode){
            return failed("图片验证码已失效！");
        }

        if (!sessionKaptchaCode.equals(kaptchaCode)){
            return failed("图片验证码不匹配！");
        }
        //移除session中的图片验证码
        session.removeAttribute(P2PConstant.PICTURE_VERIFY_KEY);


        String code= RandomCodesUtil.createRandom(true,4);
        ResultInfo resultInfo = null;
        try {
            smsService.sendVerifyCodeSms(mobile,code);

            session.setAttribute(P2PConstant.SMS_VERIFY_CODE+mobile,code);
            session.setAttribute(P2PConstant.SMS_VERIFY_CODE_GEN_TIME+mobile,new Date());
            resultInfo=success("验证码发送成功！");
        }catch (ParamsException e){
            e.printStackTrace();
            resultInfo=failed(e.getErrorMsg());
        }catch (Exception e){
            e.printStackTrace();
            resultInfo=failed(P2PConstant.OP_FAILED_MSG);
        }

        return resultInfo;
    }
}
