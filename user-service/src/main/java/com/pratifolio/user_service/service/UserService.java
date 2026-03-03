package com.pratifolio.user_service.service;

import com.pratifolio.user_service.model.User;
import com.pratifolio.user_service.model.UserDTO;
import com.pratifolio.user_service.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public ResponseEntity<?> registerUser(User user) {
        try {
            user.setPassword(encoder.encode(user.getPassword()));
            userRepo.save(user);
            return new ResponseEntity<>("Created User", HttpStatus.CREATED);
        } catch (Exception e) {
             return new ResponseEntity<>("Error Creating User - Try Again", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<UserDTO> getUserDetails(int id) {
        User user = userRepo.findById(id).get();

        UserDTO userDTO = new UserDTO(
                user.getUsername(),
                user.getEmail()
        );

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
}
