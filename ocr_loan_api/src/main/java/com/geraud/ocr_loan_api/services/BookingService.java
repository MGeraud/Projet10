package com.geraud.ocr_loan_api.services;

import com.geraud.ocr_loan_api.domain.Booking;
import com.geraud.ocr_loan_api.exceptions.FunctionnalException;

import java.util.List;

public interface BookingService {

    List<Booking> listBookingOfMember (String email, String cardmember);
    List<Booking> listBookingWithTitle (String title);

    Booking createBooking(Booking booking) throws FunctionnalException;

    void deleteBooking(Long id);
}
