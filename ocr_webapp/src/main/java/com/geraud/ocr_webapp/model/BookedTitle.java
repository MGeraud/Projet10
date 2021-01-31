package com.geraud.ocr_webapp.model;


import com.geraud.ocr_webapp.utils.Login;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BookedTitle {

    private Booking booking;
    private LocalDate guessedBookBackDate;
    private int queuePosition;
    private int numberOfBooking;
}
