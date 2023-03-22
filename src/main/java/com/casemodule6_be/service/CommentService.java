package com.casemodule6_be.service;

import com.casemodule6_be.dto.CommentDTO;
import com.casemodule6_be.dto.CommentProjection;
import com.casemodule6_be.model.Comment;
import com.casemodule6_be.model.Room;
import com.casemodule6_be.repository.ICommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CommentService {
    @Autowired
    ICommentRepo iCommentRepo;

    public List<CommentDTO> showCommentDTO(){
        List<Comment> comments = (List<Comment>) iCommentRepo.findAll();
        List<CommentDTO> result =  comments.stream().map( c->{
            CommentDTO commentDTO = new CommentDTO(c.getContent(), c.getRoom().getId(), c.getAccount().getId(),
                    c.getRating(),c.getAccount().getAvatar(),c.getAccount().getName());
            return commentDTO;
        }).collect(Collectors.toList());
        return result;

    }


    public List<CommentProjection> showComment(Long id) {
        return iCommentRepo.showComment(id);
    }

    public List<Comment> findCommentByRoom(Room room) {
        return iCommentRepo.findCommentsByRoom(room);
    }

    public Comment save(Comment comment){
        return iCommentRepo.save(comment);
    }
}
