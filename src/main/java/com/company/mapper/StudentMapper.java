package com.company.mapper;


import com.company.entity.Student;
import java.util.List;

public interface StudentMapper {
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
    int insert(Student record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(Student record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    Student selectByPrimaryKey(Integer id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(Student record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(Student record);

    int updateBatchSelective(List<Student> list);


    List<Student> selectByAll(Student student);


}