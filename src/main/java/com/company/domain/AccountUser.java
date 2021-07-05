/**
 * Copyright (C), 2020-2021
 * FileName: AccountUser
 * Author:   zcq
 * Date:     2021/1/26 16:23
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.company.domain;

import java.io.Serializable;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author zcq
 * @date 2021/1/26
 * @since 1.0.0
 */
public class AccountUser extends Account implements Serializable {
    private String username;
    private  String address;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return super.toString()+ "AccountUser{" +
                "username='" + username + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}

