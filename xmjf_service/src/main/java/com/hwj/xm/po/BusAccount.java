package com.hwj.xm.po;

import java.math.BigDecimal;

public class BusAccount {
    private Integer id;

    private Integer userId;

    private BigDecimal total;

    private BigDecimal usable;

    private BigDecimal cash;

    private BigDecimal frozen;

    private BigDecimal wait;

    private BigDecimal repay;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getUsable() {
        return usable;
    }

    public void setUsable(BigDecimal usable) {
        this.usable = usable;
    }

    public BigDecimal getCash() {
        return cash;
    }

    public void setCash(BigDecimal cash) {
        this.cash = cash;
    }

    public BigDecimal getFrozen() {
        return frozen;
    }

    public void setFrozen(BigDecimal frozen) {
        this.frozen = frozen;
    }

    public BigDecimal getWait() {
        return wait;
    }

    public void setWait(BigDecimal wait) {
        this.wait = wait;
    }

    public BigDecimal getRepay() {
        return repay;
    }

    public void setRepay(BigDecimal repay) {
        this.repay = repay;
    }
}