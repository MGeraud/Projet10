package com.geraud.ocr_loan_api.services;

import com.geraud.ocr_loan_api.domain.Booking;
import com.geraud.ocr_loan_api.domain.Loan;

import java.time.LocalDate;
import java.util.List;

public interface BatchService {
    List<Loan> findTitleAndEmail(LocalDate date , int refresh);

    List<Loan> getLoansBackToday();

    List<Booking> getBookingByTitleAndMailSendDateIsNull(String title);

    List<Booking> getBookingByMailSendDate(LocalDate date);
}
