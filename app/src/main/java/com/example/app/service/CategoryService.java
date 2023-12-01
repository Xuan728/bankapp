package com.example.app.service;

import com.example.app.pojo.Category;

import java.util.List;

public interface CategoryService {
    void add(Category category);

    List<Category> list();

    Category fingById(Integer id);

    void update(Category category);
}
