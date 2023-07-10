package com.casemodule6_be.service.room.impl;

import com.casemodule6_be.common.constant.Constant;
import com.casemodule6_be.common.utils.ObjectMapperUtils;
import com.casemodule6_be.dto.file.FileDto;
import com.casemodule6_be.dto.room.RoomRequest;
import com.casemodule6_be.dto.room.RoomResponse;
import com.casemodule6_be.model.Account;
import com.casemodule6_be.model.Category;
import com.casemodule6_be.model.Room;
import com.casemodule6_be.repository.AccountRepository;
import com.casemodule6_be.repository.AddressRepository;
import com.casemodule6_be.repository.CategoryRepository;
import com.casemodule6_be.repository.RoomRepository;
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

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final CategoryRepository categoryRepository;
    private final AccountRepository accountRepository;
    private final MinioService minioService;
    private final AddressRepository addressRepository;

    @Value("${minio.default.image}")
    private String defaultImage;


//    public List<RoomRequest> findTop5(){
//        List<Object[]>  roomDTOS = customRoomRepository.topRent();
//        List<RoomRequest> result = roomDTOS.stream().map(r -> {
//            List<String> imgs = Arrays.asList(r.getImages().split(","));
//            RoomRequest roomDTO = new RoomRequest(r.getName(), imgs.get(0),
//                    r.getPrice(), r.getId());
//            return roomDTO;
//        }).collect(Collectors.toList());
//        return result;
//    }


//    public List<RoomRequest> findTopRent() {
//        List<RoomProjection> roomDTOS = roomRepository.findTopRent();
//        List<RoomRequest> result = roomDTOS.stream().map(r -> {
//            List<String> imgs = Arrays.asList(r.getImg().split(","));
//            RoomRequest roomRequest = new RoomRequest(r.getName(), imgs.get(0),
//                    r.getPrice(), r.getId());
//            return roomRequest;
//        }).collect(Collectors.toList());
//        return result;
//    }


    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<RoomRequest> findTopRent() {
        return null;
    }

    public String findCategoryName(Long idCategory) {
        return roomRepository.findCategoryName(idCategory);
    }

    public Category findCategoryById(Long idCategory) {
        return categoryRepository.findCategoryById(idCategory);
    }

    @Override
    public List<RoomResponse> getRoomForGuest() {
        return null;
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


    public List<Room> listRoom() {
        return (List<Room>) roomRepository.findAll();
    }

    @Override
    public List<RoomResponse> find(String categoryName, String addressName, double price1, double price2, String checkin, String checkout) {
        return null;
    }


//    public List<RoomResponse> find(String categoryName, String addressName, double price1, double price2, String
//            checkin, String checkout) {
//        List<Room> rooms = roomRepository.findAll(categoryName, addressName, price1, price2, checkin, checkout);
//        List<RoomResponse> roomResponseList = rooms.stream().map(room -> modelMapper.map(room, RoomResponse.class))
//                .collect(Collectors.toList());
//        for (int i = 0; i < roomResponseList.size(); i++) {
//            List<Image> images = imageRepository.findImageByRoomId(roomResponseList.get(i).getId());
//            roomResponseList.get(i).setImg(images.get(0).getImage());
//            roomResponseList.get(i).setCategory(findCategoryName(roomResponseList.get(i).getId()));
//        }
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

    public void delete(Long Id) {
        roomRepository.deleteById(Id);
    }


    public Room findRoomById(Long id) {
        return roomRepository.findById(id).get();
    }

    public Account findAccByRoomId(Long id) {
        return accountRepository.findAccountByRoomId(id);
    }
}
