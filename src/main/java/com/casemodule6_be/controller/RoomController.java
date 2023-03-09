package com.casemodule6_be.controller;

import com.casemodule6_be.dto.RoomCount;
import com.casemodule6_be.model.Room;
import com.casemodule6_be.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/rooms")
public class RoomController {
    @Autowired
    RoomService roomService;

    @GetMapping
    public List<?> getTopListRent() {
        return roomService.findTopListRent();
    }

}
