package com.casemodule6_be.service;

import com.casemodule6_be.model.Image;
import com.casemodule6_be.repository.IIMageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {
    @Autowired
    IIMageRepo iiMageRepo;
    public List<Image> saveAll (List<Image> images) {
        return (List<Image>) iiMageRepo.saveAll(images);
    }
}
