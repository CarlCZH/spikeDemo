package com.hui.miaosha.dao;

import com.hui.miaosha.domain.SpikeUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Author: CarlChen
 * @Despriction:
 * @Date: Create in 18:30 2019\4\13 0013
 */
@Mapper
public interface SpikeUserDao {

    @Select("select * from spike_user where id = #{id}")
    SpikeUser selectUserById(@Param("id") long id);

}
