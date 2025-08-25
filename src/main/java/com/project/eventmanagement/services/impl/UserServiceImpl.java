package com.project.eventmanagement.services.impl;

import com.project.eventmanagement.dto.AddUserDTO;
import com.project.eventmanagement.dto.UserDTO;
import com.project.eventmanagement.model.User;
import com.project.eventmanagement.repository.UserRepo;
import com.project.eventmanagement.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

   private final UserRepo repo;
    private  final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    UserServiceImpl(UserRepo repo , PasswordEncoder passwordEncoder,ModelMapper modelMapper){
       this.repo = repo;
       this.passwordEncoder = passwordEncoder;
       this.modelMapper = modelMapper;
   }

@Override
    public UserDTO saveNewUser(AddUserDTO userDTO){

        User user = modelMapper.map(userDTO , User.class);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
       UserDTO newUser = modelMapper.map( repo.save(user), UserDTO.class) ;
       return newUser;
    }
    @Override
    public UserDTO saveNewAdmin(AddUserDTO userDTO){
        User user = modelMapper.map(userDTO , User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER", "ADMIN"));
        UserDTO newAdmin = modelMapper.map( repo.save(user), UserDTO.class) ;
        return newAdmin;
    }

    @Override
    public void saveUser(User user){
        repo.save(user);
    }

    @Override
    public List<User> getAll(){
        return repo.findAll();
    }

    @Override
    public User findById(Long id){
        return repo.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id){
        repo.deleteById(id);
    }

    @Override
    public void deleteByUsername(String email){
        repo.deleteByEmail(email);
    }

    @Override
    public User findByEmail(String email) {
        return repo.findByEmail(email);
    }
}
