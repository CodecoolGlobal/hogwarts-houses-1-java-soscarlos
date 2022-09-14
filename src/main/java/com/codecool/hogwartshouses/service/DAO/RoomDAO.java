package com.codecool.hogwartshouses.service.DAO;

import com.codecool.hogwartshouses.model.Room;
import com.codecool.hogwartshouses.model.Student;

import java.util.Optional;
import java.util.Set;

public interface RoomDAO extends ModelDAO<Room> {
    void addStudentToRoom(Student student, long roomId);
    Set<Room> findAvailableRooms();
}