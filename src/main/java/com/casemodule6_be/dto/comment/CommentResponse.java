package com.casemodule6_be.dto.comment;

import com.casemodule6_be.model.User;
import com.casemodule6_be.model.Room;
import lombok.Data;

@Data
public class CommentResponse {
    private Long id;
    private String content;
    private User user;
    private Room room;
    private Integer rating;
    private Long status;
}
