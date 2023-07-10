package com.casemodule6_be.service.category;

import com.casemodule6_be.dto.category.CategoryRequest;
import com.casemodule6_be.dto.category.CategoryResponse;
import com.casemodule6_be.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAll();

    CategoryResponse save(CategoryRequest categoryRequest);
    CategoryResponse update(CategoryRequest categoryRequest);
    void delete(Long id);
}
