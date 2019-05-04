package com.hui.miaosha.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @Author: CarlChen
 * @Despriction: 消息接收
 * @Date: Create in 21:31 2019\5\4 0004
 */
@Service
public class MQReceiver {

    private static Logger logger = LoggerFactory.getLogger(MQReceiver.class);

    /**
     * Direct模式 交换机Exchange
     * @param message
     */
    @RabbitListener(queues = MQConfig.QUEUE)
    public void receiver(String message){
        logger.info("The receive message is ---- " + message);
    }
}
