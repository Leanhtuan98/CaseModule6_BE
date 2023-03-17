package com.casemodule6_be.repository;

import com.casemodule6_be.dto.CommentDTO;
import com.casemodule6_be.dto.CommentProjection;
import com.casemodule6_be.model.Comment;
import com.casemodule6_be.model.Room;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ICommentRepo extends PagingAndSortingRepository<Comment, Long> {


//  @Query(value = "select c from Comment c")

    @Query(nativeQuery = true, value = "SELECT  comment.content , comment.rating, comment.room_id as roomId,\n" +
            "           comment.account_id as accountId,\n" +
            "              account.name , account.avatar\n" +
            "            FROM comment JOIN account ON comment.account_id = account.id\n" +
            "                          JOIN room ON room.id = comment.room_id\n" +
            "            WHERE room.id =:id")
    List<CommentProjection> showComment(@Param("id") Long id );

    List<Comment> findCommentsByRoom(Room room);
}
