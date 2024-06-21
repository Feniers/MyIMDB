package com.example.myimdb.authorization.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.myimdb.authorization.annotation.Admin;
import com.example.myimdb.authorization.annotation.LoginRequire;
import com.example.myimdb.config.Constants;
import com.example.myimdb.domain.ResultStatus;
import com.example.myimdb.exception.AuthorizationException;
import com.example.myimdb.service.UserService;
import com.example.myimdb.utils.JWTUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.lang.reflect.Method;
import java.util.Map;

@Component
@Slf4j
public class AuthorizationInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;
    @Autowired
    private JWTUtils jwtUtils;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        log.info("请求: {}  {}  {}", request.getMethod(), request.getRequestURI(), request.getRemoteAddr());

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        if (method.isAnnotationPresent(LoginRequire.class) || method.isAnnotationPresent(Admin.class)) {
            String authorization = request.getHeader(Constants.AUTHORIZATION);
            if (authorization == null || !authorization.startsWith("Bearer ")) {
                log.error("Authorization header is missing or does not start with Bearer");
                throw new AuthorizationException(ResultStatus.USER_NOT_LOGIN);
            }

            String token = authorization.substring(7);
            if (!jwtUtils.verifyToken(token)) {
                log.error("Token verification failed");
                throw new AuthorizationException(ResultStatus.USER_NOT_LOGIN);
            }

            Map<String, Claim> claims = jwtUtils.getClaims(token);
            if (claims == null) {
                log.error("Token does not contain user id");
                throw new AuthorizationException(ResultStatus.USER_NOT_LOGIN);
            }else {
                String id = claims.get("id").asString();


                if (id == null) {
                    log.error("Token does not contain user id");
                    throw new AuthorizationException(ResultStatus.USER_NOT_LOGIN);
                }

                request.setAttribute(Constants.CURRENT_USER_ID, id);

                if (method.isAnnotationPresent(Admin.class)) {
                    if (userService.checkAdmin(Long.parseLong(id))) {
                        return true;
                    } else {
                        log.error("User {} does not have admin permissions", id);
                        throw new AuthorizationException(ResultStatus.PERMISSION_DENIED);
                    }
                }
            }

            return true;
        }

        return true;
    }
}
