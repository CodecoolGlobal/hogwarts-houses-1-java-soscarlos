package com.codecool.hogwartshouses.service;

import com.codecool.hogwartshouses.model.Room;
import com.codecool.hogwartshouses.service.DAO.RoomDAO;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class RoomService {
    private final RoomDAO roomDAO;

    public RoomService(RoomDAO roomDAO) {
        this.roomDAO = roomDAO;
    }

    public Room saveRoom(Room room){
        roomDAO.save(room);
        return room;
    }

    public Set<Room> getRooms(){
        return roomDAO.getAll();
    }
    public Room updateRoom(Room room, long id){
        roomDAO.update(room, id);
        return room;
    }
    public void deleteRoom(long id){
        roomDAO.delete(id);
    }
}
