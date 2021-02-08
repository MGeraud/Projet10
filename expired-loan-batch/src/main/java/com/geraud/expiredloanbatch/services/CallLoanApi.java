package com.geraud.expiredloanbatch.services;

import com.geraud.expiredloanbatch.model.Booking;
import com.geraud.expiredloanbatch.model.Loan;

import java.time.LocalDate;
import java.util.List;

public interface CallLoanApi {
    Booking[] getBookingByTitleWithMailSendDateIsNull(String title);

    void patchBooking(Booking bookingToPatch);

    void deleteBooking(Booking booking);

    List<Loan> getLoansBackToday();

    List<Booking> getBookingsByMailSendDate(LocalDate minusDays);
}
