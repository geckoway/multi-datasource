package com.way.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by wangchun on 2019/10/24.
 */
@Mapper
public interface FirstDao {
    @Select(" select user from mysql.user limit 5 ")
    List<String> getData();
}