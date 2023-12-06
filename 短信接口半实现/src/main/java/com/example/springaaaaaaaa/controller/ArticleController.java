package com.example.springaaaaaaaa.controller;

import com.example.springaaaaaaaa.pojo.Result;
import com.example.springaaaaaaaa.utils.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * ClassName: ArticleComtroller
 * Package: com.example.springaaaaaaaa.controller
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
    @GetMapping("/list")
    public Result<String> list(/*@RequestHeader(name = "Authorization") String token, HttpServletResponse response */){
       /* try {
            Map<String, Object> claims = JwtUtil.parseToken(token);
            return Result.success("所有的文章数据");
        }catch (Exception e){
            response.setStatus(401);
            return Result.error("未登录");
        }
    */
        return Result.success("所有的文章数据");
    }
}
