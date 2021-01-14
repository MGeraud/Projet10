package com.geraud.ocr_webapp.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Getter
@Setter
public class Publisher {

    private Long id_publisher;

    private String name;

    private Set<Book> books;
}
