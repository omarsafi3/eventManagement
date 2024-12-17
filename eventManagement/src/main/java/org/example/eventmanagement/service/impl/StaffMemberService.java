package org.example.eventmanagement.service.impl;

import org.example.eventmanagement.dto.StaffMemberDTO;
import org.example.eventmanagement.entity.generated.StaffMember;
import org.example.eventmanagement.repository.StaffMemberRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StaffMemberService {

    private final StaffMemberRepository repository;

    public StaffMemberService(StaffMemberRepository repository) {
        this.repository = repository;
    }

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
}
