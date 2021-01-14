package com.geraud.ocr_webapp.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class Loan {

    private Long id;
    private int refreshEndingCounter;
    private LocalDate startingDate;
    private LocalDate bookBackDate;
    private String label;
    private String title;

    private Member member;
}
