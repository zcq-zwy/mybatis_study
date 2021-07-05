/*
 * Copyright (C), 2020-2021
 * FileName: MyDruidDataSourceFactory
 * Author:   zcq
 * Date:     2021/1/31 18:41
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.company.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.ibatis.datasource.DataSourceFactory;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author zcq
 * @date 2021/1/31
 * @since 1.0.0
 */
public class MyDruidDataSourceFactory extends DruidDataSourceFactory implements DataSourceFactory {
   private Properties properties;
    @Override
    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    @Override
    public DataSource getDataSource() {
        try {
            DataSource dataSource = createDataSource(properties);
            return dataSource;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return  null;
    }
}

