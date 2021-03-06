package com.geraud.ocr_loan_api.controllers;

import com.geraud.ocr_loan_api.domain.Booking;
import com.geraud.ocr_loan_api.domain.Member;
import com.geraud.ocr_loan_api.exceptions.FunctionnalException;
import com.geraud.ocr_loan_api.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
    public List<Booking> bookingsWithTitle(@RequestParam("title") String title) throws UnsupportedEncodingException {
        String titleToUtf = URLDecoder.decode(title , "utf-8");
        return bookingService.listBookingWithTitle(titleToUtf);
    }

    /**
     * Création d'une réservation
     */
    @PostMapping("/booking")
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) throws FunctionnalException {
        return new ResponseEntity<Booking>(bookingService.createBooking(booking) , HttpStatus.OK);
    }

    /**
     * effacement d'une réservation
     * @param id de la réservation à annuler
     * @return
     */
    @DeleteMapping("/booking/{id}")
    public ResponseEntity<Member> deleteBooking(@PathVariable Long id){
        return new ResponseEntity<Member>(bookingService.deleteBooking(id) , HttpStatus.OK);
    }

}
