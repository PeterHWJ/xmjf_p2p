package com.hwj.xm.service;

import com.hwj.xm.dto.BasItemDto;
import com.hwj.xm.po.BasItem;
import com.hwj.xm.query.BasItemQuery;
import com.hwj.xm.util.PageList;

import java.util.List;

/**
 * @author hwjcsu999@gmail.com
 * @date 2017/11/9 20:42
 */
public interface IBasItemService  {

    PageList queryBasItemsByParams(BasItemQuery basItemQuery);


}
