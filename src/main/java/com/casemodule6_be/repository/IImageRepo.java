package com.casemodule6_be.repository;

import com.casemodule6_be.model.Comment;
import com.casemodule6_be.model.Image;
import com.casemodule6_be.model.Room;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface IImageRepo extends PagingAndSortingRepository<Image,Long> {
    List<Image> findImageByRoom(Room room);
}
