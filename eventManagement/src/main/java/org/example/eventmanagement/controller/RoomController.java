package org.example.eventmanagement.controller;

import org.example.eventmanagement.entity.generated.Room;
import org.example.eventmanagement.service.impl.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/api/v1/room")
public class RoomController {

    private final RoomService roomService;
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    // Create a new room
    @PostMapping
    public ResponseEntity<Room> createRoom(@RequestBody Room room) {
        roomService.createRoom(room);
        return new ResponseEntity<>(room, HttpStatus.CREATED);
    }

    // Retrieve all rooms
    @GetMapping
    public ResponseEntity<List<Room>> getAllRooms() {
        List<Room> rooms = roomService.getAllRooms();
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

    // Retrieve a room by its ID
    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable Long id) {
        Room room = roomService.getRoomById(id);
        return new ResponseEntity<>(room, HttpStatus.OK);
    }

    // Retrieve a room by its name
    @GetMapping("/by-name")
    public ResponseEntity<Room> getRoomByName(@RequestParam String name) {
        Room room = roomService.getRoomByName(name);
        return new ResponseEntity<>(room, HttpStatus.OK);
    }

    // Retrieve rooms with a surface greater than the specified value
    @GetMapping("/by-surface")
    public ResponseEntity<List<Room>> getRoomsBySurfaceGreaterThan(@RequestParam double surface) {
        List<Room> rooms = roomService.getRoomsBySurfaceGreaterThan(surface);
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

    // Retrieve rooms with a capacity greater than the specified value
    @GetMapping("/by-capacity")
    public ResponseEntity<List<Room>> getRoomsByCapacityGreaterThan(@RequestParam int capacity) {
        List<Room> rooms = roomService.getRoomsByCapacityGreaterThan(capacity);
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

    // Retrieve rooms with an hourly rate less than the specified value
    @GetMapping("/by-hourly-rate")
    public ResponseEntity<List<Room>> getRoomsByHourlyRateLessThan(@RequestParam double hourlyRate) {
        List<Room> rooms = roomService.getRoomsByHourlyRateLessThan(hourlyRate);
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

    // Update a room
    @PutMapping("/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable Long id, @RequestBody Room updatedRoom) {
        updatedRoom.setId(id); // Ensure the ID matches
        Room room= roomService.updateRoom(updatedRoom);
        return new ResponseEntity<>(room, HttpStatus.OK);
    }

    // Delete a room by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRoomById(@PathVariable Long id) {
        roomService.deleteRoomById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/available-rooms")
    public ResponseEntity<List<Room>> findAvailableRooms
            (@RequestParam String start, @RequestParam String end, @RequestParam String day)
            throws ParseException {
        List<Room> rooms = roomService.findAvailableRooms(start, end, day);
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }
}
