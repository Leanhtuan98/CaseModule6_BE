package com.casemodule6_be.repository;


import com.casemodule6_be.model.Image;
import com.casemodule6_be.model.Room;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IImageRepo extends PagingAndSortingRepository<Image, Long> {


    List<Image> findImageByRoomId(Long Idroom);

    List<Image> findImageByRoom(Room room);
}
