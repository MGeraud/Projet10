package com.geraud.expiredloanbatch.jobs;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Implementation de l'ItemWriter avec utilisation de JavaMailSender pour envoyer les mails créés via MailExpiredLoanWithoutRefreshProcessor
 */
@Component
public class MailWriter implements ItemWriter<SimpleMailMessage> {
    @Autowired
    JavaMailSender javaMailSender;

    @Override
    public void write(List<? extends SimpleMailMessage> list) {
        for (SimpleMailMessage message : list) {
            javaMailSender.send(message);
        }
    }
}
