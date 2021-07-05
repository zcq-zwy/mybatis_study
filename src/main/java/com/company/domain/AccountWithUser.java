/**
 * Copyright (C), 2020-2021
 * FileName: AccountWithUser
 * Author:   zcq
 * Date:     2021/2/27 9:42
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.company.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author zcq
 * @date 2021/2/27
 * @since 1.0.0
 */
public class AccountWithUser extends User implements Serializable {
    private List<Account> accountList;

    @Override
    public List<Account> getAccountList() {
        return accountList;
    }

    @Override
    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

}

