package com.casemodule6_be.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomDetailDTO {
    private long id;
    private String name;
    private String addressRoom;
    private double price;
    private List<String> image;
    private String description;



}
