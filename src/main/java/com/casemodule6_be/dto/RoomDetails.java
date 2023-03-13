package com.casemodule6_be.dto;

import com.casemodule6_be.model.*;

import java.util.List;

public class RoomDetails {
   private Room room;
    private List<Comment> comments;
    private List<Image> img;

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public List<Comment> getComments() {
        return comments;
    }


    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Image> getImg() {
        return img;
    }

    public void setImg(List<Image> img) {
        this.img = img;
    }

    public RoomDetails() {
    }

    public RoomDetails(Room room, List<Comment> comments, List<Image> img) {
        this.room = room;
        this.comments = comments;
        this.img = img;
    }
}
