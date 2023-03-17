package com.casemodule6_be.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private String content;
    private Long roomId;
    private Long accountId;
    private Integer rating;
    private String avatar;
    private String name;


}
