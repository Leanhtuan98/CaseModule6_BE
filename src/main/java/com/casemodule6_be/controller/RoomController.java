package com.casemodule6_be.controller;

import com.casemodule6_be.dto.*;

import com.casemodule6_be.model.Room;
import com.casemodule6_be.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.casemodule6_be.service.CommentService;
import com.casemodule6_be.service.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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


    @GetMapping("/find/")
    public List<RoomSFGDto> find(@RequestParam String categoryName, @RequestParam String addressName,
                                 @RequestParam double price1, @RequestParam double price2,
                                 @RequestParam String checkin, @RequestParam String checkout) {
        return roomService.find('%'+categoryName+'%', addressName,
                price1,price2,checkin,checkout);
    }

    @Autowired
    CommentService commentService;
    @Autowired
    ImageService imageService;


    @GetMapping("/topRent")
    public List<RoomDTO> getTopRent(){
        return roomService.findTopRent();
    }




//    @GetMapping("/roomDetail/{id}")
//    public ResponseEntity<RoomDetailDTO> findById(@PathVariable Long id) {
//        RoomDetailDTO roomdetails = new RoomDetailDTO();
//        Room room = roomService.findRoomById(id);
//        roomdetails.setRoom(room);
//        roomdetails.setComments(commentService.findCommentByRoom(room));
//        roomdetails.setImg(imageService.findImgByRoom(room));
//        return new ResponseEntity<>(roomdetails, HttpStatus.OK);
//    }


//    @GetMapping("/roomDetail/{id}")
//    public List<RoomDetailDTO> showDetail(@PathVariable Long id){
//        return roomService.showRoomDetail(id);
//    }

    @GetMapping("/roomDetail/{id}")
    public RoomDetailProjection showDetail(@PathVariable Long id){
        return roomService.showRoomDetail(id);
    }
}







