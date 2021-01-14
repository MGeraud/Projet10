package com.geraud.expiredloanbatch.jobs;

import com.geraud.expiredloanbatch.model.Loan;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

/**
 * Implémentation de l'ItemProcessor pour le job
 * Utilisation de la librairie spring SimpleMailMessage pour la création du contenu du mail de relance
 * avec les informations tirées de la liste des prêts, pour les livres empruntés depuis plus de 4 semaines sans période de prolongataion
 */
@Component
public class MailExpiredLoanWihtoutRefreshProcessor implements ItemProcessor<Loan, SimpleMailMessage> {
    @Override
    public SimpleMailMessage process(Loan loan) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(loan.getMember().getEmail());
        mailMessage.setFrom("noreply@ocr-bibliotheque.com");
        mailMessage.setSubject("Livre non rendu");
        mailMessage.setText("Bonjour,\n\nA ce jour, vous n'avez pas ramené le livre : " + loan.getTitle() + " ," +
                " emprunté depuis plus de 4 semaines. Vous pouvez prolonger une fois cette période d'emprunt en " +
                "vous connectant au site de la bibliothèque avec votre email et votre numéro de carte de membre, afin d'éviter les " +
                "pénalités de retard.\n\n" +
                "A bientôt dans nos bibliothèques,\n" +
                "L'équipe des bibliothèques d'OCR.");
        return mailMessage;
    }
}
