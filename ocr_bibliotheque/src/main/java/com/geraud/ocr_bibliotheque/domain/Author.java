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
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "firstname_lastname" , columnNames = {"firstname" , "lastname"})
})
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_author;

    private String firstname;
    private String lastname;

    @ManyToMany(mappedBy = "authors")
    @JsonIgnore
    private Set<Book> books;
}
