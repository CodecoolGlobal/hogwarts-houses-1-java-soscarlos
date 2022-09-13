package com.codecool.hogwartshouses.data_sample;

import com.codecool.hogwartshouses.service.DAO.RoomMemory;
import org.springframework.stereotype.Component;

@Component
public class RoomCreator {
    RoomMemory roomMemory;

    public RoomCreator(RoomMemory roomMemory) {
        this.roomMemory = roomMemory;
    }

    public RoomCreator() {
        initialize();
    }

    public void initialize() {
        //TODO initialize rooms
    }
}
