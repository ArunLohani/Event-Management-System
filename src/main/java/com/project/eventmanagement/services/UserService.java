package com.project.eventmanagement.services;

import com.project.eventmanagement.dto.AddUserDTO;
import com.project.eventmanagement.dto.UserDTO;
import com.project.eventmanagement.model.User;

import java.util.Arrays;
import java.util.List;

public interface UserService {


     UserDTO saveNewUser(AddUserDTO user);

     UserDTO saveNewAdmin(AddUserDTO user);

     void saveUser(User user);

     List<User> getAll();

     User findById(Long id);
     void deleteById(Long id);

     void deleteByUsername(String email);

     User findByEmail(String email);
}
