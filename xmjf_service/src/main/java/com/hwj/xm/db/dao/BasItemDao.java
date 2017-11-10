package com.hwj.xm.db.dao;

import com.hwj.xm.base.BaseDao;
import com.hwj.xm.po.BasItem;
import com.hwj.xm.query.BasItemQuery;
import com.hwj.xm.util.PageList;

public interface BasItemDao extends BaseDao<BasItem> {

    PageList queryBasItemsByParams(BasItemQuery basItemQuery);
}