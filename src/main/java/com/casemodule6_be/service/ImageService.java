package com.casemodule6_be.service;
import com.casemodule6_be.model.Image;
//import com.casemodule6_be.repository.IIMageRepo;
import com.casemodule6_be.model.Room;
//import com.casemodule6_be.repository.IImageRepo;
import com.casemodule6_be.repository.IImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {
    @Autowired
    IImageRepo iiMageRepo;
    public List<Image> saveAll (List<Image> images) {
        return (List<Image>) iiMageRepo.saveAll(images);
    }


    public List<Image> findImgbyRoom(Room room){ return iiMageRepo.findImageByRoom(room);}

    public List<Image> findImageByRoomId(Long id){
        return iiMageRepo.findImageByRoomId(id);
    }

}
