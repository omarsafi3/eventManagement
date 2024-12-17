package org.example.eventmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StaffMemberDTO {
    private long id;
    private String fullName;
    private String email;
    private String roles;
}
