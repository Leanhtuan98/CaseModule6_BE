package com.casemodule6_be.controller;

import com.casemodule6_be.common.constant.Constant;

import com.casemodule6_be.common.utils.ObjectMapperUtils;
import com.casemodule6_be.dto.bill_detail.BillDetailProjection;
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

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping(Constant.PREFIX_API_URL + "/rooms")

public class RoomController {
    private final RoomService roomService;

    private final Logger L = LoggerFactory.getLogger(RoomController.class);


    @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RoomResponse>> getAll() {
        L.info("[GET] {}: get all rooms", Constant.PREFIX_API_URL + "/rooms/getAll");
        return ResponseEntity.ok().body(roomService.getAll());
    }


    @GetMapping(value = "/findForMany")
    public ResponseEntity<List<RoomResponse>> findForMany(@RequestParam Long idAddress, @RequestParam Long idCategory,
                                                          @RequestParam Double minPrice, @RequestParam Double maxPrice,
                                                          @RequestParam String checkIn, @RequestParam String checkOut) {
        L.info("[GET] {}: find exists rooms ", Constant.PREFIX_API_URL + "/rooms/findForMany");
        return ResponseEntity.ok().body(roomService.findForMany(idAddress,idCategory,minPrice,maxPrice,checkIn,checkOut));
    }


    @GetMapping("/topRent")
    public List<RoomResponse> getTopRent() {
        L.info("[GET] {}: find top rom for rent ", Constant.PREFIX_API_URL + "/rooms/topRent");
        return roomService.findTopRent();
    }




    @GetMapping("/findRoomById")
    public ResponseEntity<RoomResponse> findRoomById(@RequestParam Long id) {
        L.info("[GET] {}: find room by id ", Constant.PREFIX_API_URL + "/rooms/findRoomById");
        return ResponseEntity.ok().body(roomService.findRoomById(id));
    }

    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RoomResponse> save(RoomRequest roomRequest) {
        L.info("[Post] {}: save rooms ", Constant.PREFIX_API_URL + "/rooms/save");
        return ResponseEntity.ok().body(roomService.save(roomRequest));
    }
    @PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RoomResponse> update(RoomRequest roomRequest) {
        L.info("[Post] {}: update rooms ", Constant.PREFIX_API_URL + "/rooms/update");
        return ResponseEntity.ok().body(roomService.update(roomRequest));
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam Long id) {
        L.info("[DELETE] {}: delete room by id ", Constant.PREFIX_API_URL + "/rooms/delete");
        roomService.delete(id);
    }
}







