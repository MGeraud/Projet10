package com.geraud.ocr_webapp.model;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class Member {

    private Long id;
    private String email;
    private String cardnumber;

    private Set<Loan> loans =new HashSet<>();

}
