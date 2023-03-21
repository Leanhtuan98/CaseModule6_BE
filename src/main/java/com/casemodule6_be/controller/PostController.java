package com.casemodule6_be.controller;

import com.casemodule6_be.model.Address;
import com.casemodule6_be.model.Category;
import com.casemodule6_be.model.Image;
import com.casemodule6_be.model.Room;
import com.casemodule6_be.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin("*")
@RequestMapping("/post")
public class PostController {
    @Autowired
    private RoomService roomService;
    @Autowired
    private ImageService imageService;

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private AddressService addressService;

    @Autowired
    private AccountService accountService;

//    @GetMapping()
//    public List<RoomHostDto> getRooms(@RequestParam Long accountId) {
//
//        return roomService.roomToRoomHostDto(accountId);
//    }

//    @GetMapping
//public ResponseEntity<String>sayHello(){
//        return ResponseEntity.ok("huy đẹp trai vl");
//    }
//    @PostMapping
//    public HttpStatus saveRoom(Room room) {
//roomService.save(room);
//        return HttpStatus.OK;
//    }

//    @PostMapping
//    public HttpStatus saveRoomWithImages(@RequestBody RoomDTO roomDto) {
//        Room room = roomDto.getRoom();
//        List<Image> images = roomDto.getImages();
//
//         roomService.save(room);
//
//        for (Image image : images) {
//            image.setRoom(room);
//        }
//        imageService.saveAll(images);
//
//        return HttpStatus.OK;
//    }

    @PostMapping(value = "/upload/{id}", consumes = {"multipart/form-data"})
    public HttpStatus saveImg(@RequestPart("files") MultipartFile[] files, @PathVariable long id) {

        Room room = roomService.findRoomByid(id);
        List<Image> imageList = new ArrayList<>();
        for (MultipartFile fileImg : files) {
            String nameImg = fileImg.getOriginalFilename();
            try {
                FileCopyUtils.copy(fileImg.getBytes(), new File("C:\\Users\\dell\\Desktop\\MD6\\CaseModule6_FE\\src\\assets\\img\\bach\\" + nameImg));
                Image image = new Image();
                image.setName("assets/img/bach/" + nameImg);
                image.setRoom(room);
                imageList.add(image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        imageService.saveAll(imageList);
        return HttpStatus.OK;
    }

    @PostMapping("/room/{accountId}")
    public Long saveRoom(@RequestBody Room room, @PathVariable long accountId ) {
        room.setAccount(accountService.findById(accountId));
        roomService.save(room);
        return room.getId();
    }


    @DeleteMapping
    public HttpStatus deleteRoom(@RequestParam Long id) {
        roomService.delete(id);
        return HttpStatus.OK;
    }

    @GetMapping("/categories")
    public List<Category> getAllCategory() {
        return categoryService.getAll();
    }

    @GetMapping("/address")
    public List<Address> getAllAddress() {
        return addressService.getAll();
    }

    @PostMapping("/updateStt/{id}")
    public ResponseEntity<Room> changeSttRoom(@PathVariable long id, @RequestBody Boolean status){
Room room =  roomService.findRoomByid(id);
room.setStatus(status);
return ResponseEntity.ok(roomService.save(room));
    }
}
