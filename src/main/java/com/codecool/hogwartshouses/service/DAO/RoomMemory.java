package com.codecool.hogwartshouses.service.DAO;

import com.codecool.hogwartshouses.model.Room;
import com.codecool.hogwartshouses.model.Student;
import com.codecool.hogwartshouses.model.types.PetType;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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

    public void addStudentToRoom(Student student, long roomId) {
        Optional<Room> optionalRoom = get(roomId);
        if (optionalRoom.isPresent()) {
            Room room = optionalRoom.get();
            Set<Student> roommates = room.getRoommates();
            roommates.add(student);
        }
    }

    @Override
    public Set<Room> findAvailableRooms() {
        return rooms.stream()
                .filter(room -> room.getRoommates().isEmpty())
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Room> findRoomsForRatOwners() {
        Predicate<Student> noCatOwner = student -> student.getType().equals(PetType.CAT);
        Predicate<Student> noOwlOwner = student -> student.getType().equals(PetType.OWL);
        return rooms.stream()
                .filter(room -> room.getRoommates().stream().noneMatch(noCatOwner) &&
                        room.getRoommates().stream().noneMatch(noOwlOwner))
                .collect(Collectors.toSet());
    }
}
