package com.casemodule6_be.controller;

import com.casemodule6_be.common.constant.Constant;
import com.casemodule6_be.dto.category.CategoryRequest;
import com.casemodule6_be.dto.category.CategoryResponse;
import com.casemodule6_be.model.Category;
import com.casemodule6_be.service.category.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(Constant.PREFIX_API_URL +"/category")
@RequiredArgsConstructor
public class CategoryController {
private final CategoryService categoryService;

private final Logger L =  LoggerFactory.getLogger(CategoryController.class);




    @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Category>> getAllCategory() {
        L.info("[GET] {}: show all category ",Constant.PREFIX_API_URL + "/category/getAll");
        return ResponseEntity.ok().body(categoryService.getAll());
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryResponse> create(@RequestBody CategoryRequest categoryRequest) {
        L.info("[POST] {}: create category ",Constant.PREFIX_API_URL + "/category/create");
        return ResponseEntity.ok().body(categoryService.save(categoryRequest));
    }


    @PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryResponse> update(@RequestBody CategoryRequest categoryRequest) {
        L.info("[POST] {}: update category ",Constant.PREFIX_API_URL + "/category/update");
        return ResponseEntity.ok().body(categoryService.update(categoryRequest));
    }


    @DeleteMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@RequestParam Long id) {
        L.info("[DELETE] {}: delete category ",Constant.PREFIX_API_URL + "/category/delete");
        categoryService.delete(id);
    }
}
