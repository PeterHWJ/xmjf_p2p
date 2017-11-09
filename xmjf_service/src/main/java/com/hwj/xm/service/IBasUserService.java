package com.hwj.xm.service;

import com.hwj.xm.po.BasUser;

public interface IBasUserService  {
    /**
     * 根据手机号查找用户
     * @param mobile
     * @return
     */
    BasUser queryBasUserByMobile(String mobile);

    /**
     * 注册用户
     * @param phone
     * @param password
     * @return
     */
    void saveUser(String phone,String password);

    /**
     * 用户登录验证
     * @param phone
     * @param password
     * @return
     */
    BasUser userLogincheck(String phone,String password);

}
