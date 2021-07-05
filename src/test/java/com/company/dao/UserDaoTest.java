/**
 * Copyright (C), 2020-2021
 * FileName: UserDaoTest
 * Author:   zcq
 * Date:     2021/1/29 18:10
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.company.dao;

import com.company.domain.User;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author zcq
 * @date 2021/1/29
 * @since 1.0.0
 */
public class UserDaoTest {

    private static UserDao mapper;

    @BeforeClass
    public static void setUpMybatisDatabase() {
        SqlSessionFactory builder = new SqlSessionFactoryBuilder().build(UserDaoTest.class.getClassLoader().getResourceAsStream("mybatisTestConfiguration/UserDaoTestConfiguration.xml"));
        //you can use builder.openSession(false) to not commit to database
        mapper = builder.getConfiguration().getMapper(UserDao.class, builder.openSession(true));
    }

    @Test
    public void testFindAllByUsernameAndId() throws FileNotFoundException {
        System.out.println(mapper.findAllByUsernameAndId("憨包", 41));
    }

    @Test
    public void testUpdateByAddressAndId() throws FileNotFoundException {
        User user = new User();
        user.setAddress("长沙");
        mapper.updateByAddressAndId(user, "hn", 60);
    }

    @Test
    public void testFindByUserName() throws FileNotFoundException {

        List<User> list = mapper.findByUserName("王");
        for (User user1 : list) {
            System.out.println(user1);
        }
    }
}

