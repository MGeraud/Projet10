package com.geraud.ocr_loan_api.controllers;

import com.geraud.ocr_loan_api.domain.Booking;
import com.geraud.ocr_loan_api.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookingController {

    @Autowired
    BookingService bookingService;

    /**
     * Envoi de la liste des réservations d'un utilisateur
     */
    @GetMapping("/booking/member")
    public List<Booking> bookingsOfMember(@RequestParam("email") String email,
                                          @RequestParam("cardnumber") String cardnumber){
        return bookingService.listBookingOfMember(email, cardnumber);
    }

    /**
     * Envoi de la liste des réservations d'un titre
     */
    @GetMapping("/booking/title")
    public List<Booking> bookingsWithTitle(@RequestParam("title") String title){
        return bookingService.listBookingWithTitle(title);
    }
}
