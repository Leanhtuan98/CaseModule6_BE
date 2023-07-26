package com.casemodule6_be.controller;

import com.casemodule6_be.common.constant.Constant;
import com.casemodule6_be.dto.address.AddressRequest;
import com.casemodule6_be.dto.address.AddressResponse;
import com.casemodule6_be.model.Address;
import com.casemodule6_be.service.address.AddressService;
import com.casemodule6_be.service.address.impl.AddressServiceImpl;
import com.casemodule6_be.service.room.impl.RoomServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin("*")
@RequestMapping(Constant.PREFIX_API_URL +"/address")
@RequiredArgsConstructor
public class AddressController {
    private final RoomServiceImpl roomServiceImpl;
    private final AddressServiceImpl addressServiceImpl;

    private final AddressService addressService;


    @DeleteMapping
    public HttpStatus deleteRoom(@RequestParam Long id) {
        roomServiceImpl.delete(id);
        return HttpStatus.OK;
    }



    @GetMapping("/getAll")
    public List<Address> getAllAddress() {
        return addressServiceImpl.getAll();
    }


    @PostMapping(value = "/create")
    public ResponseEntity<AddressResponse> create(@RequestBody AddressRequest addressRequest){
        return ResponseEntity.ok().body(addressService.create(addressRequest));
    }


    @PostMapping(value = "/update")
    public ResponseEntity<AddressResponse> update(@RequestBody AddressRequest addressRequest){
        return ResponseEntity.ok().body(addressService.update(addressRequest));
    }


    @DeleteMapping(value = "/delete")
    public void delete(@RequestParam Long id){
        addressService.delete(id);
    }




//    @PostMapping("/updateStt/{id}")
//    public ResponseEntity<Room> changeSttRoom(@PathVariable Long id, @RequestBody Long status) {
//        Room room = roomServiceImpl.findRoomById(id);
//        room.setStatus(status);
//        return ResponseEntity.ok(roomServiceImpl.save(room));
//    }
}
