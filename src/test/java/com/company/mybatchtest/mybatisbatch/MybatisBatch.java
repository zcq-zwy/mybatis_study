package com.company.mybatchtest.mybatisbatch;

import com.company.dao.AccountDao;
import com.company.dao.RoleDao;
import com.company.dao.UserDao;


import com.company.entity.User;
import com.company.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Copyright (C), 2020-2021
 * FileName: MYybatisBatch
 *
 * @author zcq
 * Date:     2021/7/5 21:36
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public class MybatisBatch {
    private InputStream inputStream;
    private SqlSession sqlSession;
    private UserDao userDao;
    private AccountDao accountDao;
    private RoleDao roleDao;
    private  SqlSession sqlSession2;
    private  SqlSession sqlSession3;
    private  SqlSession batchSqlSession;
    @Before
    public void init() throws Exception{
        //1.读取配置文件---类路径
        inputStream= Resources.getResourceAsStream("SqlMapConfig.xml");

        //2.创建工厂
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder=new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory=sqlSessionFactoryBuilder.build(inputStream);

        //3.得到数据库操作的核心类
        sqlSession3=sqlSessionFactory.openSession();
        sqlSession2 = sqlSessionFactory.openSession();
        sqlSession = sqlSessionFactory.openSession();
        batchSqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
        //4.得到代理类

        userDao = this.sqlSession.getMapper(UserDao.class);

        accountDao = this.sqlSession.getMapper(AccountDao.class);

        roleDao = this.sqlSession.getMapper(RoleDao.class);
    }
    @After
    public void destroy() throws Exception{

        //sqlSession.commit();

        sqlSession.close();
        inputStream.close();
    }

    /*
     * @Author  zcq
     * @Description (1)利用batch执行器
     * @Date 2021/7/5 21:38
     * @params @param
     * @return * @return void
     */
    @Test
    public void testBatch() {
        //但是批处理的方法必须相同
        UserDao mapper = batchSqlSession.getMapper(UserDao.class);
        mapper.updateUsernameById("憨包1",41);
        mapper.updateUsernameById("憨包24",43);
        mapper.updateUsernameById("憨包34",45);
        //commit有flushStatment的效果
        batchSqlSession.commit();

    }


    /*
     * @Author  zcq
     * @Description (2)利用foreach方法
     * @Date 2021/7/5 21:38
     * @params @param
     * @return * @return void
     */
    @Test
    public void testBatch1() {
        User user=new User();
        user.setUsername("mybatis batch insert1");
        user.setBirthday(new Date());
        user.setAddress("长沙雨花区");
        user.setSex("男");

        User user1=new User();
        user1.setUsername("mybatis batch insert2");
        user1.setBirthday(new Date());
        user1.setAddress("长沙雨花区");
        user1.setSex("男");

        User user2=new User();
        user2.setUsername("mybatis batch insert3");
        user2.setBirthday(new Date());
        user2.setAddress("长沙雨花区");
        user2.setSex("男");

        List<User> userList= new ArrayList<>();
        userList.add(user);
        userList.add(user1);
        userList.add(user2);

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.batchInsert(userList);

        sqlSession.commit();

    }


    /*
     * @Author  zcq
     * @Description (3)利用case when方法
     * @Date 2021/7/5 21:38
     * @params @param
     * @return * @return void
     */
    @Test
    public void testBatch2() {
        User user=new User();
        user.setId(73);
        user.setUsername("mybatis batch insert479");
        user.setBirthday(new Date());
        user.setSex("男");
        user.setAddress("长沙雨花区89999");


        User user1=new User();
        user1.setId(74);
        user1.setUsername("mybatis batch insert498");
        user1.setBirthday(new Date());
        user1.setSex("男");
        user1.setAddress("长沙雨花区89999");


        User user2=new User();
        user2.setId(75);
        user2.setUsername("mybatis batch insert49");
        user2.setBirthday(new Date());
        user2.setSex("男");
        user2.setAddress("长沙雨花区89999");


        List<User> userList= new ArrayList<>();
        userList.add(user2);
        userList.add(user1);
        userList.add(user);

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.updateBatch(userList);

        sqlSession.commit();

    }


}

