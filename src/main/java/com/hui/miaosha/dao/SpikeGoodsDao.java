package com.hui.miaosha.dao;

import com.hui.miaosha.vo.SpikeGoodsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface SpikeGoodsDao {

    @Select({"select g.*, sg.spike_price, sg.stock_count, sg.start_date, sg.end_date from spike_goods sg left join goods g on sg.goods_id=g.id"})
    List<SpikeGoodsVo> selectSpikeGoodsInfo();

    @Select("select g.*, sg.spike_price, sg.stock_count, sg.start_date, sg.end_date from spike_goods sg inner join goods g on sg.goods_id=g.id and sg.goods_id=#{goodsId}")
    SpikeGoodsVo selectInfoById(@Param("goodsId") long goodsId);

    //在对商品数量减的时候先对其数量进行判断
    @Update("update spike_goods set stock_count = stock_count - 1 where goods_id = #{goodsId} and stock_count > 0")
    Integer stockReduce(@Param("goodsId") long goodsId);
}
