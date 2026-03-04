package com.example.Calendar.repository;

import com.example.Calendar.model.entities.FilmEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FilmRepository extends JpaRepository<FilmEntity, Long> {
    FilmEntity findFilmByUuid(UUID uuid);
    /*
        save() → INSERT/UPDATE

        findById() → SELECT по id

        findAll() → SELECT всех строк

        delete() → DELETE
    */
}
