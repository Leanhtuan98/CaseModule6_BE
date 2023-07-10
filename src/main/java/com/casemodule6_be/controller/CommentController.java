package com.casemodule6_be.controller;

import com.casemodule6_be.common.constant.Constant;
import com.casemodule6_be.dto.comment.CommentRequest;
import com.casemodule6_be.dto.comment.CommentResponse;
import com.casemodule6_be.service.comment.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(Constant.PREFIX_API_URL + "/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/getAllCommentByRoomId")
    public ResponseEntity<List<CommentResponse>> getAll(@RequestParam Long id) {

        return ResponseEntity.ok().body(commentService.findCommentByRoomId(id));
    }


    @PostMapping("/save")
    public ResponseEntity<CommentResponse> save(@RequestBody CommentRequest commentRequest) {

        return ResponseEntity.ok().body(commentService.save(commentRequest));
    }

    @PostMapping("/update")
    public ResponseEntity<CommentResponse> update(@RequestBody CommentRequest commentRequest) {

        return ResponseEntity.ok().body(commentService.update(commentRequest));
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam Long id) {
        commentService.delete(id);
    }

}
