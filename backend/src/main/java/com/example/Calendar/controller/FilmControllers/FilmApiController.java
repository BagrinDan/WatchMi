package com.example.Calendar.controller.FilmControllers;


import com.example.Calendar.model.dto.request.FilmRequest;
import com.example.Calendar.model.dto.response.FilmResponse;
import com.example.Calendar.service.intefaces.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/films")
public class FilmApiController {

    private final FilmService filmService;

    @Autowired
    public FilmApiController(FilmService filmService){
        this.filmService = filmService;
    }

    @PostMapping("/createFilmPlan")
    public ResponseEntity<String> createFilmPlan(@RequestBody FilmRequest filmRequest) { // TODO: Change name of the function
        filmService.createFilm(filmRequest);
        return ResponseEntity.ok("[*] Success: Film Plan was created");
    }

    @GetMapping("/getAllFilms")
    public List<FilmResponse> getAllFilms(){
        return filmService.getAllFilms();
    }

    @PutMapping("/updateFilm/{uuid}")
    public ResponseEntity<String> updateFilm(@PathVariable UUID uuid, @RequestBody FilmRequest filmRequest){
        filmService.updateFilm(uuid, filmRequest);
        return ResponseEntity.ok("[*] Success: Film Plan was updated");
    }
}
