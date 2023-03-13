package com.casemodule6_be.controller;


import com.casemodule6_be.dto.RoomDetails;

import com.casemodule6_be.dto.RoomSFGDto;
import com.casemodule6_be.model.Image;
import com.casemodule6_be.model.Room;
import com.casemodule6_be.service.CommentService;
import com.casemodule6_be.service.ImageService;
import com.casemodule6_be.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/rooms")


public class RoomController {
    @Autowired
    RoomService roomService;
    @Autowired
    CommentService commentService;
    @Autowired
    ImageService imageService;
    @GetMapping("/showRoomForGuest")
    public List<RoomSFGDto> showRoomForGuest() {
        return roomService.getRoomForGuest();
    }
@GetMapping("/show/{idroom}")
public List<Image> rooms(@PathVariable long idroom){
        return  imageService.findImageByRoomId(idroom);
}

    @GetMapping("/find/{categoryName}/{addressName}/{price1}/{price2}/{checkin}/{checkout}")
    public List<RoomSFGDto> find(@PathVariable String categoryName, @PathVariable String addressName,
                                 @PathVariable double price1, @PathVariable double price2,
                                 @PathVariable String checkin, @PathVariable String checkout) {
        return roomService.find(categoryName, addressName, price1, price2, checkin, checkout);
    }


    @GetMapping("/{id}")
    public ResponseEntity<RoomDetails> findById(@PathVariable Long id) {
        RoomDetails roomdetails = new RoomDetails();
        Room room = roomService.findRoomById(id);
        roomdetails.setRoom(room);
        roomdetails.setComments(commentService.findCommentByRoom(room));
        roomdetails.setImg(imageService.findImgByRoom(room));
        return new ResponseEntity<>(roomdetails, HttpStatus.OK);
    }

}

