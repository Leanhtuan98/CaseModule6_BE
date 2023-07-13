package com.casemodule6_be.service.room.impl;

import com.casemodule6_be.common.constant.Constant;
import com.casemodule6_be.common.enums.EnumSSWException;
import com.casemodule6_be.common.exception.SSWException;
import com.casemodule6_be.common.utils.ObjectMapperUtils;
import com.casemodule6_be.dto.bill_detail.BillDetailProjection;
import com.casemodule6_be.dto.file.FileDto;
import com.casemodule6_be.dto.room.RoomRequest;
import com.casemodule6_be.dto.room.RoomResponse;
import com.casemodule6_be.model.Account;
import com.casemodule6_be.model.Category;
import com.casemodule6_be.model.Room;
import com.casemodule6_be.repository.*;
import com.casemodule6_be.service.address.AddressService;
import com.casemodule6_be.service.minio.MinioService;
import com.casemodule6_be.service.room.RoomService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final CategoryRepository categoryRepository;
    private final AccountRepository accountRepository;
    private final MinioService minioService;
    private final AddressRepository addressRepository;
    private final BillDetailRepository billDetailRepository;


    @Value("${minio.default.image}")
    private String defaultImage;



    @Override
    public List<RoomResponse> findTopRent() {
       List<Long> list = new ArrayList<>();
       List<BillDetailProjection> billDetailProjectionList = billDetailRepository.findTopRent();
        for (int i = 0; i < billDetailProjectionList.size(); i++) {
            list.add( billDetailProjectionList.get(i).getRoomId());
        }
        List<Room> rooms = roomRepository.findAllById(list);
        return ObjectMapperUtils.mapAll(rooms, RoomResponse.class) ;
    }



    @Override
    public List<RoomResponse> getAll() {
        return ObjectMapperUtils.mapAll(roomRepository.findAll(),RoomResponse.class);
    }

    @Override
    public List<RoomResponse> findForMany(Long idAddress, Long idCategory, Double minPrice, Double maxPrice, String checkIn, String checkOut) {
        return ObjectMapperUtils.mapAll(roomRepository.findForMany(idAddress,idCategory,minPrice,maxPrice,checkIn,checkOut),RoomResponse.class);
    }


//    public List<RoomResponse> getRoomForGuest() {
//        List<Room> rooms = listRoom();
//        List<RoomResponse> roomResponseList = rooms.stream().map(room -> modelMapper.map(room, RoomResponse.class))
//                .collect(Collectors.toList());
//        for (RoomResponse roomResponse : roomResponseList) {
//            List<Image> images = imageRepository.findImageByRoomId(roomResponse.getId());
//            if (!images.isEmpty()) {
//                roomResponse.setImg(images.get(0).getImage());
//            }
//            roomResponse.setCategory(findCategoryName(roomResponse.getId()));
//        }
//
//        return roomResponseList;
//    }









//    public ResponseEntity<?> showRoomDetail(Long id) {
////       Room room = roomRepository.showDetail(id);
//       RoomResponse roomResponse = ObjectMapperUtils.map(roomRepository.showDetail(id),RoomResponse.class);
//
//        return ResponseEntity.ok().body(roomResponse);
//    }


    public RoomResponse save(RoomRequest roomRequest) {
        Room room = new Room();
        List<String> list = minioService.uploadFile(new FileDto(roomRequest.getTitle(), roomRequest.getFileList()));

        String result = list.stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(","));

        room.setImage(result);
        room.setAddress(addressRepository.findById(roomRequest.getAddressId()).get());
        room.setAccount(accountRepository.findById(roomRequest.getAccountId()).get());
        room.setCategory(categoryRepository.findById(roomRequest.getCategoryId()).get());
        room.setStatus(Constant.DEFAULT_STATUS);
        room.setName(roomRequest.getName());
        room.setPrice(roomRequest.getPrice());
        room.setDescription(roomRequest.getDescription());
        roomRepository.save(room);
        return ObjectMapperUtils.map(room,RoomResponse.class);

    }

    @Override
    public RoomResponse update(RoomRequest roomRequest) {
        Optional<Room> room = roomRepository.findById(roomRequest.getId());
        if(!room.isPresent()){
            throw  new SSWException(EnumSSWException.ROOM_NOT_EXISTED);
        }
        Room updateRoom = room.get();
        List<String> list = minioService.uploadFile(new FileDto(roomRequest.getTitle(), roomRequest.getFileList()));

        String result = list.stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(","));
        updateRoom.setImage(result);
        updateRoom.setAddress(addressRepository.findById(roomRequest.getAddressId()).get());
        updateRoom.setAccount(accountRepository.findById(roomRequest.getAccountId()).get());
        updateRoom.setCategory(categoryRepository.findById(roomRequest.getCategoryId()).get());
        updateRoom.setStatus(Constant.DEFAULT_STATUS);
        updateRoom.setName(roomRequest.getName());
        updateRoom.setPrice(roomRequest.getPrice());
        updateRoom.setDescription(roomRequest.getDescription());
        roomRepository.save(updateRoom);
        return ObjectMapperUtils.map(updateRoom,RoomResponse.class);

    }

    public void delete(Long id) {
        Optional<Room> room = roomRepository.findById(id);
        if(!room.isPresent()){
            throw  new SSWException(EnumSSWException.ROOM_NOT_EXISTED);
        }
        Room deleteRoom = room.get();
        deleteRoom.setStatus(Constant.DELETE_STATUS);
        roomRepository.save(deleteRoom);
    }


    public RoomResponse findRoomById(Long id) {
        Optional<Room> room = roomRepository.findById(id);
        if(!room.isPresent()){
            throw  new SSWException(EnumSSWException.ROOM_NOT_EXISTED);
        }
        Room findRoom = room.get();
        return ObjectMapperUtils.map(findRoom,RoomResponse.class);
    }

}
