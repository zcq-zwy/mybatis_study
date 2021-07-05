/*
 * Copyright (C), 2020-2021
 * FileName: EmployeeMapperTest
 * Author:   zcq
 * Date:     2021/1/31 11:00
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.company.mapper;

import com.company.entity.Employee;
import com.company.entity.StatusEnum;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;


import java.io.FileNotFoundException;
import java.util.Date;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author zcq
 * @date 2021/1/31
 * @since 1.0.0
 */
public class EmployeeMapperTest {

    private static EmployeeMapper mapper;

    @BeforeClass
    public static void setUpMybatisDatabase() {
        SqlSessionFactory builder = new SqlSessionFactoryBuilder().build(EmployeeMapperTest.class.getClassLoader().getResourceAsStream("mybatisTestConfiguration/EmployeeMapperTestConfiguration.xml"));
        //you can use builder.openSession(false) to not commit to database
        mapper = builder.getConfiguration().getMapper(EmployeeMapper.class, builder.openSession(true));
    }

    @Test
    public void testInsertSelective() {
        Employee employee = new Employee();
        //employee.setId(1);
        employee.setName("小航3");
        employee.setEmail("292@11.com");
        employee.setDate(new Date());
        employee.setPhone("135678");
        employee.setStatus(StatusEnum.LOGIN);
        mapper.insertSelective(employee);
    }

    @Test
    public void testInsert() {
        Employee employee = new Employee();
        //employee.setId(1);
        employee.setName("小航3");
        employee.setEmail("292@11.com");
        employee.setDate(new Date());
        employee.setPhone("135678");
        employee.setStatus(StatusEnum.LOGOUT);
        mapper.insert(employee);
    }

    @Test
    public void testSelectByPrimaryKey() {
        Employee employee = mapper.selectByPrimaryKey(5);
        System.out.println(employee);

    }


}

