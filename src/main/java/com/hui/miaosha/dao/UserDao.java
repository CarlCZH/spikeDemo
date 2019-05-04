package com.hui.miaosha.dao;

import com.hui.miaosha.domain.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserDao {

    @Select("select * from user_info where id = #{id}")
    UserInfo getUserById(@Param("id") int id);

    @Update("update user_info set password = #{password} where id = #{id} ")
    int updateUserInfo(UserInfo updateUserInfo);
}
