package org.example.eventmanagement.service.impl;

import org.example.eventmanagement.entity.generated.Room;
import org.example.eventmanagement.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    // Create a new room
    public void createRoom(Room room) {

        roomRepository.save(room);
    }

    // Retrieve all rooms
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    // Retrieve a room by its ID
    public Room getRoomById(Long id) {
        Room room = roomRepository.findById(id);
        if (room == null) {
            throw new RuntimeException("Room with ID " + id + " not found");
        }
        return room;
    }

    // Retrieve a room by its name
    public Room getRoomByName(String name) {
        Room room = roomRepository.findByName(name);
        if (room == null) {
            throw new RuntimeException("Room with name '" + name + "' not found");
        }
        return room;
    }

    public List<Room> getRoomsBySurfaceGreaterThan(double surface) {
        return roomRepository.findBySurfaceGreaterThan(surface);
    }

    public List<Room> getRoomsByCapacityGreaterThan(int capacity) {
        return roomRepository.findByCapacityGreaterThan(capacity);
    }

    public List<Room> getRoomsByHourlyRateLessThan(double hourlyRate) {
        return roomRepository.findByHourlyRateLessThan(hourlyRate);
    }

    public Room updateRoom(Room room) {
        if (roomRepository.findById(room.getId()) == null) {
            throw new RuntimeException("Room with ID " + room.getId() + " not found");
        }
        roomRepository.update(room);
        return room;
    }


    public void deleteRoomById(Long id) {
        if (roomRepository.findById(id) == null) {
            throw new RuntimeException("Room with ID " + id + " not found");
        }
        roomRepository.deleteById(id);
    }
}
