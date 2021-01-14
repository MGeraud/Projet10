package com.geraud.ocr_loan_api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int refreshEndingCounter;
    private LocalDate startingDate;
    private LocalDate bookBackDate;
    private String label;
    private String title;

    @ManyToOne
    private Member member;
}
