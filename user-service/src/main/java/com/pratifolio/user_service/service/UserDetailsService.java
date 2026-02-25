package com.pratifolio.user_service.service;

import com.pratifolio.user_service.model.User;
import com.pratifolio.user_service.model.UserPrinciple;
import com.pratifolio.user_service.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepo.findByUsername(username);

        if (user == null)
            throw new UsernameNotFoundException("User not found 404");

        return new UserPrinciple(user);
    }
}
