package com.casemodule6_be.controller;

import com.casemodule6_be.dto.RoomDetails;
import com.casemodule6_be.dto.RoomProjection;

import com.casemodule6_be.dto.RoomSFGDto;
import com.casemodule6_be.model.Room;
import com.casemodule6_be.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.casemodule6_be.dto.RoomDetails;
import com.casemodule6_be.service.CommentService;
import com.casemodule6_be.service.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/rooms")

public class RoomController {
    @Autowired
    RoomService roomService;

    @GetMapping("/showRoomForGuest")
    public List<RoomSFGDto> showRoomForGuest() {
        return roomService.getRoomForGuest();
    }


    @GetMapping("/find/{categoryName}/{addressName}/{price1}/{price2}/{checkin}/{checkout}")
    public List<RoomSFGDto> find(@PathVariable String categoryName, @PathVariable String addressName,
                                 @PathVariable double price1, @PathVariable double price2,
                                 @PathVariable String checkin, @PathVariable String checkout) {
        return roomService.find(categoryName, addressName, price1, price2, checkin, checkout);
    }

    @Autowired
    CommentService commentService;
    @Autowired
    ImageService imageService;


    @GetMapping("/topRent")
    public List<RoomProjection> getTopRent(){
        return roomService.findTopRent();
    }

    @GetMapping("/roomDetail/{id}")
    public ResponseEntity<RoomDetails> findById(@PathVariable Long id) {
        RoomDetails roomdetails = new RoomDetails();
        Room room = roomService.findRoomById(id);
        roomdetails.setRoom(room);
        roomdetails.setComments(commentService.findCommentByRoom(room));
        roomdetails.setImg(imageService.findImgByRoom(room));
        return new ResponseEntity<>(roomdetails, HttpStatus.OK);
    }
}







