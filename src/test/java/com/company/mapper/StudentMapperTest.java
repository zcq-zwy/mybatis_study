/**
 * Copyright (C), 2020-2021
 * FileName: StudentMapperTest
 * Author:   zcq
 * Date:     2021/6/21 19:50
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.company.mapper;

import com.company.entity.Student;
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
 * @date 2021/6/21
 * @since 1.0.0
 */
public class StudentMapperTest {

    private static StudentMapper mapper;

    @BeforeClass
    public static void setUpMybatisDatabase() {
        SqlSessionFactory builder = new SqlSessionFactoryBuilder().build(StudentMapperTest.class.getClassLoader().getResourceAsStream("mybatisTestConfiguration/StudentMapperTestConfiguration.xml"));
        //you can use builder.openSession(false) to not commit to database
        mapper = builder.getConfiguration().getMapper(StudentMapper.class, builder.openSession(true));
    }

    @Test
    public void testSelectByAll() throws FileNotFoundException {
        List<Student> students = mapper.selectByAll(null);
        System.out.println(students);
    }
}

