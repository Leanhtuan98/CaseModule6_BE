package com.casemodule6_be.repository;

import com.casemodule6_be.model.Comment;
import com.casemodule6_be.model.Room;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ICommentRepo extends PagingAndSortingRepository<Comment,Long> {
  List<Comment> findCommentsByRoom(Room room);
}
