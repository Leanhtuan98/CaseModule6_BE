package com.casemodule6_be.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class RoomHostDto {
    private long id;
    private String name;
    private String addressRoom;
    private Boolean status;
    private String img;

    public RoomHostDto() {
    }

    public RoomHostDto(long id, String name, String addressRoom, Boolean status, String img) {
        this.id = id;
        this.name = name;
        this.addressRoom = addressRoom;
        this.status = status;
        this.img = img;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddressRoom() {
        return addressRoom;
    }

    public void setAddressRoom(String addressRoom) {
        this.addressRoom = addressRoom;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
