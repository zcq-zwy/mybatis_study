package com.company.mapper;

import com.company.entity.User;
import java.util.Collection;import java.util.Date;import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @author zcq
 */
public interface UserMapper {
    /**
     * delete by primary key
     *
     * @param id primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * insert record to table
     *
     * @param record the record
     * @return insert count
     */
    int insert(User record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(User record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    User selectByPrimaryKey(Integer id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(User record);

    int updateBatch(List<User> list);

    int batchInsert(@Param("list") List<User> list);

    /**
     * bath update record
     *
     * @param list the updated record
     * @return update count
     */
    int updateBatchSelective(List<User> list);

    int updateByAddress(@Param("updated") User updated, @Param("address") String address);

    List<User> selectAllBySex(@Param("sex") String sex);

    List<User> selectByBirthdayAndUsername(@Param("birthday") Date birthday, @Param("username") String username);

    List<User> selectAllByIdIn(@Param("idCollection") Collection<Integer> idCollection);
}