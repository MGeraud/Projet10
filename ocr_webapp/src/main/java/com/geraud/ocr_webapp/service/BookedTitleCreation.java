package com.geraud.ocr_webapp.service;

import com.geraud.ocr_webapp.model.Book;
import com.geraud.ocr_webapp.model.BookedTitle;
import com.geraud.ocr_webapp.model.Booking;
import com.geraud.ocr_webapp.model.Loan;
import com.geraud.ocr_webapp.utils.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookedTitleCreation {

    @Autowired
    CallLoanApi callLoanApi;

    public List<BookedTitle> createBookedTitles(Login login){
        List<BookedTitle> bookedTitles = new ArrayList<>();

        Booking[] myBookings = callLoanApi.getBookingsByMember(login);

        //pour chacune de mes réservations, je vais attribuer la réservation, la date de retour probable et ma position dans la file d'attente
        for (Booking booking: myBookings
             ) {
            BookedTitle bookedTitle = new BookedTitle();
            bookedTitle.setBooking(booking);
            bookedTitle.setQueuePosition(findPositionInQueue(booking));
            bookedTitle.setGuessedBookBackDate(guessBookBackDate(booking.getTitle()));

            bookedTitles.add(bookedTitle);
        }

        return bookedTitles;
    }

    /**
     * Méthode utilitaire permetant de récupérer la position d'un membre dans la file d'attente des réservations d'un titre
     * @param booking la réservation du membre
     * @return la position du membre dans la file d'attente sous forme de int
     */
    int findPositionInQueue(Booking booking) {
        int position = 0;
        //récupération d'un array des réservations avec le même titre que celui de  mes réservations
        Booking[] titleBookings = callLoanApi.getBookingByTitle(booking.getTitle());
        //tri de cet array par date de réservation
        Arrays.stream(titleBookings).sorted(Comparator.comparing(Booking::getBookingDate)).collect(Collectors.toList());
        //recherche de la position du membre dans cette liste et ajout à l'attribut de BookedTitle
        for (Booking positionInBooking: titleBookings
        ) {
            if (positionInBooking.getMember().getEmail().equals(booking.getMember().getEmail())) {
                position = Arrays.asList(titleBookings).indexOf(positionInBooking) + 1;
            }
        }
        return position;
    }

    /**
     * Méthode utilitaire permetant de rechercher la date probable de retour la plus proche d'un titre
     * @param title le titre du livre
     * @return date de retour probable la plus proche
     */
    private LocalDate guessBookBackDate(String title){
        //récupération d'un array des emprunts avec le même titre
        Loan[] titleLoans = callLoanApi.getLoanByTitle(title);
        //tri de cet array par date d'emprunt
        Arrays.stream(titleLoans).sorted(Comparator.comparing(Loan::getStartingDate)).collect(Collectors.toList());
        // division de titleLoans en 2 list en fonction de si l'emprunt est prolongé
        List<Loan> extendedLoans = new ArrayList<>();
        List<Loan> nonExtendedLoans = new ArrayList<>();
        for (Loan loan:titleLoans
        ) {
            if (loan.getRefreshEndingCounter() > 0) {
                extendedLoans.add(loan);
            }else {
                nonExtendedLoans.add(loan);
            }
        }
        //comparaison des 2 dates de retour possible selon extension ou non
        if ( !extendedLoans.isEmpty() &&
                extendedLoans.get(0).getStartingDate().plusWeeks(8).isBefore(nonExtendedLoans.get(0).getStartingDate().plusWeeks(4))){
            return extendedLoans.get(0).getBookBackDate().plusWeeks(8);
        } else {
            return nonExtendedLoans.get(0).getStartingDate().plusWeeks(4);
        }
    }

    public BookedTitle createBookedTitle(Book book){
        BookedTitle bookedTitle = new BookedTitle();
        //récupération de la date de retour la plus proche
        bookedTitle.setGuessedBookBackDate(guessBookBackDate(book.getTitle()));
        //récupération d'un array des emprunts avec le même titre
        Loan[] titleLoans = callLoanApi.getLoanByTitle(book.getTitle());
        bookedTitle.setNumberOfBooking(titleLoans.length);

        return bookedTitle;
    }
}
