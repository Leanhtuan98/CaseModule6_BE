package com.casemodule6_be.service;

import com.casemodule6_be.model.Comment;
import com.casemodule6_be.model.Room;
import com.casemodule6_be.repository.ICommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CommentService {
    @Autowired
    ICommentRepo iCommentRepo;
    public List<Comment> findCommentByRoom(Room room){
        return iCommentRepo.findCommentsByRoom(room);
    }
}
