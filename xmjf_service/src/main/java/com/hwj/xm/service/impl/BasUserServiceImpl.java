package com.hwj.xm.service.impl;

import com.hwj.xm.base.BaseService;
import com.hwj.xm.db.dao.BasUserDao;
import com.hwj.xm.po.BasUser;
import com.hwj.xm.service.IBasUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasUserServiceImpl extends BaseService<BasUser> implements IBasUserService{
    @Autowired
    private BasUserDao basUserDao;

    @Override
    public BasUser queryBasUserByMobile(String mobile) {
        return basUserDao.queryBasUserByMobile(mobile);
    }
}
