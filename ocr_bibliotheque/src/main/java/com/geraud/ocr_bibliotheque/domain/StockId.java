package com.geraud.ocr_bibliotheque.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Objet représentant la clé composée pour l'objet Stock
 */
@Embeddable
@Getter
@Setter
public class StockId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "idbook")
    @JsonIgnore
    private Book book;

    @ManyToOne
    @JoinColumn(name = "id_library")
    private Library library;

}
