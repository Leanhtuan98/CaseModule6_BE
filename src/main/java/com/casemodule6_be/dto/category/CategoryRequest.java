package com.casemodule6_be.dto.category;

import lombok.Data;

@Data
public class CategoryRequest {

    private Long id;
    private String name;
    private Long status;
}
