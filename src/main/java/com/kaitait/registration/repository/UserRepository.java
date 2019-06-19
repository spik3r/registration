package com.kaitait.registration.repository;

import com.kaitait.registration.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByEmail(String email);
    List<User> findAll();

    List<User> getById(String id);
}
