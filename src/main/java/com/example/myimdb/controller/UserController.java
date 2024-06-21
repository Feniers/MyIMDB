package com.example.myimdb.controller;

import com.example.myimdb.authorization.annotation.CurrentUser;
import com.example.myimdb.authorization.annotation.LoginRequire;
import com.example.myimdb.domain.Result;
import com.example.myimdb.domain.ResultStatus;
import com.example.myimdb.domain.User;
import com.example.myimdb.service.IRatingsSmallService;
import com.example.myimdb.service.UserService;
import com.example.myimdb.utils.JWTUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User", description = "用户相关接口")
@RestController
@Validated
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private IRatingsSmallService ratingsSmallService;
    @Autowired
    private JWTUtils jWTUtils;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    @Operation(summary = "用户注册", description = "用户注册接口")
    @PostMapping("/user/register")
    public ResponseEntity<Result> register(@RequestBody User user) {
        Assert.hasLength(user.getUsername(), "用户名不能为空");
        Assert.hasLength(user.getPassword(), "密码不能为空");

        User u = userService.findByUsername(user.getUsername());
        if (u != null) {
            //提示用户名已存在
            return new ResponseEntity<>(Result.error(ResultStatus.USERNAME_EXIST), HttpStatus.BAD_REQUEST);
        }
        userService.getBaseMapper().insert(user);
        return new ResponseEntity<>(Result.ok(), HttpStatus.CREATED);
    }

    @Operation(summary = "用户登录", description = "用户登录接口")
    @Parameters({
            @Parameter(name = "username", description = "用户名", required = true),
            @Parameter(name = "password", description = "密码", required = true)
    })
    @PutMapping("/user/login")
    public ResponseEntity<Result> login(@RequestParam  String username,
                                        @RequestParam  String password) {

        log.info("username: {}, password: {}", username, password);

        User user = userService.findByUsername(username);
        if (user == null ||  //未注册
                !user.getPassword().equals(password)) {  //密码错误
            //提示用户名或密码错误
            return new ResponseEntity<>(Result.error(ResultStatus.USERNAME_OR_PASSWORD_ERROR), HttpStatus.BAD_REQUEST);
        }

        // 如果已经登录，返回已登录
//        if(tokenManager.checkToken(new TokenModel(user.getId(),null))){
//            return new ResponseEntity<>(Result.error(ResultStatus.USER_ALREADY_LOGIN), HttpStatus.BAD_REQUEST);
//        }

        User newUser = userService.findByUsername(username);


        //生成一个token，保存用户登录状态
//        TokenModel model = tokenManager.createToken(user.getId());
//        TokenModel model = tokenManager.createToken(user);
        String token= jWTUtils.createToken(newUser);
        return new ResponseEntity<>(Result.ok(token), HttpStatus.OK);
    }

    @Operation(summary = "用户登出", description = "用户登出接口")
    @Parameter(name = "Authorization", description = "token", required = true, in = ParameterIn.HEADER)
    @DeleteMapping("/user")
    @LoginRequire
    public ResponseEntity<Result> logout(@CurrentUser User user) {
        //删除redis中的token
        redisTemplate.delete(user.getId().toString());

        return new ResponseEntity<>(Result.ok(), HttpStatus.OK);
    }

    @Operation(summary = "获取用户信息", description = "获取用户信息接口")
    @Parameter(name = "Authorization", description = "token", required = true, in = ParameterIn.HEADER)
    @LoginRequire
    @GetMapping("/user")
    public ResponseEntity<Result> getUser(@CurrentUser User user) {
        return new ResponseEntity<>(Result.ok(user), HttpStatus.OK);
    }

    @Operation(summary = "更新用户信息", description = "更新用户信息接口")
    @Parameter(name = "Authorization", description = "token", required = true, in = ParameterIn.HEADER)
    @PutMapping("/user")
    @LoginRequire
    public ResponseEntity<Result> updateUser(@CurrentUser User user, @RequestBody User newUser) {
        if (userService.getById(user.getId()) == null) {
            return new ResponseEntity<>(Result.error(ResultStatus.RESOURCE_NOT_FOUND), HttpStatus.NOT_FOUND);
        }
        if (newUser.getUsername() != null) {
            user.setUsername(newUser.getUsername());
        }
        if (newUser.getPassword() != null) {
            user.setPassword(newUser.getPassword());
        }
        if (newUser.getNickname() != null) {
            user.setNickname(newUser.getNickname());
        }
        if (newUser.getRole() != null) {
            user.setRole(newUser.getRole());
        }
        if (userService.getBaseMapper().updateById(user) > 0) {
            return new ResponseEntity<>(Result.ok(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Result.error(501, "更新失败"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "打分", description = "打分接口")
    @Parameters({
            @Parameter(name = "movieId", description = "电影id", required = true),
            @Parameter(name = "rating", description = "评分", required = true),
            @Parameter(name = "Authorization, token", required = true, in = ParameterIn.HEADER)
    })
    @LoginRequire
    @PostMapping("/rate")
    public ResponseEntity<Result> rateMovie(@CurrentUser User user,
                                            @RequestParam @NotNull Integer movieId,
                                            @RequestParam @Range(min = 0, max = 10, message = "评分应该在0-10之间") Double rating) {
        if (ratingsSmallService.rate(user.getId(), movieId, rating)) {
            return new ResponseEntity<>(Result.ok(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Result.error(501, "评分失败"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
