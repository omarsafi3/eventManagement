package org.example.eventmanagement.service.impl;

import org.example.eventmanagement.entity.generated.Material;
import org.example.eventmanagement.entity.generated.Event;
import org.example.eventmanagement.entity.generated.EventMaterial;
import org.example.eventmanagement.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MaterialService {

    @Autowired
    private MaterialRepository repository;

    @Autowired
    private EventService eventService;  // You might need to inject EventService to access events


    public String addMaterial(Material material) {
        repository.save(material);
        return "Material Added Successfully";
    }


    public Material findById(Long id) {
        return repository.findById(id);
    }


    public List<Material> allMaterials() {
        return repository.findAll();
    }


    public Material findByName(String name) {
        return repository.findByName(name);
    }


    public List<Material> findByNameContaining(String keyword) {
        return repository.findByNameContaining(keyword);
    }


    public String updateMaterial(Material material) {
        repository.update(material);
        return "Material Updated Successfully";
    }


    public String deleteById(Long id) {
        repository.deleteById(id);
        return "Material Deleted Successfully";
    }


    public String deleteByName(String name) {
        repository.deleteByName(name);
        return "Material Deleted Successfully";
    }


    public List<Material> findAvailableMaterials(String startTime, String endTime, String day) throws ParseException {
        if (startTime == null || endTime == null || day == null) {
            throw new IllegalArgumentException("Invalid time range");
        }

        LocalTime startLocalTime = LocalTime.parse(startTime);
        LocalTime endLocalTime = LocalTime.parse(endTime);

        if (startLocalTime.isAfter(endLocalTime)) {
            throw new IllegalArgumentException("Invalid time range");
        }

        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime startZonedDateTime = startLocalTime.atDate(LocalDate.parse(day)).atZone(zoneId);
        ZonedDateTime endZonedDateTime = endLocalTime.atDate(LocalDate.parse(day)).atZone(zoneId);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse(day);
        List<Material> materials = allMaterials();
        List<Event> eventList = eventService.allEvents();

        for (Event event : eventList) {
            LocalTime eventStart = LocalTime.parse(event.getStartTime());
            LocalTime eventEnd = LocalTime.parse(event.getFinishTime());

            if (event.getDate().equals(date)) {
                boolean hasOverlap = !(endZonedDateTime.toLocalTime().isBefore(eventStart) || startZonedDateTime.toLocalTime().isAfter(eventEnd));
                if (hasOverlap) {
                    materials.removeAll(event.getEventMaterialWrapper().getEventMaterial().stream()
                            .map(EventMaterial::getMaterial)
                            .collect(Collectors.toList()));
                }
            }
        }

        return materials;
    }
}
