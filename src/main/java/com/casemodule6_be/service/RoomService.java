package com.casemodule6_be.service;

import com.casemodule6_be.dto.RoomProjection;
import com.casemodule6_be.model.Room;
import com.casemodule6_be.repository.IRoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class RoomService {
    @Autowired
    IRoomRepo iRoomRepo;

    public List<RoomProjection> findTopRent(){
        return iRoomRepo.findTopRent();
    }

    public List<Room> list(){
        return (List<Room>) iRoomRepo.findAll();
    }

    public Page<Room> pageRoom(Pageable pageable){
        return (Page<Room>) iRoomRepo.findAll(pageable);
    }
    public Room findRoomById(Long id){return iRoomRepo.findById(id).get();}




}
