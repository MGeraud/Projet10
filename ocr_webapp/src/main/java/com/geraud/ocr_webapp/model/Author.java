package com.geraud.ocr_webapp.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Getter
@Setter

public class Author {


    private Long id_author;

    private String firstname;
    private String lastname;

    private Set<Book> books;
}
