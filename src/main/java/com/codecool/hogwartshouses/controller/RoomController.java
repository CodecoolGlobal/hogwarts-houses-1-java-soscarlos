package com.codecool.hogwartshouses.controller;

import com.codecool.hogwartshouses.data_sample.RoomCreator;
import com.codecool.hogwartshouses.model.Room;
import com.codecool.hogwartshouses.model.Student;
import com.codecool.hogwartshouses.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/rooms")
public class RoomController {
    private final RoomService service;

    private final RoomCreator creator;

    public RoomController(RoomService service, RoomCreator creator) {
        this.service = service;
        this.creator = creator;
    }
    @GetMapping
    public String showRooms(Model model) {
        Set<Room> rooms = service.getRooms();
        model.addAttribute("rooms", rooms);
        return "rooms";
    }
    @PostMapping("/initialize")
    public ResponseEntity<Set<Room>> createRooms(@RequestBody Set<Room> rooms){
        creator.initialize(rooms);
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Room> save(@RequestBody Room room) {
        Room newRoom = service.saveRoom(room);
        return new ResponseEntity<>(newRoom, HttpStatus.OK);
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<Room> findRoom(@PathVariable("roomId") long id) {
        Optional<Room> foundRoom = service.findRoomById(id);
        return foundRoom
                .map(room -> new ResponseEntity<>(room, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping("/{roomId}")
    public ResponseEntity<String> deleteRoom(@PathVariable("roomId") long id) {
        return service.deleteRoom(id) ?
                new ResponseEntity<>("Deleted successfully", HttpStatus.OK) :
                new ResponseEntity<>("Room not found", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{roomId}")
    public ResponseEntity<Room> updateRoom(@RequestBody Room room, @PathVariable("roomId") long id) {
        Optional<Room> updatedRoom = service.updateRoom(room, id);
        return updatedRoom
                .map(foundRoom -> new ResponseEntity<>(foundRoom, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }
    @PostMapping("/{roomId}/students")
    public ResponseEntity<Room> addStudentToRoom(@RequestBody Student student, @PathVariable("roomId") long roomId){
        Optional<Room> filledRoom = service.addStudentToRoom(student, roomId);
        return filledRoom
                .map(room -> new ResponseEntity<>(room, HttpStatus.OK))
                .orElseGet(()-> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @GetMapping("/available")
    public ResponseEntity<Set<Room>> getEmptyRooms(){
        Set<Room> availableRooms = service.findAvailableRooms();
        return new ResponseEntity<>(availableRooms, HttpStatus.OK);
    }
    @GetMapping("/rat-owners")
    public ResponseEntity<Set<Room>> getRoomsRatOwners(){
        Set<Room> availableRooms = service.findRoomForRatOwners();
        return new ResponseEntity<>(availableRooms, HttpStatus.OK);
    }
}
