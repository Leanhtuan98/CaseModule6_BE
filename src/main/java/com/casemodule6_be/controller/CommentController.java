package com.casemodule6_be.controller;

import com.casemodule6_be.dto.CommentDTO;
import com.casemodule6_be.dto.CommentProjection;
import com.casemodule6_be.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    CommentService commentService;


    @GetMapping("/showComment")
    public List<CommentProjection> showComment(){
        return commentService.showComment();
    }
}
