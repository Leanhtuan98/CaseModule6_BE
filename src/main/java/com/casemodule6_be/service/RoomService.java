package com.casemodule6_be.service;

import com.casemodule6_be.dto.RoomSFGDto;
import com.casemodule6_be.model.Image;
import com.casemodule6_be.model.Room;
import com.casemodule6_be.repository.ICategoryRepo;
import com.casemodule6_be.repository.IImageRepo;
import com.casemodule6_be.repository.IRoomRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    ICategoryRepo iCategoryRepo;

    @Autowired
    ModelMapper modelMapper;

public String findCategoryName(long idCategory){
   return iRoomRepo.findCategoryName(idCategory);
}
    public List<RoomSFGDto> getRoomForGuest() {
        List<Room> rooms = listRoom();
        List<RoomSFGDto> roomSFGDtoList = rooms.stream().map(room -> modelMapper.map(room, RoomSFGDto.class))
                .collect(Collectors.toList());
        for (int i = 0; i < roomSFGDtoList.size(); i++) {
            List<Image> images = iImageRepo.findImageByRoomId(roomSFGDtoList.get(i).getId());
            roomSFGDtoList.get(i).setImg(images.get(0).getName());
            roomSFGDtoList.get(i).setCategory(findCategoryName(roomSFGDtoList.get(i).getId()));
        }

        return roomSFGDtoList;
    }


    public List<Room> listRoom() {
        return (List<Room>) iRoomRepo.findAll();
    }


    public List<RoomSFGDto> find(String categoryName, String addressName, double price1, double price2, String checkin, String checkout) {
        List<Room> rooms = iRoomRepo.findAll(categoryName, addressName, price1, price2, checkin,checkout);
        List<RoomSFGDto> roomSFGDtoList = rooms.stream().map(room -> modelMapper.map(room, RoomSFGDto.class))
                .collect(Collectors.toList());
        for (int i = 0; i < roomSFGDtoList.size(); i++) {
            List<Image> images = iImageRepo.findImageByRoomId(roomSFGDtoList.get(i).getId());
            roomSFGDtoList.get(i).setImg(images.get(0).getName());
            roomSFGDtoList.get(i).setCategory(findCategoryName(roomSFGDtoList.get(i).getId()));
        }
        return roomSFGDtoList;
    }
    public Room findRoomByid(Long id){return iRoomRepo.findById(id).get();}

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



}
