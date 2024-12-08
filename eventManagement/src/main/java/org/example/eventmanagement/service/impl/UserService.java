package org.example.eventmanagement.service.impl;

import org.example.eventmanagement.dto.UserDTO;
import org.example.eventmanagement.entity.User;
import org.example.eventmanagement.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public UserService(UserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Find the user by email
        User userDetail = repository.findByEmail(username);
        if (userDetail == null) {
            throw new UsernameNotFoundException("User not found with email: " + username);
        }
        System.out.println("User Roles: " + userDetail.getRoles());
        return userDetail;
    }

    public String addUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        repository.save(user);
        return "User Added Successfully";
    }

    public User findById(Long id) {
        return repository.findById(id);
    }

    public List<User> allUsers() {
        return new ArrayList<>(repository.findAll());
    }


    public UserDTO convertToDTO(User user) {
        // Map the User fields
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFullName(user.getFullName());
        userDTO.setEmail(user.getEmail());

        // Check if reviews exist, map them to DTOs


        return userDTO;
    }

}
