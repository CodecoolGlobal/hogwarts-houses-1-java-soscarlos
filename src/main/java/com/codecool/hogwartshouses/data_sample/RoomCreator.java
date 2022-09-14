package com.codecool.hogwartshouses.data_sample;

import com.codecool.hogwartshouses.model.Room;
import com.codecool.hogwartshouses.service.DAO.RoomMemory;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class RoomCreator {
    private final RoomMemory roomMemory;

    public RoomCreator(RoomMemory roomMemory) {
        this.roomMemory = roomMemory;
    }

    public void initialize(Set<Room> roomSet) {
        roomSet.forEach(roomMemory::save);
    }
}
