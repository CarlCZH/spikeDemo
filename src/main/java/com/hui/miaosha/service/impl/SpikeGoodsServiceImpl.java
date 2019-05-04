package com.hui.miaosha.service.impl;

import com.hui.miaosha.dao.SpikeGoodsDao;
import com.hui.miaosha.service.SpikeGoodsService;
import com.hui.miaosha.vo.SpikeGoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: CarlChen
 * @Despriction:TODO
 * @Date: Create in 17:22 2019\4\15 0015
 */
@Service
public class SpikeGoodsServiceImpl implements SpikeGoodsService {

    @Autowired
    SpikeGoodsDao spikeGoodsDao;

    @Override
    public List<SpikeGoodsVo> getSpikeGoodsInfo() {
        List<SpikeGoodsVo> goodsVoList = spikeGoodsDao.selectSpikeGoodsInfo();
        return goodsVoList;
    }

    @Override
    public SpikeGoodsVo getInfoById(long goodsId) {
        SpikeGoodsVo spikeGoodsInfo = spikeGoodsDao.selectInfoById(goodsId);
        return spikeGoodsInfo;
    }

    @Override
    public Integer stockReduce(SpikeGoodsVo goodsInfo) {
        Long goodsId = goodsInfo.getId();
        return spikeGoodsDao.stockReduce(goodsId);
    }
}
