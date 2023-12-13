package com.example.app.controller;


import com.example.app.pojo.Result;
import com.example.app.utils.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @GetMapping("/list")
    public Result<String> list(){
//        @RequestHeader(name = "Authorization") String token, HttpServletResponse response
//        //验证token
//        try{
//            Map<String,Object> claims = JwtUtil.parseToken(token);
//            return Result.success("成功进入文章列表");
//        }catch (Exception e){
//            response.setStatus(401);
//            return Result.error("未登录");
//        }
        return Result.success("成功进入文章列表");
    }
}
