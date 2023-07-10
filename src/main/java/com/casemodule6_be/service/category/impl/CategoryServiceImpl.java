package com.casemodule6_be.service.category.impl;

import com.casemodule6_be.common.constant.Constant;
import com.casemodule6_be.common.enums.EnumSSWException;
import com.casemodule6_be.common.exception.SSWException;
import com.casemodule6_be.common.utils.ObjectMapperUtils;
import com.casemodule6_be.dto.category.CategoryRequest;
import com.casemodule6_be.dto.category.CategoryResponse;
import com.casemodule6_be.model.Category;
import com.casemodule6_be.repository.CategoryRepository;
import com.casemodule6_be.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    public List<Category> getAll(){
       return  categoryRepository.findAll();
    }

    @Override
    public CategoryResponse save(CategoryRequest categoryRequest) {
        Category category = Category.builder()
                .name(categoryRequest.getName())
                .status(Constant.DEFAULT_STATUS)
                .build();

        category = categoryRepository.save(category);
        return ObjectMapperUtils.map(category,CategoryResponse.class);
    }

    @Override
    public CategoryResponse update(CategoryRequest categoryRequest) {
        Optional<Category> category = categoryRepository.findById(categoryRequest.getId());
        if(!category.isPresent()){
            throw new SSWException(EnumSSWException.CATEGORY_NOT_EXISTED);
        }
        Category updateCategory = category.get();
        updateCategory.setName(categoryRequest.getName());
        updateCategory = categoryRepository.save(updateCategory);

        return ObjectMapperUtils.map(updateCategory,CategoryResponse.class);
    }

    @Override
    public void delete(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if(!category.isPresent()){
            throw new SSWException(EnumSSWException.CATEGORY_NOT_EXISTED);
        }
        Category deleteCategory = category.get();
        deleteCategory.setStatus(Constant.DELETE_STATUS);
        categoryRepository.save(deleteCategory);
    }
}
