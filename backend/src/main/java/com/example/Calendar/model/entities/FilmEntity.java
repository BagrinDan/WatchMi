package com.example.Calendar.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "films")
@Data
@EqualsAndHashCode(callSuper = true)

public class FilmEntity extends BaseEntity{
    @Column(nullable = false, unique = true, length = 30)
    private String title;

    @Column(length = 50)
    private String description;

    @Column(length = 30)
    private String genre;

    private LocalTime date;

    @ElementCollection
    @CollectionTable(name = "film_with_who",
                    joinColumns = @JoinColumn(name = "film_id")
    )
    @Column(name = "person")
    private List<String> withWho = new ArrayList<>(); //TODO: 1. Option "Alone" by default, 2. Constrains
}
