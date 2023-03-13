package com.casemodule6_be.service;

import com.casemodule6_be.model.Image;
import com.casemodule6_be.repository.IImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImgService {
    @Autowired
    IImageRepo iImageRepo;
    public List<Image> findImgByIdRoom(Long idRoom){
        return iImageRepo.findImageByRoom_Id(idRoom);
    }
}
