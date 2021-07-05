/*
 * Copyright (C), 2020-2021
 * FileName: UserMapperTest
 * Author:   zcq
 * Date:     2021/1/30 16:20
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.company.mapper;

import com.company.entity.User;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;



import java.util.Date;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author zcq
 * @date 2021/1/30
 * @since 1.0.0
 */
public class UserMapperTest {

    private static UserMapper mapper;

    @BeforeClass
    public static void setUpMybatisDatabase() {
        SqlSessionFactory builder = new SqlSessionFactoryBuilder().build(UserMapperTest.class.getClassLoader().getResourceAsStream("mybatisTestConfiguration/UserMapperTestConfiguration.xml"));
        //you can use builder.openSession(false) to not commit to database
        mapper = builder.getConfiguration().getMapper(UserMapper.class, builder.openSession(true));
    }

    @Test
    public void testInsert() {
        User vo1 = new User();
        vo1.setUsername("mybatis last insert authid");
        vo1.setBirthday(new Date());
        vo1.setSex("男");
        vo1.setAddress("长沙雨花区23333");

        int insert = mapper.insert(vo1);
        System.out.println(insert);
        System.out.println(vo1.getId());
    }

    @Test
    public void testUpdateBatchSelective() {

    }

    @Test
    public void testSelectByPrimaryKey() {
        User user = mapper.selectByPrimaryKey(42);
        System.out.println(user);
    }
}

