package com.casemodule6_be.service;
import com.casemodule6_be.model.Image;
import com.casemodule6_be.model.Room;
import com.casemodule6_be.dto.RoomHostDto;
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
    private ModelMapper modelMapper;
    @Autowired
    private IRoomRepo iRoomRepo;
    @Autowired
    private ImageService imageService;

    public List<Room> list() {
        return (List<Room>) iRoomRepo.findAll();
    }

    public List<?> findTopListRent(){
        return iRoomRepo.findTopListRent();
    }
    public Room findRoomById(Long id){return iRoomRepo.findById(id).get();}
    
    public List<Room> findByAccountId(Long accountId){
        return iRoomRepo.findByAccountId(accountId);
    }
    public List<RoomHostDto> roomToRoomHostDto(Long accountId) {
         List<Room> rooms = findByAccountId(accountId);
        List<RoomHostDto> roomHostDtoList =  rooms.stream().map(room -> modelMapper.map(room, RoomHostDto.class))
                .collect(Collectors.toList());

        for (RoomHostDto roomHostDto: roomHostDtoList) {
            List<Image> images = imageService.findImageByRoomId(roomHostDto.getId());
            roomHostDto.setImg(images.get(0).getName());
        }

        return roomHostDtoList ;
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

    public Room findRoomByid(Long id) {
        return iRoomRepo.findById(id).get();
    }

}
