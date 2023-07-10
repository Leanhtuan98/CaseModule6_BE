package com.casemodule6_be.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequest {
    private Long id;
    private String content;
    private Long roomId;
    private Long accountId;
    private Integer rating;
    private Long status;


}
