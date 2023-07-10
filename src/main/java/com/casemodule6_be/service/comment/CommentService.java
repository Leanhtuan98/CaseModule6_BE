package com.casemodule6_be.service.comment;

import com.casemodule6_be.dto.comment.CommentRequest;
import com.casemodule6_be.dto.comment.CommentResponse;


import java.util.List;

public interface CommentService {

    List<CommentResponse> findCommentByRoomId(Long id);
    CommentResponse save(CommentRequest commentRequest);
    CommentResponse update(CommentRequest commentRequest);
    void delete(Long id);
}
