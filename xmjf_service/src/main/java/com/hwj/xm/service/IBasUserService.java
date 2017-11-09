package com.hwj.xm.service;

import com.hwj.xm.po.BasUser;

public interface IBasUserService  {
    /**
     * 根据手机号查找用户
     * @param mobile
     * @return
     */
    BasUser queryBasUserByMobile(String mobile);

}
