package com.casemodule6_be.repository;

import com.casemodule6_be.model.Comment;
import com.casemodule6_be.model.Image;
import com.casemodule6_be.model.Room;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IImageRepo extends PagingAndSortingRepository<Image,Long> {
    List<Image> findImageByRoom(Room room);

    @Query("SELECT i FROM Image i WHERE i.room.id = :id")
    List<Image> findImageByRoomId(@Param("id") Long id);

    List<Image> findImageByRoom_Id(Long Idroom);
}
