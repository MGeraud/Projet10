package com.geraud.expiredloanbatch.jobs;

import com.geraud.expiredloanbatch.model.Booking;
import com.geraud.expiredloanbatch.services.CallLoanApi;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class ExpiredBookingPriorityProcessor implements ItemProcessor<Booking, SimpleMailMessage> {

    @Autowired
    CallLoanApi callLoanApi;

    @Override
    public SimpleMailMessage process(Booking booking) throws Exception {

        //récup les réservations avec le titre de l'emprunt rendu
        Booking[] bookings = callLoanApi.getBookingByTitleWithMailSendDateIsNull(booking.getTitle());
        if (bookings.length >=1) {
            //création du mail à envoyer au 1er de la liste
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(bookings[0].getMember().getEmail());
            mailMessage.setFrom("noreply@ocr-bibliotheque.com");
            mailMessage.setSubject("Ouvrage réservé mis à disposition");
            mailMessage.setText("Bonjour,\n\nVous avez réservé l'ouvrage : " + booking.getTitle() + " ." +
                    "Ce livre a été retourné à labibliothèque.\n" +
                    "Vous disposez de 48h pour venir le récupérer. Passez ce délai, votre réservation n'aura plus court. " +
                    "A bientôt dans nos bibliothèques,\n" +
                    "L'équipe des bibliothèques d'OCR.");

            //patch de la réservation à laquelle on envoi le mail
            callLoanApi.patchBooking(bookings[0]);
            //effacer la réservation expirée
            callLoanApi.deleteBooking(booking);
            return mailMessage;
        } else {
            return null;
        }
    }
}
