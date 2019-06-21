package com.kaitait.registration.repository;

import com.kaitait.registration.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    List<User> findByEmail(String email);
    List<User> findAll();

    User getById(String id);

    @Transactional
    String deleteByEmail(final String email);
}
