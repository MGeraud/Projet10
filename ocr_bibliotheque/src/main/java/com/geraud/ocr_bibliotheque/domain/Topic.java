package com.geraud.ocr_bibliotheque.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@EqualsAndHashCode(exclude = {"books"})
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_topic;

    @Column(unique = true)
    private String keyword;

    @ManyToMany(mappedBy = "topics")
    @JsonIgnore
    private Set<Book> books;
}
