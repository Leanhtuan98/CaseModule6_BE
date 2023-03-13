package com.casemodule6_be.service;

import com.casemodule6_be.model.Category;
import com.casemodule6_be.repository.ICategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class CategoryService {
    @Autowired
    ICategoryRepo categoryRepo;
    public List<Category> getAll(){
       return (List<Category>) categoryRepo.findAll();
    }
}
