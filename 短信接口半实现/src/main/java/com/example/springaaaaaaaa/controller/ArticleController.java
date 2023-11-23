package com.example.springaaaaaaaa.controller;

import com.example.springaaaaaaaa.pojo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: ArticleComtroller
 * Package: com.example.springaaaaaaaa.controller
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
    @GetMapping("/list")
    public Result<String> list(){
        return Result.success("所有的文章数据");
    }
}
