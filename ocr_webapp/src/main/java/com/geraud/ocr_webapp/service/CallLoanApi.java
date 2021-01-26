package com.geraud.ocr_webapp.service;

import com.geraud.ocr_webapp.model.Booking;
import com.geraud.ocr_webapp.model.Loan;
import com.geraud.ocr_webapp.utils.Login;

public interface CallLoanApi {
    Loan[] getLoanbyMember(Login login);
    Booking[] getBookingsByMember(Login login);
    Loan patchLoan(Long id);
}
