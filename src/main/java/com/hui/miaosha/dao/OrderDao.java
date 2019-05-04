package com.hui.miaosha.dao;

import com.hui.miaosha.domain.OrderInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Author: CarlChen
 * @Despriction:TODO
 * @Date: Create in 22:41 2019\5\3 0003
 */
@Mapper
public interface OrderDao {

    @Select("select * from order_info where id=#{orderId}")
    OrderInfo getOrderInfo(@Param("orderId") long orderId);
}
