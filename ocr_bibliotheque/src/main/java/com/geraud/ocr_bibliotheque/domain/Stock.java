package com.geraud.ocr_bibliotheque.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Stock {

    @Id
    StockId pk;

    private String label;
    private String wings;
    private String pillar;
    private String shelf;
}
