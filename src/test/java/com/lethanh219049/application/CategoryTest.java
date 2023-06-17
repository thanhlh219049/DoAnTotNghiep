package com.lethanh219049.application;


import com.lethanh219049.application.entity.Category;
import com.lethanh219049.application.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CategoryTest {

    @Autowired
    private CategoryService categoryService;


    @Test
    void getListCategoriesTest(){
        List<Category> categories = categoryService.getListCategories();
        categories.forEach(System.out::println);
    }
}
