package com.geraud.ocr_loan_api.services;

import com.geraud.ocr_loan_api.dao.BookingDao;
import com.geraud.ocr_loan_api.dao.LoanDao;
import com.geraud.ocr_loan_api.domain.Booking;
import com.geraud.ocr_loan_api.domain.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BatchSerciceImpl implements BatchService{
    @Autowired
    LoanDao loanDao;
    @Autowired
    BookingDao bookingDao;

    @Override
    public List<Loan> findTitleAndEmail(LocalDate date, int refresh) {
        return loanDao.findAllByBookBackDateIsNullAndStartingDateLessThanAndRefreshEndingCounterEquals(date, refresh);
    }

    /**
     *
     * @return liste des emprunts retournés ce jour
     */
    @Override
    public List<Loan> getLoansBackToday() {
        return loanDao.findByBookBackDate(LocalDate.now());
    }


    /**
     * Recherche des réservations dont le titre du livre est passé en paramètre et ne comportant pas de date d'envoi de mail
     * classés par date de réservation
     * @param title titre du livre
     * @return liste des réservations
     */
    @Override
    public List<Booking> getBookingByTitleAndMailSendDateIsNull(String title) {
        return bookingDao.findByTitleAndMailSendDateIsNullOrderByBookingDate(title);
    }

    /**
     * Recherche des réservations dont la date d'envoi de mail de notification de disponibilité est passée en paramètre
     * @param date d'envoi du mail denotification de disponibilité
     * @return liste de réservations
     */
    @Override
    public List<Booking> getBookingByMailSendDate(LocalDate date) {
        return bookingDao.findByMailSendDate(date);
    }
}
