package com.javarush.domain;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "category", schema = "movie")
@Data
@NoArgsConstructor
public class Category {
    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Byte id;

    @Column(name = "name")
    private String name;

    @Column(name = "last_update")
    @UpdateTimestamp
    private LocalDateTime lastUpdate;

    @ManyToMany
    @JoinTable(name = "film_category",
            joinColumns = @JoinColumn(name = "category_id", referencedColumnName = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "film_id", referencedColumnName = "film_id"))
    private Set<Film> films;
}
