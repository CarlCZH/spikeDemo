package com.hui.miaosha.service.impl;

import com.hui.miaosha.dao.OrderDao;
import com.hui.miaosha.domain.OrderInfo;
import com.hui.miaosha.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: CarlChen
 * @Despriction:TODO
 * @Date: Create in 22:40 2019\5\3 0003
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public OrderInfo getOrderInfo(long orderId) {
        return orderDao.getOrderInfo(orderId);
    }
}
