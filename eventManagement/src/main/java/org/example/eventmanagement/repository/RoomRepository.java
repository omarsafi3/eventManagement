package org.example.eventmanagement.repository;

import jakarta.xml.bind.*;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import org.example.eventmanagement.entity.generated.Room;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RoomRepository {

    private static final String FILE_PATH = "rooms.xml";
    private final File file;
    private final JAXBContext context;

    public RoomRepository() {
        try {
            this.file = new File(FILE_PATH);
            this.context = JAXBContext.newInstance(RoomListWrapper.class);

            // Initialize the file if it doesn't exist
            if (!file.exists()) {
                saveAll(new ArrayList<>());
            }
        } catch (JAXBException e) {
            throw new RuntimeException("Error initializing JAXB context", e);
        }
    }

    private void saveAll(List<Room> rooms) {
        try {
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(new RoomListWrapper(rooms), file);
        } catch (JAXBException e) {
            throw new RuntimeException("Error saving rooms to XML", e);
        }
    }

    public void save(Room room) {
        List<Room> rooms = findAll();
        if (rooms == null) {
            rooms = new ArrayList<>();
        }
        rooms.add(room);
        saveAll(rooms);
    }

    public List<Room> findAll() {
        try {
            Unmarshaller unmarshaller = context.createUnmarshaller();
            RoomListWrapper wrapper = (RoomListWrapper) unmarshaller.unmarshal(file);
            return wrapper != null ? wrapper.getRooms() : new ArrayList<>();
        } catch (JAXBException e) {
            System.out.println("Error reading rooms from XML. Returning empty list.");
            return new ArrayList<>();
        }
    }

    public Room findById(Long id) {
        return findAll().stream()
                .filter(room -> room.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Room findByName(String name) {
        return findAll().stream()
                .filter(room -> room.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public List<Room> findBySurfaceGreaterThan(double surface) {
        return findAll().stream()
                .filter(room -> room.getSurface() > surface)
                .collect(Collectors.toList());
    }

    public List<Room> findByCapacityGreaterThan(int capacity) {
        return findAll().stream()
                .filter(room -> room.getCapacity() > capacity)
                .collect(Collectors.toList());
    }

    public List<Room> findByHourlyRateLessThan(double hourlyRate) {
        return findAll().stream()
                .filter(room -> room.getHourlyRate() < hourlyRate)
                .collect(Collectors.toList());
    }

    public void update(Room room) {
        List<Room> rooms = findAll();
        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).getId() == room.getId()) {
                rooms.set(i, room);
                break;
            }
        }
        saveAll(rooms);
    }

    public void deleteById(Long id) {
        List<Room> rooms = findAll();
        rooms.removeIf(room -> room.getId() == id);
        saveAll(rooms);
    }

    // Wrapper class for marshalling and unmarshalling
    @XmlRootElement(name = "rooms")
    private static class RoomListWrapper {
        private List<Room> rooms;

        public RoomListWrapper() {
        }

        public RoomListWrapper(List<Room> rooms) {
            this.rooms = rooms;
        }

        @XmlElement(name = "room") // Match individual item names in XML
        public List<Room> getRooms() {
            return rooms;
        }

        public void setRooms(List<Room> rooms) {
            this.rooms = rooms;
        }
    }
}
