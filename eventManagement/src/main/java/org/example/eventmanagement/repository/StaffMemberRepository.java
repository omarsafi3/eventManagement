package org.example.eventmanagement.repository;

import jakarta.xml.bind.*;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import org.example.eventmanagement.entity.generated.Material;
import org.example.eventmanagement.entity.generated.StaffMember;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class StaffMemberRepository {

    private static final String FILE_PATH = "staff_members.xml";
    private final File file;
    private final JAXBContext context;

    public StaffMemberRepository() {
        try {
            this.file = new File(FILE_PATH);
            this.context = JAXBContext.newInstance(StaffMemberListWrapper.class);

            // Initialize the file if it doesn't exist
            if (!file.exists()) {
                saveAll(new ArrayList<>());
            }
        } catch (JAXBException e) {
            throw new RuntimeException("Error initializing JAXB context", e);
        }
    }

    private void saveAll(List<StaffMember> staffMembers) {
        try {
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(new StaffMemberListWrapper(staffMembers), file);
        } catch (JAXBException e) {
            throw new RuntimeException("Error saving staff members to XML", e);
        }
    }

    public void save(StaffMember staffMember) {
        List<StaffMember> staffMembers = findAll();
        if (staffMembers == null) {
            staffMembers = new ArrayList<>();
        }




        staffMembers.add(staffMember);
        saveAll(staffMembers);
    }


    public List<StaffMember> findAll() {
        try {
            Unmarshaller unmarshaller = context.createUnmarshaller();
            StaffMemberListWrapper wrapper = (StaffMemberListWrapper) unmarshaller.unmarshal(file);
            return wrapper != null ? wrapper.getStaffMembers() : new ArrayList<>();
        } catch (JAXBException e) {
            System.out.println("Error reading staff members from XML. Returning empty list.");
            return new ArrayList<>();
        }
    }

    public StaffMember findById(Long id) {
        List<StaffMember> staffMembers = findAll();
        return staffMembers.stream()
                .filter(staffMember -> staffMember.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public StaffMember findByEmail(String email) {
        List<StaffMember> staffMembers = findAll();
        return staffMembers.stream()
                .filter(staffMember -> staffMember.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    public List<StaffMember> findByFullNameContaining(String keyword) {
        return findAll().stream()
                .filter(staffMember -> staffMember.getFullName().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    public void update(StaffMember staffMember) {
        List<StaffMember> staffMembers = findAll();
        for (int i = 0; i < staffMembers.size(); i++) {
            if (staffMembers.get(i).getId() == staffMember.getId()) {
                staffMembers.set(i, staffMember);
                break;
            }
        }
        saveAll(staffMembers);
    }

    public void deleteById(Long id) {
        List<StaffMember> staffMembers = findAll();
        staffMembers.removeIf(staffMember -> staffMember.getId() == id);
        saveAll(staffMembers);
    }

    public void deleteByEmail(String email) {
        List<StaffMember> staffMembers = findAll();
        staffMembers.removeIf(staffMember -> staffMember.getEmail().equals(email));
        saveAll(staffMembers);
    }

    // Wrapper class for marshalling and unmarshalling
    @XmlRootElement(name = "staff_members")
    private static class StaffMemberListWrapper {
        private List<StaffMember> staffMembers;

        public StaffMemberListWrapper() {
        }

        public StaffMemberListWrapper(List<StaffMember> staffMembers) {
            this.staffMembers = staffMembers;
        }

        @XmlElement(name = "staff_member") // Match individual item names in XML
        public List<StaffMember> getStaffMembers() {
            return staffMembers;
        }

        public void setStaffMembers(List<StaffMember> staffMembers) {
            this.staffMembers = staffMembers;
        }
    }
}
