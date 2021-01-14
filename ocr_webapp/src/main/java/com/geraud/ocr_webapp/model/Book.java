package com.geraud.ocr_webapp.model;


import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class Book {


    private Long idbook;

    private String isbn;

    private String title;

    private String description;
    private int year;
    private String image;

    private Category category;

    private Set<Author> authors = new HashSet<>();

    private Set<Topic> topics = new HashSet<>();

    private Publisher publisher;

    private Set<Stock> stocks = new HashSet<>();
}
