package com.geraud.ocr_loan_api.controllers;

import com.geraud.ocr_loan_api.domain.Booking;
import com.geraud.ocr_loan_api.exceptions.FunctionnalException;
import com.geraud.ocr_loan_api.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    /**
     * Création d'une réservation
     */
    @PostMapping("/booking")
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) throws FunctionnalException {
        return new ResponseEntity<Booking>(bookingService.createBooking(booking) , HttpStatus.OK);
    }

    @DeleteMapping("/booking/{id}")
    public ResponseEntity<Long> deleteBooking(@PathVariable Long id){
        bookingService.deleteBooking(id);
        return new ResponseEntity<Long>(id , HttpStatus.OK);
    }

    @GetMapping("/booking/{id}/emailing")
    public ResponseEntity<Booking> patchBooking(@PathVariable Long id){
        return new ResponseEntity<Booking>(bookingService.patchBooking(id) , HttpStatus.OK);
    }
}
