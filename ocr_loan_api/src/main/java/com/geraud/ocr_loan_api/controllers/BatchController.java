package com.geraud.ocr_loan_api.controllers;

import com.geraud.ocr_loan_api.domain.Booking;
import com.geraud.ocr_loan_api.domain.Loan;
import com.geraud.ocr_loan_api.services.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class BatchController {
    @Autowired
    BatchService batchService;

    /* Envoi de la liste des prêts échus en fonction d'une date et d'un nombre de prolongation */
    @GetMapping("/batch")
    public ResponseEntity<List<Loan>> batchDtoList(@RequestParam("date") String date,
                                                  @RequestParam("refresh") int refresh){
        return new ResponseEntity<>(batchService.findTitleAndEmail(LocalDate.parse(date), refresh), HttpStatus.OK);
    }

    /**
     * obtention de la liste des livres ramenés ce jour
     * @return la liste des livres dont la date de retour est aujourd'hui
     */
    @GetMapping("/batch/loans/backtoday")
    public ResponseEntity<List<Loan>> batchLoansBackToday(){
        return new ResponseEntity<>(batchService.getLoansBackToday() , HttpStatus.OK);
    }

    /**
     * obtention de la liste des réservations d'un titre pour lesquels aucun mail n'a été envoyé
     * @param title titre du livre
     * @return liste de réservations
     */
    @GetMapping("/batch/bookings/title")
    public ResponseEntity<List<Booking>> bookingsByTitleAndMailSendDateNull(@RequestParam("title") String title){
        return new ResponseEntity<>(batchService.getBookingByTitleAndMailSendDateIsNull(title) , HttpStatus.OK);
    }

    /**
     * obtention de la liste des réservations dont le mail de notification de disponibilité a été envoyé à la date passée en paramètre
     * @param date d'envoi de la notification de disponibilité
     * @return liste des réservations
     */
    @GetMapping("/batch/booking/maildate")
    public ResponseEntity<List<Booking>> bookingsByMailSendDate(@RequestParam("date") LocalDate date)
    {
        return new ResponseEntity<>(batchService.getBookingByMailSendDate(date) , HttpStatus.OK);
    }

}
