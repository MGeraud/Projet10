package com.geraud.expiredloanbatch.jobs;

import com.geraud.expiredloanbatch.model.Loan;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

/**
 * Implémentation de l'ItemProcessor pour le job
 * Utilisation de la librairie spring SimpleMailMessage pour la création du contenu du mail de relance
 * avec les informations tirées de la liste des prêts, pour les livres empruntés depuis plus de 8 semaines, donc période d'emprunt déjà prolongée
 */
@Component
public class MailExpiredLoanAlreadyRefreshedProcessor implements ItemProcessor<Loan, SimpleMailMessage> {
    @Override
    public SimpleMailMessage process(Loan loan) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(loan.getMember().getEmail());
        mailMessage.setFrom("noreply@ocr-bibliotheque.com");
        mailMessage.setSubject("Livre non rendu");
        mailMessage.setText("Bonjour,\n\nA ce jour, vous n'avez pas ramené le livre : " + loan.getTitle() + " ," +
                " emprunté depuis plus de 8 semaines. Vous ne pouvez pas prolonger cette période d'emprunt.\n" +
                "Merci de nous contacter ou de ramener rapidement l'ouvrage afin d'éviter les " +
                "pénalités de retard.\n\n" +
                "A bientôt dans nos bibliothèques,\n" +
                "L'équipe des bibliothèques d'OCR.");
        return mailMessage;
    }
}
