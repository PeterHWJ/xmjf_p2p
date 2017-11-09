package com.hwj.xm.db.dao;

import com.hwj.xm.base.BaseDao;
import com.hwj.xm.po.BasUser;
import org.apache.ibatis.annotations.Param;

public interface BasUserDao extends BaseDao<BasUser> {

    BasUser queryBasUserByMobile(@Param("mobile")String mobile);
}