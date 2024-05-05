package com.example.myimdb.authorization.interceptor;

import com.example.myimdb.authorization.annotation.Admin;
import com.example.myimdb.authorization.annotation.CurrentUser;
import com.example.myimdb.authorization.annotation.LoginRequire;
import com.example.myimdb.authorization.manager.TokenManager;
import com.example.myimdb.authorization.model.TokenModel;
import com.example.myimdb.config.Constants;
import com.example.myimdb.domain.ResultStatus;
import com.example.myimdb.exception.AuthorizationException;
import com.example.myimdb.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.lang.reflect.Method;

/**
 * 自定义拦截器，判断此次请求是否有权限
 */
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenManager manager;
    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        if (method.isAnnotationPresent(LoginRequire.class) || method.isAnnotationPresent(Admin.class)) {
            String authorization = request.getHeader(Constants.AUTHORIZATION);
            TokenModel model = manager.getToken(authorization);

            if (manager.checkToken(model)) {
                request.setAttribute(Constants.CURRENT_USER_ID, model.getUserId());

                //检查是否有权限
                if (method.isAnnotationPresent(Admin.class)) {
                    if (userService.checkAdmin(model.getUserId())) {
                        return true;
                    } else {
                        throw new AuthorizationException(ResultStatus.PERMISSION_DENIED);
                    }
                }
            } else {
                throw new AuthorizationException(ResultStatus.USER_NOT_LOGIN);
            }
        }

        return true;
    }


}
