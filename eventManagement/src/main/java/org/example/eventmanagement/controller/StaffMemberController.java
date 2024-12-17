package org.example.eventmanagement.controller;

import org.example.eventmanagement.entity.generated.StaffMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.eventmanagement.service.impl.StaffMemberService;

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


}
