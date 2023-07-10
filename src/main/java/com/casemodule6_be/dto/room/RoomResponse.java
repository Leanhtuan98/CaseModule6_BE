package com.casemodule6_be.dto.room;
import lombok.Data;



@Data
public class RoomResponse {
    private Long id;
    private String name;
    private Double price;
    private Long addressId;
    private Long categoryId;
    private Long accountId;
    private String description;
    private Long status;
    private String image;
    
}
