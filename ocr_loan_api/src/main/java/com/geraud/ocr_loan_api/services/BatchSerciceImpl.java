package com.geraud.ocr_loan_api.services;

import com.geraud.ocr_loan_api.dao.LoanDao;
import com.geraud.ocr_loan_api.domain.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BatchSerciceImpl implements BatchService{
    @Autowired
    LoanDao loanDao;
    @Override
    public List<Loan> findTitleAndEmail(LocalDate date, int refresh) {
        return loanDao.findAllByBookBackDateIsNullAndStartingDateLessThanAndRefreshEndingCounterEquals(date, refresh);
    }
}
