package com.example.myimdb.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.example.myimdb.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.example.myimdb.config.Constants.TOKEN_EXPIRES_HOUR;

@Slf4j
@Component
public class JWTUtils {

    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public JWTUtils(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    private static final String SECRET = "my_secret";
    private static final long EXPIRATION = TOKEN_EXPIRES_HOUR * 1000;

    public String createToken(User user) {
        Date expireDate = new Date(System.currentTimeMillis() + EXPIRATION);
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");

        String token = JWT.create()
                .withHeader(map)
                .withClaim("id", user.getId().toString())
                .withClaim("userName", user.getUsername())
                .withClaim("password", user.getPassword())
                .withIssuedAt(new Date())
                .sign(Algorithm.HMAC256(SECRET));

        // 存入redis
        redisTemplate.boundValueOps(String.valueOf(user.getId())).set(token, TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
        return token;
    }

    public boolean verifyToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            verifier.verify(token);

            // 验证redis中是否存在
            DecodedJWT jwt = JWT.decode(token);
//            Map<String, Claim> claims = jwt.getClaims();
            String userId = jwt.getClaim("id").asString();

            String redisToken = (String) redisTemplate.boundValueOps(userId).get();
            if (!token.equals(redisToken)) {
                log.error("Token verification failed: token not found in redis");
                return false;
            } else {
                // 更新token过期时间
                redisTemplate.expire(userId, TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
                return true;
            }
        } catch (Exception e) {
            log.error("Token verification failed: {}", e.getMessage());
            return false;
        }
    }

    public Map<String, Claim> getClaims(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaims();
        } catch (Exception e) {
            log.error("Failed to decode token: {}", e.getMessage());
            return null;
        }
    }
}
