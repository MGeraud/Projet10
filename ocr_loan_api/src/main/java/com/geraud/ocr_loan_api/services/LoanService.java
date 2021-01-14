package com.geraud.ocr_loan_api.services;

import com.geraud.ocr_loan_api.domain.Loan;

import java.util.List;

public interface LoanService {

    Loan patchLoan(Long id , Loan loan);
    Loan createLoan(String cardnumber, Loan loan);
    List<Loan> listLoans(String email, String cardnumber);
    boolean labelAvailable(String label);
}
