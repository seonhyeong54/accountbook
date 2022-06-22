package com.payhere.accountbook.accountbook.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.payhere.accountbook.accountbook.entity.User;

import java.time.Instant;


public class JwtUtil {

    private static final Algorithm ALGORITHM = Algorithm.HMAC256("secret!@#&*");
    private static final long AUTH_TIME = 10 * 60;

    public static String getAuthToken(User user) {
        return JWT.create()
                .withSubject(user.getEmail())
                .withClaim("exp", Instant.now().getEpochSecond() + AUTH_TIME)
                .sign(ALGORITHM);
    }

    public static VerifyResult verify(String token) {
        try {
            DecodedJWT verify = JWT.require(ALGORITHM).build().verify(token);
            return VerifyResult.builder()
                    .success(true)
                    .email(verify.getSubject())
                    .build();
        } catch (Exception e) {
            DecodedJWT decode = JWT.decode(token);
            return VerifyResult.builder()
                    .success(false)
                    .email(decode.getSubject())
                    .build();
        }
    }

}
