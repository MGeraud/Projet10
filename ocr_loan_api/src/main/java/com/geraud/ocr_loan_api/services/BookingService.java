package com.geraud.ocr_loan_api.services;

import com.geraud.ocr_loan_api.domain.Booking;

import java.util.List;

public interface BookingService {

    List<Booking> listBookingOfMember (String email, String cardmember);
    List<Booking> listBookingWithTitle (String title);
}
