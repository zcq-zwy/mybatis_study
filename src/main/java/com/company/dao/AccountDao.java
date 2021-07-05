package com.company.dao;

import com.company.domain.Account;
import com.company.domain.AccountUser;
import com.company.domain.AccountWithUser;
import com.company.domain.User;

import java.util.List;

/**
 * @author zcq
 */
public interface AccountDao {

    List<AccountUser> findAll();


    List<Account> findAllAccounts();

    List<User> findUsersAndAccounts();

    void updateAccounts(Account account);


    List<AccountWithUser> findUsersAndAccount();


      List<Account> findAccounts();


    /**
     * 根据用户 id 查询账户信息
     * @param uid
     * @return
     */
    List<Account> findByUid(Integer uid);

}
