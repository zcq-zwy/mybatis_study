package com.company.test;

import com.company.dao.AccountDao;
import com.company.dao.RoleDao;
import com.company.dao.UserDao;
import com.company.domain.*;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author zcq
 * @date 2021/1/21
 * @since 1.0.0
 */

public class MybatisTest  {
    private static final int COUNT = 10;
    // 使用CountDownLatch 来模拟并发，并发量10个
    private static CountDownLatch cdl = new CountDownLatch(COUNT);
    private InputStream inputStream;
    private  SqlSession sqlSession;
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

@Test
    public  void test() throws Exception {



        //5.得到结果集

        List<User> userList = userDao.findAll();

        for (User user : userList) {
            System.out.println(user);
        }


    }
    @Test
 public void insertUserTest() throws Exception {

     User user=new User();
     user.setUsername("mybatis autocommit insert");
     user.setAddress("长沙雨花区");
     user.setSex("男");
     user.setBirthday(new Date());
        System.out.println( "保存操作之前"+ user);
     userDao.insertUser(user);
        System.out.println( "保存操作之后"+ user);
     sqlSession.commit();

     destroy();
 }

/*
 * @Author  zcq
 * @Description 测试更新操作
 * @Date 2021/1/24 11:06
 * @params
 * @return
 */
    @Test
    public void updateUserTest() throws Exception {

        User user=new User();


        sqlSession.commit();


    }
/**
 *@Author zcq
*@Description //TOdO 测试删除
        *@Param
        *@Return
        *@Author zcq
        *@Date
        */

    @Test
    public void deleteUserTest() throws Exception {
        userDao.deleteUser(70);
       sqlSession.commit();
    }


    /**
     *@Author zcq
     *@Description //TOdO 测试用id查询用户
     *@Param
     *@Return
     *@Author zcq
     *@Date
     */

    @Test
    public void findUserByIdTest() throws Exception {
       User user=userDao.findUserById(45);
        System.out.println(user);
        sqlSession.commit();
        UserDao mapper = sqlSession2.getMapper(UserDao.class);
        user=mapper                                                                                                                                                 .findUserById(45);
        System.out.println(user);
    }


    /**
     *@Author zcq
     *@Description //TOdO 测试用name模糊查询用户
     *@Param
     *@Return
     *@Author zcq
     *@Date
     */

    @Test
    public void findUserByNameTest() throws Exception {
        List<User> userList = userDao.findByUserName("%王%");
        for (User user : userList) {
            System.out.println(user);
        }
        List<User> userList1 = userDao.findByUserName("%王%");
        sqlSession.commit();
    }


    /**
     *@Author zcq
     *@Description //TOdO 测试查询总记录条数
     *@Param
     *@Return
     *@Author zcq
     *@Date
     */

    @Test
    public void findTotalTest() throws Exception {
        int total = userDao.findTotal();
        System.out.println(total);
        sqlSession.commit();
    }


    /**
     *@Author zcq
     *@Description //TOdO 测试用queryvo模糊查询用户
     *@Param
     *@Return
     *@Author zcq
     *@Date
     */

    @Test
    public void findUserByVoTest() throws Exception {
        QueryVo queryVo=new QueryVo();
        User user=new User();
        user.setUsername("%王%");
        queryVo.setUser(user);
        List<User> userList = userDao.findUserByVo(queryVo);
        for (User users : userList) {
            System.out.println(users);
        }
        sqlSession.commit();
    }


    @Test
    public  void createConnectionTimeTest() throws Exception
    {

        String sql = "select * from user where id < ? and id >= ?";
        PreparedStatement st = null;
        ResultSet rs = null;

        long beforeTimeOffset = -1L; //创建Connection对象前时间
        long afterTimeOffset = -1L; //创建Connection对象后时间
        long executeTimeOffset = -1L; //创建Connection对象后时间

        Connection con = null;
        Class.forName("com.mysql.jdbc.Driver");

        beforeTimeOffset = new Date().getTime();
        System.out.println("before:\t" + beforeTimeOffset);

        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatisdb", "root", "123456");

        afterTimeOffset = new Date().getTime();
        System.out.println("after:\t\t" + afterTimeOffset);
        System.out.println("Create Costs:\t\t" + (afterTimeOffset - beforeTimeOffset) + " ms");

        st = con.prepareStatement(sql);
        //设置参数
        st.setInt(1, 101);
        st.setInt(2, 0);
        //查询，得出结果集
        rs = st.executeQuery();
        executeTimeOffset = new Date().getTime();
        System.out.println("Exec Costs:\t\t" + (executeTimeOffset - afterTimeOffset) + " ms");

    }

    @Test
    public void findByUserTest(){
        User user=new User();
        user.setUsername("%王%");
        user.setAddress("北京金燕龙");
        List<User> userList = userDao.findByUser(user);
        for (User users : userList) {
            System.out.println(users);
        }
    }

    @Test
    public void testFindInIds() {
        ListInteger list = new ListInteger();
        List<Integer> ids = new ArrayList<Integer>();
        ids.add(41);
        ids.add(42);
        ids.add(43);
        ids.add(46);
        ids.add(60);
        list.setIntegerList(ids);
//6.执行操作
        List<User> users = userDao.findInIds(list);
        for(User user : users) {
            System.out.println(user);
        }
    }


    @Test
    public void findAllIds(){
        List<AccountUser> all = accountDao.findAll();
        for (AccountUser user : all) {
            System.out.println(user);
        }
    }


    @Test
    public void findAllAccountIds(){
        List<Account> all = accountDao.findAllAccounts();
        for (Account user : all) {
            System.out.println("-----每个账户");
            System.out.println(user);
            System.out.println(user.getUser());
        }
    }

    @Test
    public void findAllUserAndAccount(){
        List<User> usersAndAccounts = accountDao.findUsersAndAccounts();
        for (User user : usersAndAccounts) {
            System.out.println("----------每个用户-----------");
            System.out.println(user);
            System.out.println(user.getAccountList());
        }
    }

    @Test
    public void findAllUserAndAccounts(){
        List<AccountWithUser> usersAndAccounts = accountDao.findUsersAndAccount();
        for (AccountWithUser user : usersAndAccounts) {
            System.out.println("----------每个用户-----------");
            System.out.println(user);
            System.out.println(user.getUsername());
        }
    }

    @Test
    public void findAllRoleTest(){
        List<Role> roleList = roleDao.findAll();
        for (Role role : roleList) {
            System.out.println(role);
        }
    }


    @Test
    public void findAllUserAndRoleTest(){
        List<User> userList = roleDao.findAllUserAndRole();
        for (User user : userList) {
            System.out.println("-----每个用户----------");
            System.out.println(user);
            System.out.println(user.getList());
        }
    }

    @Test
    public void findAllRoleAndUserTest(){
        List<Role> userList = roleDao.findAllRoleAndUser();
        for (Role role : userList) {
            System.out.println("-----每个角色----------");
            System.out.println(role);
            System.out.println(role.getUserList());
        }
    }

/*
 * @Author  zcq
 * @Description 对缓存的一个测试，发现日志中打印的缓存命中率是2级缓存的命中率
 * @Date 2021/6/26 19:23
 * @params @param
 * @return * @return void
 */
    @Test
    public void CacheTest(){
        User user=new User();
        user.setUsername("%王%");
        user.setAddress("北京金燕龙");
        user.setSex("女");
        //sqlSession.clearCache();
        Date first = new Date();
        //第一次查询
        List userList = userDao.findByUser(user);
        System.out.println("first quest costs:"+ (new Date().getTime()-first.getTime()) +" ms");
        //会刷新一级缓存到2级缓存
            sqlSession.commit();
//           sqlSession.clearCache();
           Integer number_ID =45;
            userDao.deleteUser(number_ID);
//          sqlSession.commit();

        Date second = new Date();
        List userList1 = userDao.findByUser(user);
        System.out.println("second quest costs:"+ (new Date().getTime()-second.getTime()) +" ms");

//        sqlSession.commit();
//        sqlSession.clearCache();
//
//        List userList2 = userDao.findByUser(user);
//        System.out.println("three quest costs:"+ (new Date().getTime()-second.getTime()) +" ms");
//        System.out.println(userList.hashCode());
//        System.out.println(userList1.hashCode());
//        System.out.println(userList2.hashCode());
//        System.out.println(userList==userList1);
//        System.out.println(userList1==userList2);
//        System.out.println(userList==userList2);
    }
    /**
     *@Author zcq
     *@Description //TOdO 首先sqlsession和数据库的事务不相同，但是基本类似
     * 类似于开启一个mysql会话(mysql命令行界面）,
     * sqlsession.commit==>connection.commit,但这和mysql命令行界面的commit有区别吗？
     *
     * 经过验证得知 update(或者其他）操作之后让事务去提交，这就和mysql命令行界面的commit一样
     * 经过验证得知 select操作之后让事务去提交，这就只是刷新缓存到2级缓存，事务没有提交
     *
     *  事务：(1)mybatis中的事务对象（jdbctransaction）和sqlsession一样只有一个
     *       (2)怎么算开启一个数据库中的事务呢？调用对应的方法开启一个事务，
     *       这个数据库事务的id为sqlsession_id，这个数据库事务开启以后再mybatis中怎么结束你
     *       调用close方法 或者在执行更新操作(或者其他）以后commit，事务就结束了
     *       这样以后再调用一个方法，相当于开启了新的一个事务
     *
     *       总结：数据库中的事务和mybatis中的事务（org.apache.ibatis.transaction.jdbc.JdbcTransaction}
     *       不相同，数据库的中事务和mybatis中的事务是多对1的关系
     *
     *      （1） sqlsession.commit只有在事务内有更新操作，才会真的去提交（数据库提交）
     *       如果一个事务内没有更新操作的话，执行commit 并不会进行数据库提交操作，这就只是刷新缓存到2级缓存
     *
     *
     *      （2） 1.mysql中的可重复读：一个事务开启时的查询和在这个事务执行期间再去查询一模一样
     *       ，在这个期间，如果有其他数据被其他事务更新，那么这个数据的改变对这个事务不可见
     *
     *       2.一个事务执行过程中看到的数据，总是跟这个事务在启动时看到的数据是一致的。
     *       当然在可重复读隔离级别下，未提交变更对其他事务也是不可见的。
     *
     *       3.在同一个事务里， select 的结果是事务开始时时间点的状态，
     *       因此，同样的 select 操作读到的结果会是一致的，
     *
     *
     *@Param
     *@Return
     *@Author zcq
     *@Date
     */
    @Test
    public void mybatisCacheTest(){


        Account account=new Account();
        account.setId(1);
        account.setMoney(8799.00);
        Date first = new Date();
        checkCacheStatus(sqlSession);

        List<User> usersWithAccounts = userDao.findUsersWithAccounts(65);
        System.out.println("开启事务1");
         sqlSession.commit();
        checkCacheStatus(sqlSession);
        for (User user : usersWithAccounts) {
            System.out.println("事务1的查询");
            System.out.println(user);
        }

        usersWithAccounts=userDao.findUsersWithAccounts(45);
        sqlSession.commit();
        checkCacheStatus(sqlSession);

        usersWithAccounts=userDao.findUsersWithAccounts(46);
        sqlSession.commit();
        checkCacheStatus(sqlSession);
        sqlSession.clearCache();

        usersWithAccounts=userDao.findUsersWithAccounts(60);
        sqlSession.commit();
        checkCacheStatus(sqlSession);

        System.out.println("开启事务2");
        AccountDao mapper = sqlSession2.getMapper(AccountDao.class);

        System.out.println("事务1的2次查询");
        usersWithAccounts = userDao.findUsersWithAccounts(41);
        sqlSession.commit();
        checkCacheStatus(sqlSession);
        for (User user: usersWithAccounts){
            System.out.println("再次查询的结果");
            System.out.println(user);
            System.out.println(user.getAccountList());
        }

        mapper.updateAccounts(account);
        sqlSession2.commit();
        System.out.println("事务2更新以后提交");
        sqlSession.commit();
        checkCacheStatus(sqlSession);

        UserDao mapper2 = sqlSession3.getMapper(UserDao.class);
        List<User> usersWithAccounts3 = mapper2.findUsersWithAccounts(41);
        sqlSession2.commit();
        for (User user: usersWithAccounts3){
            System.out.println("是否开启了另外一个事务");
            System.out.println(user);
            System.out.println(user.getAccountList());
        }
        sqlSession.commit();

        System.out.println("事务1的3次查询");
        usersWithAccounts = userDao.findUsersWithAccounts(41);
        sqlSession.commit();
        checkCacheStatus(sqlSession);
        for (User user: usersWithAccounts){
            System.out.println("事务1的3次查询的结果");
            System.out.println(user);
            System.out.println(user.getAccountList());
        }


        //这里我们换一个数据库会话，得到一个代理类
        UserDao mapper1 = sqlSession2.getMapper(UserDao.class);
        System.out.println("事务3开启");
        List<User> usersWithAccounts1 = mapper1.findUsersWithAccounts(59);
        System.out.println("事务4开启");
        List<User> usersWithAccounts2 = mapper1.findUsersWithAccounts(41);
        for (User user : usersWithAccounts1) {
            System.out.println("事务3的一次查询");
            System.out.println(user);
        }
    sqlSession2.clearCache();
        sqlSession2.commit();

        User user1=new User();
        user1.setId(59);
        user1.setAddress("北极8");
        user1.setUsername("mybatis transcaction");
        user1.setSex("女");
        System.out.println("这里事务1更新以后提交");
        userDao.updateUser(user1);
        sqlSession.commit();
        sqlSession.clearCache();

        usersWithAccounts1 =mapper1.findUsersWithAccounts(59);
        for (User user : usersWithAccounts1) {
            System.out.println("事务3的2次查询");
            System.out.println(user);
        }
        System.out.println("这里相当于开启了另外一个查询");
        usersWithAccounts = userDao.findUsersWithAccounts(41);



        sqlSession.commit();
        sqlSession2.commit();
         //得到的结果不同是因为mysql数据库的隔离级别为可重复读
        for (User user : usersWithAccounts1) {
            System.out.println(user);
            System.out.println(user.getAccountList());
        }
     for (User user : usersWithAccounts2) {
         System.out.println(user);
         System.out.println(user.getAccountList());
     }
        for (User user : usersWithAccounts) {
            System.out.println("开启另外一个查询后的结果");
            System.out.println(user);
            System.out.println(user.getAccountList());
        }
        checkCacheStatus(sqlSession);
        System.out.println("---sqlSession2--------");
        checkCacheStatus(sqlSession2);


    }
    public void  checkCacheStatus(SqlSession sqlSession){
        System.out.println("------------Cache Status------------");
        for (String it : this.sqlSession.getConfiguration().getCacheNames()) {
            System.out.println(it + ":" + this.sqlSession.getConfiguration().getCache(it).getSize());
        }
        System.out.println("------------------------------------");
    }
@Test
  public void   testCacheWithoutCommitOrClose(){


      UserDao mapper = sqlSession.getMapper(UserDao.class);

    UserDao mapper1 = sqlSession2.getMapper(UserDao.class);

      System.out.println("userMapper读取数据: " + mapper.findUserById(45));
      sqlSession.commit();
      System.out.println("userMapper2读取数据: " +mapper1.findUserById(45));
  }
    /**
     * 获取数据库名称
     */
    @Test
    public  void getDbInformation() {

        try {

        Connection    connection = sqlSession.getConnection();
            System.out.println(connection.getClass().getName());
            String dbName = connection.getMetaData().getDatabaseProductName();
            String dbVersion = connection.getMetaData().getDatabaseProductVersion();
            System.out.println("数据库名称是：" + dbName + "；版本是：" + dbVersion);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    /*
     * @Author  zcq
     * @Description sqlsession的线程不安全性，所以不能共享
     * Cause: java.lang.ClassCastException: org.apache.ibatis.executor.ExecutionPlaceholder
     * cannot be cast to java.util.List===》类型转换异常
     *
     * BaseExecutor类中第152行,是一个三元表达式，用于判断resultHandler 是否是null，
     * 这里给出结论：此处的resultHandler =null（作者的测试用例中使用selectList方法，
     * 而selectList方法传入的resultHandler就是个null，），
     * 所以三元表单式会成为：list = (List<E>) localCache.getObject(key) ，这里出现了强制类型装换，说明问题出在localCache取得值。
     * 这里的localCache是我们常说的mybatis一级缓存
     * 查询时，由于第一次查询是不存在缓存的，此时"list = (List<E>) localCache.getObject(key) "中"list=null"，
     * 代码会进入" list = queryFromDatabase(ms, parameter, rowBounds, resultHandler, key, boundSql);"，
     * 在queryFromDatabase方法中第二句代码"localCache.putObject(key, EXECUTION_PLACEHOLDER)"，
     * 先给localCache存了一个EXECUTION_PLACEHOLDER，
     * 而EXECUTION_PLACEHOLDER是枚举类ExecutionPlaceholder的一个枚举项。
     * 由于BaseExecutor中的方法都不是同步方法，在并发的情况下，就会出现这样的场景：
   ----------------------------------------------------------------------------------------
        Thread1进入query方法，用key取缓存localCache数据不存在，则进入了queryFromDatabase，
        * 并执行了"localCache.putObject(key, EXECUTION_PLACEHOLDER)"，
        * 而此时Thread2进入了query方法，用key取缓存localCache数据，
        * 此时取出来的是Thread1刚缓存的EXECUTION_PLACEHOLDER，然后执行类型转换，
        * 由于EXECUTION_PLACEHOLDER不是list类型，
        * 所以转换抛出异常(java.lang.ClassCastException：org.apache.ibatis.executor.ExecutionPlaceholder cannot be cast to java.util.List)。
        *
        *
        *
        * 是BaseExecutor中缓存机制（mybatis的一级缓存）导致了并发问题。
        * 这种并发问题，产生原因：并发操作使用了同一个DefaultSqlSession的实例，
        * 而同一个DefaultSqlSession的实例使用的是同一个Executor对象，当缓存命中时就会出现异常或者数据不完整的情况。

     *
     * @Date 2021/7/4 12:08
     * @params @param null
     * @return * @return null
     */
    
    @Test
    public void testSqlSession() throws InterruptedException {
        for (int i = 0; i < COUNT; i++) {
            new Thread(() -> {
                try {
                    cdl.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // 调用查询方法
                getUsers();
            }).start();
            cdl.countDown();
        }

        Thread.sleep(5000);
    }

    public void getUsers(){
        String statementId="com.company.dao.UserDao.findAll";
        List<User> users = sqlSession.selectList(statementId);

    }

    /*
     * @Author  zcq
     * @Description 使用 assocation 实现延迟加载
     * 但是不能使用多表查询
     * @Date 2021/7/4 16:29
     * @params @param null
     * @return * @return null
     */
      @Test
      public void testLazy() {
          List<Account> accounts = accountDao.findAccounts();
          for (Account account : accounts) {
              //只有用到的时候，才会去数据库查询user
              // System.out.println(account.getUser());
          }
      }

     /*
      * @Author  zcq
      * @Description 使用 Collection 实现延迟加载
      * @Date 2021/7/4 17:06
      * @params @param null
      * @return * @return null
      */
    @Test
    public void testLazy2() {
        List<com.company.entity.User> users = userDao.findAll();
          for (com.company.entity.User user : users) {
              System.out.println(user.getAccounts());
          }

    }
    
    @Test
    public void testbatch() {
        UserDao mapper = batchSqlSession.getMapper(UserDao.class);
        mapper.updateUsernameById("憨包1",41);
        mapper.updateUsernameById("憨包2",43);
        mapper.updateUsernameById1("憨包3",45);
        batchSqlSession.commit();
    }
}

