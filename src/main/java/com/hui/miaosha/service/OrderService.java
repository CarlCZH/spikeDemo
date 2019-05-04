package com.hui.miaosha.service;

import com.hui.miaosha.domain.OrderInfo;

/**
 * @Author: CarlChen
 * @Despriction:TODO
 * @Date: Create in 22:38 2019\5\3 0003
 */
public interface OrderService {

    OrderInfo getOrderInfo(long orderId);
}
