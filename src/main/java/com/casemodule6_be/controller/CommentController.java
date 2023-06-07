package com.casemodule6_be.controller;

import com.casemodule6_be.projection.CommentProjection;
import com.casemodule6_be.model.Comment;
import com.casemodule6_be.service.AccountService;
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
     @Autowired
    AccountService accountService;


    @GetMapping("/showComment/{id}")
    public List<CommentProjection> showComment(@PathVariable Long id){
        return commentService.showComment(id);
    }


    @PostMapping("/postComment")
    public Comment saveComment (@RequestBody Comment comment){

        return commentService.save(comment);
    }


}
