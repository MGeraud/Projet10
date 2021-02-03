package com.geraud.ocr_webapp.service;

import com.geraud.ocr_webapp.exception.FunctionnalException;
import com.geraud.ocr_webapp.exception.NotAllowedBookingException;
import com.geraud.ocr_webapp.model.Booking;
import com.geraud.ocr_webapp.model.Loan;
import com.geraud.ocr_webapp.model.Member;
import com.geraud.ocr_webapp.utils.Login;

public interface CallLoanApi {

    /**
     * récupération des emprunts d'un membre
     * @param login identifiants du membres nécessaires à la récupération de ses emprunts
     * @return array des emprunts appartenant au membre identifié
     */
    Loan[] getLoanbyMember(Login login);

    /**
     * récupération des réservations d'un membre
     * @param login identifiants du membres nécessaires à la récupération de ses réservations
     * @return array des réservations faites par le membre identifié
     */
    Booking[] getBookingsByMember(Login login);

    /**
     * modification de l'emprunt. Incrémente le compteur de prolongation d'un emprunt
     * @param id de l'emprunt dont il faut incrémenter le compteur de prolongation
     * @return emprunt modifié
     */
    Loan patchLoan(Long id);

    /**
     * Méthode permettant d'annuler une réservation
     * @param id de la réservation à annuler
     * @return l'identité du membre ayant annulé sa réservation
     */
    Member deleteBooking(Long id);

    /**
     * Méthode de récupération des réservation d'un titre
     * @param title titre de l'ouvrage
     * @return array des réservations correspondant au titre ci dessus
     */
    Booking[] getBookingByTitle(String title);

    /**
     * Methode de récupération des emprunt d'un titre
     * @param title titre de l'ouvrage
     * @return array des emprunts du titre passé en paramètre
     */
    Loan[] getLoanByTitle(String title);

    /**
     * Methode de création d'une réservation
     * @param title titre de l'ouvrage à réserver
     * @param login identifiants du membre souhaitant réserver l'ouvrage
     */
    void createBooking(String title, Login login) throws NotAllowedBookingException, FunctionnalException;
}
