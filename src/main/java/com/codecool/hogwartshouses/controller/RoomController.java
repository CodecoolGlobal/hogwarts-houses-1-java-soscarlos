package com.codecool.hogwartshouses.controller;

import com.codecool.hogwartshouses.model.Room;
import com.codecool.hogwartshouses.service.RoomService;
import lombok.Value;
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

    public RoomController(RoomService service) {
        this.service = service;
    }

    @GetMapping
    public String showRooms(Model model){
        Set<Room> rooms = service.getRooms();
        System.out.println(rooms);
        model.addAttribute("rooms", rooms);
        return "rooms";
    }
    @PostMapping
    public ResponseEntity<Room> save(@RequestBody Room room){
        Room newRoom = service.saveRoom(room);
        return new ResponseEntity<>(newRoom, HttpStatus.OK);
    }
    @GetMapping("/{roomId}")
    public ResponseEntity<Room> findRoom(@PathVariable("roomId") long id){
        Optional<Room> foundRoom = service.findRoomById(id);
        return foundRoom
                .map(room -> new ResponseEntity<>(room, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }
}
