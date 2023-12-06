package com.example.springaaaaaaaa.interceptors;

import com.example.springaaaaaaaa.utils.JwtUtil;
import com.example.springaaaaaaaa.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.net.AbstractEndpoint;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

/**
 * ClassName: LoginInter
 * Package: com.example.springaaaaaaaa.interceptors
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //令牌验证
        String token= request.getHeader("Authorization");
        try{
            Map<String,Object> claims= JwtUtil.parseToken(token);
            //可以把业务数据存储到Threadlocal中
            ThreadLocalUtil.set(claims);

            return  true;
        }catch (Exception e){
            response.setStatus(401);
            return false;
        }

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 清空ThreadLocal
        ThreadLocalUtil.remove();
    }
}
