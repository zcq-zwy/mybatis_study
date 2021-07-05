package com.company.entity;

import com.company.myenum.EnumStatus;

/**
    * 用户表
    */
public class TUser {
    private String id;

    private String accountid;

    //枚举属性，使用mybatis默认转换类
    private EnumStatus statusDef;
    //枚举属性，使用EnumOrdinalTypeHandler转换
    private EnumStatus statusOrdinal;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountid() {
        return accountid;
    }

    public void setAccountid(String accountid) {
        this.accountid = accountid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public EnumStatus getStatusDef() {
        return statusDef;
    }

    public void setStatusDef(EnumStatus statusDef) {
        this.statusDef = statusDef;
    }

    public EnumStatus getStatusOrdinal() {
        return statusOrdinal;
    }

    public void setStatusOrdinal(EnumStatus statusOrdinal) {
        this.statusOrdinal = statusOrdinal;
    }

    private String username;

    @Override
    public String toString() {
        return "TUser{" +
                "id='" + id + '\'' +
                ", accountid='" + accountid + '\'' +
                ", username='" + username + '\'' +
                ", statusDef=" + statusDef +
                ", statusOrdinal=" + statusOrdinal +
                '}';
    }




    }
