package com.codecool.hogwartshouses.service.DAO;

import com.codecool.hogwartshouses.model.Room;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public class RoomMemory implements RoomDAO {

    private final Set<Room> rooms;

    public RoomMemory(Set<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public Optional<Room> get(long id) {
        return rooms.stream().filter(room -> room.getId() == id).findFirst();
    }

    @Override
    public Set<Room> getAll() {
        return rooms;
    }

    @Override
    public void save(Room room) {
        rooms.add(room);
    }

    @Override
    public Optional<Room> update(Room room, long id) {
        boolean idsMatch = room.getId() == id;
        if (idsMatch) {
            boolean deleted = delete(id);
            if (deleted) rooms.add(room);
        }
        return get(room.getId());
    }

    @Override
    public boolean delete(long id) {
        return rooms.removeIf(room -> room.getId() == id);
    }
}
