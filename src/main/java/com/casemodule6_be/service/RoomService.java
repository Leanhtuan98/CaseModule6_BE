package com.casemodule6_be.service;

import com.casemodule6_be.model.Room;
import com.casemodule6_be.repository.IRoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class RoomService {
    @Autowired
    IRoomRepo iRoomRepo;

    public List<Room> list() {
        return (List<Room>) iRoomRepo.findAll();
    }

    public List<Room> findByAccountId(Long accountId) {
        return iRoomRepo.findByAccountId(accountId);
    }

    public Room save(Room room) {
       return iRoomRepo.save(room);
    }
    public void delete(Long Id){
        iRoomRepo.deleteById(Id);
    }
    public Room findByName(String name){
        return iRoomRepo.findByName(name);
    }

}
