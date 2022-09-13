package com.codecool.hogwartshouses.service.DAO;

import com.codecool.hogwartshouses.model.Room;

import java.util.Optional;
import java.util.Set;

public interface RoomDAO {
    Optional<Room> get(long id);

    Set<Room> getAll();

    void save(Room room);

    void update(Room room, long id);

    boolean delete(long id);
}