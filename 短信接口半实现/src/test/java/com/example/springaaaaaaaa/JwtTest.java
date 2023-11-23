package com.example.springaaaaaaaa;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: JwtTest
 * Package: com.example.springaaaaaaaa
 */
public class JwtTest {


    @Test
    public void testGen(){
        Map<String,Object> claims=new HashMap<>();
        claims.put("id",1);
        claims.put("username","张三");

       String token= JWT.create()
                            .withClaim("user",claims)
                            .withExpiresAt(new Date(System.currentTimeMillis()+1000*60*60*12))
                            .sign(Algorithm.HMAC256("violet"));
       System.out.println(token);
    }
    @Test
    public void testParse(){
        //定义字符串
        String token="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE3MDA1MjMxMzEsInVzZXIiOnsiaWQiOjEsInVzZXJuYW1lIjoi5byg5LiJIn19.ffT5LScMl4oL3eF815Ir9VuXMFfxsZ_fNmT6rOpnrUg";
        JWTVerifier jwtVerifier= JWT.require(Algorithm.HMAC256("violet")).build();
        DecodedJWT decodedJWT= jwtVerifier.verify(token);
        Map<String, Claim> claims=decodedJWT.getClaims();
        System.out.println(claims.get("user"));


    }

}
