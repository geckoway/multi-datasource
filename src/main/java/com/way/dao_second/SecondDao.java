package com.way.dao_second;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by wangchun on 2019/10/24.
 */
@Mapper
public interface SecondDao {
    @Select(" SELECT uname FROM db_merp.ts_merp_user LIMIT 5 ")
    List<String> getData();
}
