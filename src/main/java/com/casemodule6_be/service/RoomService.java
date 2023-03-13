package com.casemodule6_be.service;

import com.casemodule6_be.dto.RoomHostDto;
import com.casemodule6_be.model.Image;
import com.casemodule6_be.model.Room;
import com.casemodule6_be.repository.IImageRepo;
import com.casemodule6_be.repository.IRoomRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class RoomService {
    @Autowired
    IRoomRepo iRoomRepo;
    @Autowired
    IImageRepo iImageRepo;

    @Autowired
    ModelMapper modelMapper;


    public List<RoomHostDto> getRoomForGuest() {
        List<Room> rooms = listRoom();
        List<RoomHostDto> roomHostDtoList = rooms.stream().map(room -> modelMapper.map(room, RoomHostDto.class))
                .collect(Collectors.toList());
        for (int i = 0; i < roomHostDtoList.size(); i++) {
            List<Image> images = iImageRepo.findImageByRoom_Id(roomHostDtoList.get(i).getId());
            roomHostDtoList.get(i).setImg(images.get(0).getName());
        }

        return roomHostDtoList ;
    }


    public List<Room> listRoom() {
        return (List<Room>) iRoomRepo.findAll();
    }


    public List<RoomHostDto> find(String categoryName, String addressName, double giatri1, double giatri2) {
        List<Room> rooms = iRoomRepo.findAllByCategory_NameAndAddress_NameAndPriceBetween(categoryName, addressName, giatri1, giatri2);
        List<RoomHostDto> roomHostDtoList = rooms.stream().map(room -> modelMapper.map(room, RoomHostDto.class))
                .collect(Collectors.toList());
        for (int i = 0; i < roomHostDtoList.size(); i++) {
            List<Image> images = iImageRepo.findImageByRoom_Id(roomHostDtoList.get(i).getId());
            roomHostDtoList.get(i).setImg(images.get(0).getName());
        }
        return roomHostDtoList;
    }


}
