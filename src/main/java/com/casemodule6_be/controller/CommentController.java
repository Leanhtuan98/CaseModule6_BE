package com.casemodule6_be.controller;

import com.casemodule6_be.dto.CommentDTO;
import com.casemodule6_be.dto.CommentProjection;
import com.casemodule6_be.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    CommentService commentService;


    @GetMapping("/showComment/{id}")
    public List<CommentProjection> showComment(@PathVariable Long id){
        return commentService.showComment(id);
    }

    @GetMapping("/comm")
    public List<CommentDTO> showcmm(){
        return commentService.showCommentDTO();
    }
}
