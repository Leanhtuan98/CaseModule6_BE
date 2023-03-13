package com.casemodule6_be.repository;

import com.casemodule6_be.model.Image;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IImageRepo extends PagingAndSortingRepository<Image, Long> {
//    @Query(nativeQuery = true, value = "select image.name from image\n" +
//            "where image.room_id =:value1")
//    List<Image> findImageByIdRoom(@Param("value1") Long value1);

    List<Image> findImageByRoom_Id(Long Idroom);
}
