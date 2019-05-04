package com.hui.miaosha.service.impl;

import com.hui.miaosha.dao.UserDao;
import com.hui.miaosha.domain.UserInfo;
import com.hui.miaosha.exception.GobalException;
import com.hui.miaosha.redis.KeyPrefix.SpikeUserPrefix;
import com.hui.miaosha.redis.RedisService;
import com.hui.miaosha.result.CodeMsg;
import com.hui.miaosha.service.UserService;
import com.hui.miaosha.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: CarlChen
 * @Despriction: 进行对象级的缓存练习
 * @Date: Create in 22:40 2019\4\11 0011
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    RedisService redisService;

    @Override
    public UserInfo getUserInfo(int id) {
        //取缓存
        UserInfo userInfo = redisService.get(SpikeUserPrefix.commonUserPrefix, "" + id, UserInfo.class);
        if (userInfo != null){
            return userInfo;
        }
        userInfo = userDao.getUserById(id);
        if (userInfo != null){
            redisService.set(SpikeUserPrefix.commonUserPrefix, "" + id, userInfo);
        }
        return userInfo;
    }

    @Override
    public boolean updatePassword(int id, String fromPass) {
        UserInfo userInfo = getUserInfo(id);
        if (userInfo == null){
            throw new GobalException(CodeMsg.MOBILE_NOT_EXIST);
        }
        //对数据库中数据进行更新
        UserInfo updateUserInfo = new UserInfo();
        updateUserInfo.setId(id);
        updateUserInfo.setPassword(MD5Util.formPassToDBPass(fromPass, userInfo.getSalt()));
        userDao.updateUserInfo(updateUserInfo);

        //删除redis缓存的数据
        redisService.del(SpikeUserPrefix.commonUserPrefix, "" + id);
        userInfo.setPassword(updateUserInfo.getPassword());
        redisService.set(SpikeUserPrefix.commonUserPrefix, "" + id, userInfo);
        return true;
    }
}
