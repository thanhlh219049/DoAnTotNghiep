package com.lethanh219049.application.service;

import com.lethanh219049.application.entity.Category;
import com.lethanh219049.application.model.request.CreateCategoryRequest;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    List<Category> getListCategories();

    Category getCategoryById(long id);

    Category createCategory(CreateCategoryRequest createCategoryRequest);

    void updateCategory(CreateCategoryRequest createCategoryRequest, long id);

    void deleteCategory(long id);

    Page<Category> adminGetListCategory(String id, String name, String status, int page);

    void updateOrderCategory(int[] ids);

    //Đếm số danh mục
    long getCountCategories();
}
