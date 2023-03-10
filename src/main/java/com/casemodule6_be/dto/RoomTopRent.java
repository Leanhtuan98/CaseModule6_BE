package com.casemodule6_be.dto;

import com.casemodule6_be.model.Image;
import com.casemodule6_be.model.Room;

public class RoomTopRent {
    private Room room;
    private Image image;

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public RoomTopRent(Room room, Image image) {
        this.room = room;
        this.image = image;
    }
}
