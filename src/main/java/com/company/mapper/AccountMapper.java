package com.company.mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.company.entity.Account;

/**
 * @author zcq
 */
public interface AccountMapper {
    /**
     * delete by primary key
     * @param id primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * insert record to table
     * @param record the record
     * @return insert count
     */
    int insert(Account record);

    /**
     * insert record to table selective
     * @param record the record
     * @return insert count
     */
    int insertSelective(Account record);

    /**
     * select by primary key
     * @param id primary key
     * @return object by primary key
     */
    Account selectByPrimaryKey(Integer id);

    /**
     * update record selective
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(Account record);

    /**
     * update record
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(Account record);



    List<Account> queryAllByMoney(@Param("money")Double money);




}