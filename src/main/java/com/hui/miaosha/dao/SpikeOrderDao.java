package com.hui.miaosha.dao;

import com.hui.miaosha.domain.OrderInfo;
import com.hui.miaosha.domain.SpikeOrder;
import org.apache.ibatis.annotations.*;

/**
 * @Author: CarlChen
 * @Despriction:TODO
 * @Date: Create in 20:49 2019\4\21 0021
 */
@Mapper
public interface SpikeOrderDao {

    @Select("select * from spike_order where user_id=#{userId} and goods_id=#{goodsId}")
    SpikeOrder selectOrderInfo(@Param("userId") Long userId, @Param("goodsId") Long goodsId);

    @Insert("insert into order_info(user_id, goods_id, goods_name, goods_count, goods_price, order_channel, status, create_date) " +
            "values(#{userId}, #{goodsId}, #{goodsName}, #{goodsCount}, #{goodsPrice}, #{orderChannel},#{status},#{createDate})")
    @SelectKey(keyColumn = "id", keyProperty = "id", resultType = Long.class, before = false, statement = "select last_insert_id()")
    Long insertOrderInfo(OrderInfo orderInfo);

    @Insert("insert into spike_order(user_id, order_id, goods_id) values(#{userId}, #{orderId}, #{goodsId})")
    Integer insertSpikerOrder(SpikeOrder spikeOrder);
}
