package com.example.Calendar.service.intefaces;

import com.example.Calendar.model.dto.request.FilmRequest;
import com.example.Calendar.model.dto.response.FilmResponse;


import java.util.List;
import java.util.UUID;

public interface FilmService {
    void createFilm(FilmRequest filmRequest);

    List<FilmResponse> getAllFilms();
    void updateFilm(UUID uuid, FilmRequest filmRequest);
    void deleteFilm(UUID uuid);
}
