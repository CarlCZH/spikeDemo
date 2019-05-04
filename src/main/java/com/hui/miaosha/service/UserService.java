package com.hui.miaosha.service;

import com.hui.miaosha.domain.UserInfo;

public interface UserService {

    UserInfo getUserInfo(int id);

    boolean updatePassword(int id, String fromPass);

}
