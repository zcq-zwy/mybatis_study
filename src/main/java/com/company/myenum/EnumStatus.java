package com.company.myenum;

/**
 * Copyright (C), 2020-2021
 * FileName: EnumStatus
 *
 * @author zcq
 * Date:     2021/7/4 21:39
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public enum EnumStatus {
    NORMAL(1, "正常"),
    DELETE(0, "删除"),
    CANCEL(2, "注销");

    private EnumStatus(int code, String description) {
        this.code = new Integer(code);
        this.description = description;
    }
    private Integer code;

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String description;


    public Integer getCode() {

        return code;
    }


    public String getDescription() {

        return description;
    }
}

