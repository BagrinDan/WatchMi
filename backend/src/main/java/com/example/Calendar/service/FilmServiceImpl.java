package com.example.Calendar.service;

import com.example.Calendar.model.dto.request.FilmRequest;
import com.example.Calendar.model.dto.response.FilmResponse;
import com.example.Calendar.model.entities.FilmEntity;
import com.example.Calendar.repository.FilmRepository;
import com.example.Calendar.service.intefaces.FilmService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.UUID;


@Service
@Transactional
public class FilmServiceImpl implements FilmService {
    private final FilmRepository filmRepository;

    @Autowired
    public FilmServiceImpl(FilmRepository filmRepository){
        this.filmRepository = filmRepository;
    }


    //CREATE
    public void createFilm(FilmRequest filmRequest){ // TODO: Maybe is different way do create? (prob builder)
        FilmEntity filmEntity = new FilmEntity();

        filmEntity.setTitle(filmRequest.getTitle());
        filmEntity.setUuid(UUID.randomUUID());
        filmEntity.setDescription(filmRequest.getDescription());
        filmEntity.setGenre(filmRequest.getGenre());
        filmEntity.setDate(LocalTime.now());
        filmEntity.setWithWho(filmRequest.getWithWho());

        filmRepository.save(filmEntity);
    }

    //READ
    public List<FilmResponse> getAllFilms(){
        List<FilmEntity> filmEntities = filmRepository.findAll();
        List<FilmResponse> filmResponses = new ArrayList<>();

        ListIterator<FilmResponse> listIterator = filmResponses.listIterator();

        for(FilmEntity filmEntity : filmEntities){
            FilmResponse filmResponse = new FilmResponse();

            filmResponse.setTitle(filmEntity.getTitle());
            filmResponse.setDate(filmEntity.getDate());
            filmResponse.setDescription(filmEntity.getDescription());
            filmResponse.setWithWho(filmEntity.getWithWho());

            listIterator.add(filmResponse);
        }

        return filmResponses;
    }

    //UPDATE
    public void updateFilm(UUID uuid, FilmRequest filmRequest){
        FilmEntity filmEntity = filmRepository.findFilmByUuid(uuid);

        if(filmEntity == null){
            throw new EntityNotFoundException("[*] No such film ");
        }

        filmEntity.setTitle(filmRequest.getTitle());
        filmEntity.setDescription(filmRequest.getDescription());
        filmEntity.setGenre(filmRequest.getGenre());
        filmEntity.setWithWho(filmRequest.getWithWho());

        filmRepository.save(filmEntity);
    }

    //DELETE
    public void deleteFilm(UUID uuid){
        FilmEntity filmEntity = filmRepository.findFilmByUuid(uuid);

        if(filmEntity == null){
            throw new EntityNotFoundException("[*] No such film ");
        }

        filmRepository.delete(filmEntity);
    }
}
