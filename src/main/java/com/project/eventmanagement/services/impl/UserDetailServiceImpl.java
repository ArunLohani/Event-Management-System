package com.project.eventmanagement.services.impl;

import com.project.eventmanagement.model.CustomUserDetails;
import com.project.eventmanagement.repository.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.project.eventmanagement.model.User;
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepo repo;

    UserDetailServiceImpl(UserRepo repo){
        this.repo = repo;

    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user =   repo.findByEmail(email);

        if(user == null){
            throw new UsernameNotFoundException("User not found with email : " + email);

        }

        return new CustomUserDetails(user);

    }
}
