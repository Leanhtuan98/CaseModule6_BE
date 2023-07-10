package com.casemodule6_be.dto.room;

import com.casemodule6_be.dto.file.FileDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomRequest extends FileDto {
    private Long id;
    private String name;
    private Double price;
    private String description;
    private Long addressId;
    private Long categoryId;
    private Long accountId;
}
