package com.geraud.ocr_webapp.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Booking {

    private Long id;

    private LocalDate bookingDate;
    private LocalDate mailSendDate;
    private String title;

    private Member member;
}
