package com.hwj.xm.service.impl;

import com.hwj.xm.db.dao.BasItemDao;
import com.hwj.xm.query.BasItemQuery;
import com.hwj.xm.service.IBasItemService;
import com.hwj.xm.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hwjcsu999@gmail.com
 * @date 2017/11/9 20:49
 */
@Service
public class BasItemServiceImpl implements IBasItemService{

    @Autowired
    private BasItemDao basItemDao;

    @Override
    public PageList queryBasItemsByParams(BasItemQuery basItemQuery) {

        return basItemDao.queryBasItemsByParams(basItemQuery);
    }
}
