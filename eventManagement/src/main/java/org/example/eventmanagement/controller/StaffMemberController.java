package org.example.eventmanagement.controller;

import org.example.eventmanagement.entity.generated.StaffMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.eventmanagement.service.impl.StaffMemberService;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/staffmembers")
public class StaffMemberController {

    @Autowired
    private StaffMemberService staffMemberService;

    @GetMapping
    public ResponseEntity<List<StaffMember>> getAllStaffMembers() {
        List<StaffMember> staffMembers = staffMemberService.allStaffMembers();
        return new ResponseEntity<>(staffMembers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<StaffMember> addStaffMember(@RequestBody StaffMember staffMember) {
        System.out.println(staffMember);
        StaffMember createdStaffMember = staffMemberService.addStaffMember(staffMember);
        System.out.println(staffMember);
        return new ResponseEntity<>(createdStaffMember, HttpStatus.CREATED);
    }

    @GetMapping("/available-staff")
    public ResponseEntity<List<StaffMember>> findAvailableStaffMembers(
            @RequestParam String start,
            @RequestParam String finish,
            @RequestParam String date) throws ParseException {
        List<StaffMember> staffMembers = staffMemberService.findAvailableStaffMembers(start, finish, date);

        if (staffMembers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(staffMembers, HttpStatus.OK);
    }

    @GetMapping("/skill-name/{skillName}")
    public ResponseEntity<List<StaffMember>> findBySkillName(@PathVariable String skillName) {
        List<StaffMember> staffMembers = staffMemberService.findBySkillName(skillName);

        if (staffMembers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(staffMembers, HttpStatus.OK);
    }




}
