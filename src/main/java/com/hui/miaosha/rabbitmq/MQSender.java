package com.hui.miaosha.rabbitmq;

import com.hui.miaosha.redis.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: CarlChen
 * @Despriction: 消息发送
 * @Date: Create in 21:31 2019\5\4 0004
 */
@Service
public class MQSender {

    private static Logger logger = LoggerFactory.getLogger(MQSender.class);

    @Autowired
    AmqpTemplate amqpTemplate;

    public void send(Object message){
        String strMsg = RedisService.beanToString(message);
        logger.info("The receive message is ---- " + strMsg);
        amqpTemplate.convertAndSend(MQConfig.QUEUE, strMsg);
    }
}
