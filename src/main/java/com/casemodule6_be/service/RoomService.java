package com.casemodule6_be.service;

import com.casemodule6_be.dto.*;
import com.casemodule6_be.model.Account;
import com.casemodule6_be.model.Comment;
import com.casemodule6_be.model.Image;
import com.casemodule6_be.model.Room;
import com.casemodule6_be.repository.IAccountRepo;
import com.casemodule6_be.repository.ICategoryRepo;
import com.casemodule6_be.repository.IImageRepo;
import com.casemodule6_be.repository.IRoomRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import java.util.stream.Collectors;

@Service
public class RoomService {
    @Autowired
    IRoomRepo iRoomRepo;
    @Autowired
    IImageRepo iImageRepo;

    @Autowired
    ICategoryRepo iCategoryRepo;
    @Autowired
    IAccountRepo iAccountRepo;

//    public List<RoomProjection> findTopRent() {
//
//        return iRoomRepo.findTopRent();
//    }


    public List<RoomDTO> findTopRent() {
        List<RoomProjection> roomDTOS = iRoomRepo.findTopRent();
        List<RoomDTO> result = roomDTOS.stream().map(r -> {
            List<String> imgs = Arrays.asList(r.getImages().split(","));
            RoomDTO roomDTO = new RoomDTO(r.getName(), imgs.get(0),
                    r.getPrice(), r.getId());
            return roomDTO;
        }).collect(Collectors.toList());
        return result;
    }


    @Autowired
    ModelMapper modelMapper;

    public String findCategoryName(long idCategory) {
        return iRoomRepo.findCategoryName(idCategory);
    }


    public List<RoomSFGDto> getRoomForGuest() {
        List<Room> rooms = listRoom();
        List<RoomSFGDto> roomSFGDtoList = rooms.stream().map(room -> modelMapper.map(room, RoomSFGDto.class))
                .collect(Collectors.toList());
        for (RoomSFGDto roomSFGDto : roomSFGDtoList) {
            List<Image> images = iImageRepo.findImageByRoomId(roomSFGDto.getId());
            if (!images.isEmpty()) {
                roomSFGDto.setImg(images.get(0).getName());
            }
            roomSFGDto.setCategory(findCategoryName(roomSFGDto.getId()));
        }

        return roomSFGDtoList;
    }


    public List<Room> listRoom() {
        return (List<Room>) iRoomRepo.findAll();
    }


    public List<RoomSFGDto> find(String categoryName, String addressName, double price1, double price2, String
            checkin, String checkout) {
        List<Room> rooms = iRoomRepo.findAll(categoryName, addressName, price1, price2, checkin, checkout);
        List<RoomSFGDto> roomSFGDtoList = rooms.stream().map(room -> modelMapper.map(room, RoomSFGDto.class))
                .collect(Collectors.toList());
        for (int i = 0; i < roomSFGDtoList.size(); i++) {
            List<Image> images = iImageRepo.findImageByRoomId(roomSFGDtoList.get(i).getId());
            roomSFGDtoList.get(i).setImg(images.get(0).getName());
            roomSFGDtoList.get(i).setCategory(findCategoryName(roomSFGDtoList.get(i).getId()));
        }
        return roomSFGDtoList;
    }


//    public List<RoomDetailDTO> showRoomDetail(Long id){
//            List<RoomDetailProjection> roomDetailDTOS = iRoomRepo.showDetail(id);
//            List<RoomDetailDTO> result = roomDetailDTOS.stream().map(r ->{
//                List<String> imgs =  Arrays.asList(r.getImage().split(","));
//                RoomDetailDTO roomDetailDTO = new RoomDetailDTO(r.getId(),r.getName(),
//                        r.getAddressRoom(),r.getPrice(),imgs, r.getDescription());
//                return roomDetailDTO;
//            }).collect(Collectors.toList());
//            return result;
//    }

    public RoomDetailProjection showRoomDetail(Long id) {
        return iRoomRepo.showDetail(id);
    }


    public Room findRoomByid(Long id) {
        return iRoomRepo.findById(id).get();
    }

    public Room save(Room room) {
        return iRoomRepo.save(room);
    }

    public void delete(Long Id) {
        iRoomRepo.deleteById(Id);
    }

    public Room findByName(String name) {
        return iRoomRepo.findByName(name);
    }

    public Page<Room> pageRoom(Pageable pageable) {
        return (Page<Room>) iRoomRepo.findAll(pageable);
    }

    public Room findRoomById(Long id) {
        return iRoomRepo.findById(id).get();
    }

    public Account findAccByRoomId(long id) {
        return iAccountRepo.findAccountByRoomId(id);
    }
}
