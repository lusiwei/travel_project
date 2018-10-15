package com.lusiwei.service;

import com.lusiwei.pojo.User;

/**
 * Created  by lusiwei on 2018/10/11
 */
public interface RegisterService {
    User login(String username, String password);
}
