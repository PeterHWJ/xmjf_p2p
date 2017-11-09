package com.hwj.xm.query;

import com.hwj.xm.base.BaseQuery;

/**
 * @author hwjcsu999@gmail.com
 * @date 2017年11月9日, 0009 20:02
 */
public class BasItemQuery extends BaseQuery {

    private Integer itemCycle;

    private Integer itemType;

    private Integer isHistory;

    public Integer getItemCycle() {
        return itemCycle;
    }

    public void setItemCycle(Integer itemCycle) {
        this.itemCycle = itemCycle;
    }

    public Integer getItemType() {
        return itemType;
    }

    public void setItemType(Integer itemType) {
        this.itemType = itemType;
    }

    public Integer getIsHistory() {
        return isHistory;
    }

    public void setIsHistory(Integer isHistory) {
        this.isHistory = isHistory;
    }
}
