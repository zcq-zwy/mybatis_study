package com.company.dao;
import org.apache.ibatis.annotations.Param;

import com.company.domain.ListInteger;
import com.company.domain.QueryVo;
import com.company.domain.User;

import java.util.List;

/**
 * @author zcq
 */
public interface UserDao<updateAccountList> {
    /**
     * 查询所有操作
     * @return
     */

    List<com.company.entity.User> findAll();


    /**
     * @Author  zcq
     * @Description 添加用户
     * @Date 2021/1/22 19:06
     * @params
     * @return
     */

    void insertUser(User user);



    void updateUser(User user);


    void deleteUser(Integer userId);


    User findUserById(Integer userId);


    List<User> findByUserName(String userName);

    int findTotal();

/**
 *@Author zcq
*@Description //TOdO 根据queryvo的条件查询用户
        *@Param
        *@Return
        *@Author zcq
        *@Date
        */

    List<User> findUserByVo(QueryVo vo);


    /**
     * @Author  zcq
     * @Description 根据用户信息，查询用户列表
     * @Date 2021/1/26 14:32
     * @params
     * @return
     *
     *
     */
    List<User> findByUser(User user);


    /**
     * 根据 id 集合查询用户
     * @param listInteger
     * @return
     */
    List<User>
    findInIds(ListInteger listInteger);


    List<User> findUsersWithAccounts(Integer uid);



    User findAllByUsernameAndId(@Param("username")String username,@Param("id")Integer id);


    int updateByAddressAndId(@Param("updated")User updated, @Param("address")String address, @Param("id")Integer id);


      int updateUsernameById(@Param("updatedUsername")String updatedUsername,@Param("id")Integer id);


    int updateUsernameById1(@Param("updatedUsername")String updatedUsername,@Param("id")Integer id);

    



}
