package com.geraud.expiredloanbatch.jobs;

import com.geraud.expiredloanbatch.model.Booking;
import com.geraud.expiredloanbatch.model.Loan;
import com.geraud.expiredloanbatch.services.CallLoanApi;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;


@Component
public class LoanBackTodayProcessor implements ItemProcessor<Loan, SimpleMailMessage> {

    @Autowired
    CallLoanApi callLoanApi;

    @Override
    public SimpleMailMessage process(Loan loan) throws Exception {
        //récup les réservations avec le titre de l'emprunt rendu
        Booking[] bookings = callLoanApi.getBookingByTitleWithMailSendDateIsNull(loan.getTitle());
        if (bookings.length >= 1) {
            //création du mail à envoyer au 1er de la liste
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(bookings[0].getMember().getEmail());
            mailMessage.setFrom("noreply@ocr-bibliotheque.com");
            mailMessage.setSubject("Ouvrage réservé mis à disposition");
            mailMessage.setText("Bonjour,\n\nVous avez réservé l'ouvrage : " + loan.getTitle() + " ." +
                    "Ce livre a été retourné à labibliothèque.\n" +
                    "Vous disposez de 48h pour venir le récupérer. Passez ce délai, votre réservation n'aura plus court. " +
                    "A bientôt dans nos bibliothèques,\n" +
                    "L'équipe des bibliothèques d'OCR.");

            //patch de la réservation à laquelle on envoi le mail
            callLoanApi.patchBooking(bookings[0]);
            return mailMessage;
        }else {
            return null;
        }
    }
}
