package com.geraud.ocr_loan_api.services;

import com.geraud.ocr_loan_api.domain.Loan;

import java.util.List;

public interface LoanService {

    Loan patchLoan(Long id , Loan loan);
    Loan createLoan(String cardnumber, Loan loan);
    List<Loan> listLoans(String email, String cardnumber);
    boolean labelAvailable(String label);

    /**
     * Liste des emprunts dont le titre est passé enparametre
     * @param title titre du livre emprunté
     * @return liste des emprunts avec ce titre
     */
    List<Loan> listLoansByTitle(String title);
}
