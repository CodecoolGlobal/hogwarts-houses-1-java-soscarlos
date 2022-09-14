package com.codecool.hogwartshouses.service;

import com.codecool.hogwartshouses.model.Room;
import com.codecool.hogwartshouses.model.types.HouseType;
import com.codecool.hogwartshouses.service.DAO.RoomDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.WARN)
public class RoomServiceTest {

    private Set<Room> emptyRoom;
    private Set<Room> filledRoom;
    private Room testRoom;
    @Mock
    private RoomDAO roomDAO;
    @InjectMocks
    private RoomService service;

    @BeforeEach
    void initialize() {
        emptyRoom = new HashSet<>();
        filledRoom = new HashSet<>();
        testRoom = new Room(1, "Room Gryffindor 1", HouseType.GRYFFINDOR, new HashSet<>());
        filledRoom.add(testRoom);
    }

    @Test
    @Order(1)
    public void shouldReturnEmptySet() {
        when(roomDAO.getAll()).thenReturn(emptyRoom);
        Set<Room> emptySet = new HashSet<>();
        Set<Room> response = service.getRooms();

        assertThat(response).isEqualTo(emptySet);
    }

    @Test
    @Order(2)
    public void shouldReturnSetOfRooms() {
        Set<Room> expected = new HashSet<>();
        expected.add(testRoom);
        when(roomDAO.getAll()).thenReturn(filledRoom);
        Set<Room> response = service.getRooms();

        assertThat(response).isEqualTo(expected);
    }

    @Test
    @Order(3)
    void findRoomById_shouldReturnOptional() {
        long id = 1;
        Optional<Room> optionalRoom = Optional.of(testRoom);
        when(roomDAO.get(id)).thenReturn(optionalRoom);
        Optional<Room> response = service.findRoomById(id);

        assertThat(response).isPresent();
    }

    @Test
    @Order(4)
    void findRoomById_shouldReturnEmptyOptional() {
        long id = 2;
        Optional<Room> response = service.findRoomById(id);

        assertThat(response).isEmpty();
    }

    @Test
    @Order(5)
    void updateRoom_shouldReturnUpdatedOptional() {
        Room modified = new Room(testRoom.getId(), "Modified Room", HouseType.GRYFFINDOR, new HashSet<>());
        when(roomDAO.update(testRoom, testRoom.getId())).thenReturn(Optional.of(modified));
        Optional<Room> response = service.updateRoom(testRoom, testRoom.getId());

        assertThat(response).isEqualTo(Optional.of(modified));
    }

    @Test
    void updateRoom() {
    }

    @Test
    void deleteRoom() {
    }

    @Test
    void addStudentToRoom() {
    }

    @Test
    void findAvailableRooms() {
    }

    @Test
    void findRoomForRatOwners() {
    }

}
