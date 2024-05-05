package com.example.myimdb.controller;

import com.example.myimdb.authorization.annotation.Authorization;
import com.example.myimdb.authorization.annotation.CurrentUser;
import com.example.myimdb.authorization.manager.TokenManager;
import com.example.myimdb.authorization.model.TokenModel;
import com.example.myimdb.config.ResultStatus;
import com.example.myimdb.domain.Result;
import com.example.myimdb.domain.User;
import com.example.myimdb.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User", description = "用户相关接口")
@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private TokenManager tokenManager;

    @Operation(summary = "用户登录", description = "用户登录接口")
    @Parameters({
            @Parameter(name = "username", description = "用户名", required = true),
            @Parameter(name = "password", description = "密码", required = true)
    })
    @PostMapping("/login")
    public ResponseEntity<Result> login(@RequestParam @NotNull String username, @RequestParam @NotNull String password) {
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

    @Operation(summary = "用户登出", description = "用户登出接口")
    @Parameter(name = "Authorization", description = "token", required = true, in = ParameterIn.HEADER)
    @DeleteMapping
    @Authorization
    public ResponseEntity<Result> logout(@CurrentUser User user) {
        tokenManager.deleteToken(user.getId());
        return new ResponseEntity<>(Result.ok(), HttpStatus.OK);
    }
}
