package com.casemodule6_be.controller;

import com.casemodule6_be.common.constant.Constant;

import com.casemodule6_be.common.utils.ObjectMapperUtils;
import com.casemodule6_be.dto.room.RoomRequest;
import com.casemodule6_be.dto.room.RoomResponse;
import com.casemodule6_be.model.Category;
import com.casemodule6_be.model.Room;
import com.casemodule6_be.repository.custom_repositories.CustomRoomRepository;
import com.casemodule6_be.service.room.RoomService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping(Constant.PREFIX_API_URL + "/rooms")

public class RoomController {
    private final RoomService roomService;

    private final Logger L = LoggerFactory.getLogger(RoomController.class);


    @GetMapping(value = "/showRoomForGuest", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RoomResponse>> showRoomForGuest() {
        L.info("[GET] {}: show rooms for guest", Constant.PREFIX_API_URL + "/rooms/showRoomForGuest");
        return ResponseEntity.ok().body(roomService.getRoomForGuest());
    }


    @GetMapping(value = "/find")
    public List<RoomResponse> find(@RequestParam String categoryName, @RequestParam String addressName,
                                   @RequestParam double price1, @RequestParam double price2,
                                   @RequestParam String checkin, @RequestParam String checkout) {
        return roomService.find('%' + categoryName + '%', addressName,
                price1, price2, checkin, checkout);
    }


    @GetMapping("/topRent")
    public List<RoomRequest> getTopRent() {
        return roomService.findTopRent();
    }

    @GetMapping("/findCategory")
    public Category findCategory(@RequestParam Long id) {
        return roomService.findCategoryById(id);
    }


    @Autowired
    private CustomRoomRepository customRoomRepository;
//    @GetMapping("/topRent5")
//    public List<Object[]> getTopRent5() {
//        return customRoomRepository.topRent();
//    }





    @GetMapping("/p/findRoomById/{id}")
    public Room findRoomById(@PathVariable Long id) {
        return roomService.findRoomById(id);
    }

    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RoomResponse> save(RoomRequest roomRequest) {
        L.info("[Post] {}: save rooms ", Constant.PREFIX_API_URL + "/rooms/save");
        return ResponseEntity.ok().body(roomService.save(roomRequest));
    }

}







