package com.company.dao;

import com.company.domain.Role;
import com.company.domain.User;

import java.util.List;

/**
 * @author zcq
 */
public interface RoleDao {
   /**
    *
    * @param
    */
   List<Role> findAll();

    List<User> findAllUserAndRole();


    List<Role> findAllRoleAndUser();


}
