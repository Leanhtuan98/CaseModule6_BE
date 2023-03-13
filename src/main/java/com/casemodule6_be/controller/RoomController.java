package com.casemodule6_be.controller;

import com.casemodule6_be.dto.RoomHostDto;
import com.casemodule6_be.dto.RoomShowDTO;
import com.casemodule6_be.model.Image;
import com.casemodule6_be.model.Room;
import com.casemodule6_be.service.ImgService;
import com.casemodule6_be.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/rooms")
public class RoomController {
    @Autowired
    RoomService roomService;

    @GetMapping("/showRoomForGuest")

    public List<RoomHostDto> showRoomForGuest() {
        return roomService.getRoomForGuest();
    }


    @GetMapping("/find/{categoryName}/{addressName}/{price1}/{price2}")
    public List<RoomHostDto> find(@PathVariable String categoryName, @PathVariable String addressName,
                                  @PathVariable double price1, @PathVariable double price2) {
        return roomService.find(categoryName, addressName, price1, price2);
    }
}
