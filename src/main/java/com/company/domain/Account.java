package com.company.domain;

import java.io.Serializable;

public class Account implements Serializable {
    /**
     * 编号
     */
    private Integer id;

    /**
     * 用户编号
     */
    private Integer uid;

    /**
     * 金额
     */
    private Double money;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /**
      * 映射多对一的关系
     */
    private User user;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", uid=" + uid +
                ", money=" + money +
                '}';
    }
}

