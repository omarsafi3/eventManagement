package org.example.eventmanagement.service.impl;

import org.example.eventmanagement.entity.generated.Event;
import org.example.eventmanagement.entity.generated.Room;
import org.example.eventmanagement.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private EventService eventService;

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

    public List<Room> findAvailableRooms(String startTime, String endTime, String day) throws ParseException {
        List<Event> events = eventService.allEvents();
        if (startTime == null || endTime == null || day == null) {
            throw new IllegalArgumentException("Invalid time range");
        }

        // Parse startTime and endTime as LocalTime (for local time zone handling)
        LocalTime startLocalTime = LocalTime.parse(startTime);
        LocalTime endLocalTime = LocalTime.parse(endTime);

        if (startLocalTime.isAfter(endLocalTime)) {
            throw new IllegalArgumentException("Invalid time range");
        }

        // Convert to ZonedDateTime to adjust for local time zone (e.g., system default zone)
        ZoneId zoneId = ZoneId.systemDefault();  // You can use a specific zone if needed
        ZonedDateTime startZonedDateTime = startLocalTime.atDate(LocalDate.parse(day)).atZone(zoneId);
        ZonedDateTime endZonedDateTime = endLocalTime.atDate(LocalDate.parse(day)).atZone(zoneId);

        // Convert ZonedDateTime back to Time for comparison with event times
        Time start = Time.valueOf(startZonedDateTime.toLocalTime());
        Time end = Time.valueOf(endZonedDateTime.toLocalTime());

        // Parse the day string to Date (already in your code)
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse(day);

        // List of all rooms
        List<Room> rooms = getAllRooms();
        Set<Room> unavailableRooms = new HashSet<>();

        // Check events for availability based on the converted times
        for (Event event : events) {
            LocalTime eventStart = LocalTime.parse(event.getStartTime(), DateTimeFormatter.ISO_LOCAL_TIME);
            LocalTime eventEnd = LocalTime.parse(event.getFinishTime(), DateTimeFormatter.ISO_LOCAL_TIME);

            if (event.getDate().equals(date)) {
                boolean hasOverlap = !(endZonedDateTime.toLocalTime().isBefore(eventStart) || startZonedDateTime.toLocalTime().isAfter(eventEnd));
                if (hasOverlap) {
                    unavailableRooms.add(event.getRoom());
                    rooms.remove(event.getRoom());
                    System.out.println("I removed " + rooms.toString());
                    System.out.println("Room " + event.getRoom().getName() + " is unavailable for the requested time range");

                }
            }
        }
        System.out.println("Unavailable rooms: " + unavailableRooms);
        //rooms.removeAll(unavailableRooms);
        return rooms;
    }

}
