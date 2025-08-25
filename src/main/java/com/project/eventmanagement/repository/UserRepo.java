package com.project.eventmanagement.repository;

import com.project.eventmanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {

    User findByEmail(String email);
    void deleteByEmail(String email);

}
