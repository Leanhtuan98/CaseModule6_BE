package com.casemodule6_be.service.room;

import com.casemodule6_be.dto.room.RoomRequest;
import com.casemodule6_be.dto.room.RoomResponse;
import com.casemodule6_be.model.Account;
import com.casemodule6_be.model.Category;
import com.casemodule6_be.model.Room;

import java.util.List;

public interface RoomService {
    List<RoomRequest> findTopRent();

    String findCategoryName(Long idCategory);


    Category findCategoryById(Long idCategory);

    List<RoomResponse> getRoomForGuest();

    List<Room> listRoom();

    List<RoomResponse> find(String categoryName, String addressName, double price1, double price2, String
            checkin, String checkout);



    Room findRoomById(Long id);

    RoomResponse save(RoomRequest roomRequest);

    void delete(Long Id);


    Account findAccByRoomId(Long id);

}
