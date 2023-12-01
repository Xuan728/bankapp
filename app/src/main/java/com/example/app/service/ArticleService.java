package com.example.app.service;

import com.example.app.pojo.Article;
import com.example.app.pojo.PageBean;

public interface ArticleService {
    void add(Article article);

    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);
}
