package com.example.myimdb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.myimdb.domain.User;

public interface UserService extends IService<User> {
    Object findOne(Long currentUserId);

    User findByUsername(String username);

    boolean checkAdmin(long userId);
}
