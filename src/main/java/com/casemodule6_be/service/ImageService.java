package com.casemodule6_be.service;

import com.casemodule6_be.model.Image;
import com.casemodule6_be.model.Room;
import com.casemodule6_be.repository.IImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {
    @Autowired
    IImageRepo iImageRepo;
    public List<Image> findImgByRoom(Room room){ return iImageRepo.findImageByRoom(room);}
}
