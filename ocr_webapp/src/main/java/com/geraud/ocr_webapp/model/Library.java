package com.geraud.ocr_webapp.model;


import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
public class Library {

    private Long id_library;

    private String library_name;
    private String opening_hours;
    private String address;


    private Set<Stock> stocks = new HashSet<>();
}
