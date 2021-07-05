//package com.company.mybatchtest.jdbcbatch;
//
//import com.alibaba.druid.util.JdbcUtils;
//import org.junit.Test;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//
///**
// * Copyright (C), 2020-2021
// * FileName: JdbcBatch
// *
// * @author: zcq
// * Date:     2021/7/5 22:41
// * Description:
// * History:
// * <author>          <time>          <version>          <desc>
// * 作者姓名           修改时间           版本号              描述
// */
//public class JdbcBatch {
//    @Test
//
//    public void testBash() {
//        Connection con=null;
//        PreparedStatement pst = null;
//        try {
//            long start=System.currentTimeMillis();
//            con= JdbcUtils.getConnection();
//            String sql="insert into goods(NAME)values(?)";
//            pst = con.prepareStatement(sql);
//            for(int i = 1; i <=20000;i++){
//                pst.setObject(1,"name_"+i);
//                pst.execute();
//            }
//            long end = System.currentTimeMillis();
//            System.out.println("花费的时间为："+(end - start));
//            //20000:14683
//
//        } catch (Exception exception) {
//            exception.printStackTrace();
//        }finally {
//            JdbcUtils.closeResource(con,pst);
//        }
//    }
//
//    @Test
//    public void testBash1() {
//        Connection con = null;
//        PreparedStatement pst = null;
//        try {
//            long start = System.currentTimeMillis();
//            con = JdbcUtils.getConnection();
//            String sql = "insert into goods(NAME)values(?)";
//            pst = con.prepareStatement(sql);
//            for (int i = 1; i <= 1000000; i++) {
//                pst.setObject(1, "name_" + i);
//                // 1.攒sql
//                pst.addBatch();
//                if (i % 500 == 0) {
//                    //每攒500执行sql
//                    pst.executeBatch();
//                    //清空，继续下一次
//                    pst.clearBatch();
//
//                }
//            }
//            long end = System.currentTimeMillis();
//            System.out.println("花费的时间为：" + (end - start));
//            // 20000:565
//            //1000000:5380
//
//        } catch (Exception exception) {
//            exception.printStackTrace();
//        } finally {
//            JdbcUtils.closeResource(con, pst);
//        }
//    }
//    @Test
//    public void testBash2() {
//        Connection con = null;
//        PreparedStatement pst = null;
//        try {
//            long start = System.currentTimeMillis();
//            con = JdbcUtils.getConnection();
//            //设置不允许自动提交数据
//            con.setAutoCommit(false);
//            String sql = "insert  into goods(NAME)values(?)";
//            pst = con.prepareStatement(sql);
//            for (int i = 1; i <= 20000; i++) {
//                pst.setObject(1, "name_" + i);
//                // 1.攒sql
//                pst.addBatch();
//                if (i % 500 == 0) {
//                    //每攒500执行sql
//                    pst.executeBatch();
//                    //清空，继续下一次
//                    pst.clearBatch();
//
//                }
//            }
//            con.commit();
//            long end = System.currentTimeMillis();
//            System.out.println("花费的时间为：" + (end - start));
//            // 20000:565->400
//            //1000000:5380->4165
//
//        } catch (Exception exception) {
//            exception.printStackTrace();
//        } finally {
//            JdbcUtils.closeResource(con, pst);
//        }
//    }
//}

