package com.example.myimdb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.myimdb.dao.UserMapper;
import com.example.myimdb.domain.User;
import com.example.myimdb.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public Object findOne(Long currentUserId) {
        return this.baseMapper.selectById(currentUserId);
    }

    @Override
    public User findByUsername(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return this.baseMapper.selectOne(queryWrapper);
    }

    @Override
    public boolean checkAdmin(long userId) {
        User user = this.baseMapper.selectById(userId);
        return user.getRole() == 1;
    }

//    @Override
//    public User getUser(int id,String name, int cid) {
//        QueryWrapper<User> wrapper=new QueryWrapper<>();
//        wrapper.eq("name",name)
//                .like(());
//
//        User selectUser=this.baseMapper.selectOne(wrapper);
//        User user = this.baseMapper.selectById(id);
//        return user;
//    }



}
