package com.casemodule6_be.service.room;

import com.casemodule6_be.dto.room.RoomRequest;
import com.casemodule6_be.dto.room.RoomResponse;


import java.util.Date;
import java.util.List;

public interface RoomService {
    List<RoomResponse> findTopRent();

    List<RoomResponse> getAll();



    List<RoomResponse> findForMany(Long idAddress, Long idCategory, Double minPrice, Double maxPrice, String checkIn, String checkOut);



    RoomResponse findRoomById(Long id);

    RoomResponse save(RoomRequest roomRequest);
    RoomResponse update(RoomRequest roomRequest);


    void delete(Long Id);




}
