package com.geraud.ocr_webapp.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Objet représentant la clé composée pour l'objet Stock
 */

@Getter
@Setter
public class StockId implements Serializable {

    private Book book;


    private Library library;

}
