package com.company.mapper;

import com.company.entity.TUser;
import com.company.myenum.EnumStatus;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileNotFoundException;

/**
 * Copyright (C), 2020-2021
 * FileName: TUserMapperTest
 *
 * @author: zcq
 * Date:     2021/7/4 21:58
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public class TUserMapperTest {

    private static TUserMapper mapper;

    @BeforeClass
    public static void setUpMybatisDatabase() {
        SqlSessionFactory builder = new SqlSessionFactoryBuilder().build(TUserMapperTest.class.getClassLoader().getResourceAsStream("mybatisTestConfiguration/TUserMapperTestConfiguration.xml"));
        //you can use builder.openSession(false) to not commit to database
        mapper = builder.getConfiguration().getMapper(TUserMapper.class, builder.openSession(true));
    }


    /*
     * @Author  zcq
     * @Description EnumTypeHandler和EnumOrdinalTypeHandler的区别
     * @Date 2021/7/4 22:08
     * @params @param
     * @return * @return void
     */
    @Test
    public void testInsertSelective() throws FileNotFoundException {
        TUser tUser = new TUser();
        tUser.setId("8");
        tUser.setUsername("jack");
        tUser.setAccountid("2");
        tUser.setStatusDef(EnumStatus.CANCEL);
        tUser.setStatusOrdinal(EnumStatus.NORMAL);
        mapper.insertSelective(tUser);
    }


    /*
     * @Author  zcq
     * @Description EnumTypeHandler 用getString来获取数据库中的值
     *  EnumOrdinalTypeHandler用getInt来获取值，得到
     * @Date 2021/7/4 22:25
     * @params @param
     * @return * @return void
     */
    @Test
    public void testSelectByPrimaryKey() throws FileNotFoundException {
        TUser tUser = mapper.selectByPrimaryKey("3");
        System.out.println(tUser);
    }
}

