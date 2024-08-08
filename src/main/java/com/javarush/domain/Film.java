package com.javarush.domain;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "film", schema = "movie")
@NoArgsConstructor
@Data
public class Film {

    @Id
    @Column(name = "film_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    @Column(name = "title")
    private String title;

    @Column(name = "description", columnDefinition = "text")
    @Type(type = "text")
    private String description;

    @Column(name = "release_year", columnDefinition = "year")
    private Year releaseYear;

    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language;

    @ManyToOne
    @JoinColumn(name = "original_language_id")
    private Language originalLanguage;

    @Column(name = "rental_duration")
    private Byte rentalDuration;

    @Column(name = "rental_rate")
    private BigDecimal rentalRate;

    @Column(name = "length")
    private Short length;

    @Column(name = "replacement_cost")
    private BigDecimal replacementCost;

    @Column(name = "rating", columnDefinition = "enum('G', 'PG', 'PG-13', 'R', 'NC-17')")
    private Rating rating;

    @Column(name = "special_features", columnDefinition = "set('Trailers', 'Commentaries', 'Deleted Scenes', 'Behind the Scenes')")
    private String specialFeatures;

    @Column(name = "last_update")
    @UpdateTimestamp
    private LocalDateTime lastUpdate;

    @ManyToMany
    @JoinTable(name = "film_category",
    joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "film_id"),
    inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "category_id"))
    private Set<Category> categories;

    @ManyToMany
    @JoinTable(name = "film_actor",
            joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id", referencedColumnName = "actor_id"))
    private Set<Actor> actors;
}
