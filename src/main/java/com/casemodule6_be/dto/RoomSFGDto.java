package com.casemodule6_be.dto;

import lombok.Data;

import javax.persistence.Entity;


@Data
public class RoomSFGDto {
    private long id;
    private String name;
    private double price;
    private String addressRoom;
    private String category;
    private String description;
    private Boolean status;
    private String img;



    public RoomSFGDto() {

    }

    public RoomSFGDto(long id, String name, double price, String addressRoom, String category, String description, Boolean status, String img) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.addressRoom = addressRoom;
        this.category = category;
        this.description = description;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAddressRoom() {
        return addressRoom;
    }

    public void setAddressRoom(String addressRoom) {
        this.addressRoom = addressRoom;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
