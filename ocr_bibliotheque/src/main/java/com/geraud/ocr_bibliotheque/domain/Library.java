package com.geraud.ocr_bibliotheque.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Library {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_library;

    @Column(unique = true)
    private String library_name;
    private String opening_hours;
    private String address;

    @OneToMany(mappedBy = "pk.library")
    @JsonIgnore
    private Set<Stock> stocks = new HashSet<>();
}
