package com.example.myimdb.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.myimdb.authorization.annotation.Authorization;
import com.example.myimdb.authorization.annotation.CurrentUser;
import com.example.myimdb.authorization.manager.TokenManager;
import com.example.myimdb.authorization.model.TokenModel;
import com.example.myimdb.config.ResultStatus;
import com.example.myimdb.domain.Result;
import com.example.myimdb.domain.User;
import com.example.myimdb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private TokenManager tokenManager;

    @PostMapping("/login")
    public ResponseEntity<Result> login(@RequestParam String username, @RequestParam String password) {
        Assert.notNull(username, "username can not be empty");
        Assert.notNull(password, "password can not be empty");

        User user = userService.findByUsername(username);
        if (user == null ||  //未注册
                !user.getPassword().equals(password)) {  //密码错误
            //提示用户名或密码错误
            return new ResponseEntity<>(Result.error(ResultStatus.USERNAME_OR_PASSWORD_ERROR), HttpStatus.NOT_FOUND);
        }
        //生成一个token，保存用户登录状态
        TokenModel model = tokenManager.createToken(user.getId());
        return new ResponseEntity<>(Result.ok(model), HttpStatus.OK);
    }

    @DeleteMapping
    @Authorization
    public ResponseEntity<Result> logout(@CurrentUser User user) {
        tokenManager.deleteToken(user.getId());
        return new ResponseEntity<>(Result.ok(), HttpStatus.OK);
    }
}
