package org.example.eventmanagement.service.impl;

import org.example.eventmanagement.dto.StaffMemberDTO;
import org.example.eventmanagement.entity.generated.EventStaff;
import org.example.eventmanagement.entity.generated.StaffMember;
import org.example.eventmanagement.repository.EventRepository;
import org.example.eventmanagement.entity.generated.Event;
import org.example.eventmanagement.repository.StaffMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StaffMemberService {

    @Autowired
    private StaffMemberRepository repository;

    @Autowired
    private EventService eventService;

    public StaffMember addStaffMember(StaffMember staffMember) {
        repository.save(staffMember);
        return staffMember;
    }

    public StaffMember findById(long id) {
        return repository.findById(id);
    }

    public List<StaffMember> allStaffMembers() {
        return new ArrayList<>(repository.findAll());
    }

    public StaffMemberDTO convertToDTO(StaffMember staffMember) {
        StaffMemberDTO staffMemberDTO = new StaffMemberDTO();
        staffMemberDTO.setId(staffMember.getId());
        staffMemberDTO.setFullName(staffMember.getFullName());
        staffMemberDTO.setEmail(staffMember.getEmail());
        staffMemberDTO.setRoles(staffMember.getRoles());

        return staffMemberDTO;
    }

    public String updateStaffMember(StaffMember staffMember) {
        Optional<StaffMember> existingStaffMember = Optional.ofNullable(repository.findById(staffMember.getId()));
        if (existingStaffMember.isPresent()) {
            repository.update(staffMember);
            return "Staff Member Updated Successfully";
        } else {
            return "Staff Member Not Found";
        }
    }

    public String deleteStaffMember(long id) {
        StaffMember existingStaffMember = repository.findById(id);
        if (existingStaffMember != null) {
            repository.deleteById(id);
            return "Staff Member Deleted Successfully";
        } else {
            return "Staff Member Not Found";
        }
    }

    public List<StaffMember> findBySkillId(long skillId) {
        List<StaffMember> allStaffMembers = repository.findAll();
        return allStaffMembers.stream()
                .filter(staffMember -> staffMember.getStaffSkillWrapper() != null)
                .filter(staffMember -> staffMember.getStaffSkillWrapper().getStaffSkill() != null)
                .filter(staffMember -> staffMember.getStaffSkillWrapper().getStaffSkill().stream()
                        .anyMatch(skill -> skill.getId() == skillId))
                .collect(Collectors.toList());
    }

    public List<StaffMember> findBySkillName(String skillName) {
        List<StaffMember> allStaffMembers = repository.findAll();
        return allStaffMembers.stream()
                .filter(staffMember -> staffMember.getStaffSkillWrapper() != null)
                .filter(staffMember -> staffMember.getStaffSkillWrapper().getStaffSkill() != null)
                .filter(staffMember -> staffMember.getStaffSkillWrapper().getStaffSkill().stream()
                        .anyMatch(skill -> skill.getName().equalsIgnoreCase(skillName)))
                .collect(Collectors.toList());
    }

    public List<StaffMember> findAvailableStaffMembers(String startTime, String endTime, String day) throws ParseException {
        List<Event> eventList = eventService.allEvents();
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

        List<StaffMember> staffMembers = allStaffMembers();

        for (Event event: eventList) {
            LocalTime assignmentStart = LocalTime.parse(event.getStartTime(), DateTimeFormatter.ISO_LOCAL_TIME);
            LocalTime assignmentEnd = LocalTime.parse(event.getFinishTime(), DateTimeFormatter.ISO_LOCAL_TIME);

            if (event.getDate().equals(date)) {
                boolean hasOverlap = !(endZonedDateTime.toLocalTime().isBefore(assignmentStart) || startZonedDateTime.toLocalTime().isAfter(assignmentEnd));
                if (hasOverlap) {
                    staffMembers.removeAll(event.getEventStaffWrapper().getEventStaff().stream()
                            .map(EventStaff::getStaffMember)
                            .collect(Collectors.toList()));
                    //System.out.println("Removed " + eventStaff.getStaffMember().getName() + " from available staff members");
                    //System.out.println("Staff member " + eventStaff.getStaffMember().getName() + " is unavailable for the requested time range");
                }
            }
        }
        return staffMembers;
    }

}
