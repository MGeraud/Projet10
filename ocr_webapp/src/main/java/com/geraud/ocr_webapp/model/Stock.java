package com.geraud.ocr_webapp.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Stock {

    StockId pk;

    private String label;
    private String wings;
    private String pillar;
    private String shelf;
}
