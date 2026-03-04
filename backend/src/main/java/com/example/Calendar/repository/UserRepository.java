package com.example.Calendar.repository;

import com.example.Calendar.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByUsername(String username);
    Boolean existsUserByUsername(String username);
}

/*
    save() → INSERT/UPDATE

    findById() → SELECT по id

    findAll() → SELECT всех строк

    delete() → DELETE
*/