package com.casemodule6_be.dto.comment;

import com.casemodule6_be.model.Account;
import com.casemodule6_be.model.Room;
import lombok.Data;

@Data
public class CommentResponse {
    private Long id;
    private String content;
    private Account account;
    private Room room;
    private Integer rating;
    private Long status;
}
