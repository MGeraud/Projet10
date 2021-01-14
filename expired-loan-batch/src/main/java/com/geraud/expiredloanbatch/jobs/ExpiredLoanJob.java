package com.geraud.expiredloanbatch.jobs;

import com.geraud.expiredloanbatch.model.Loan;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.support.ListItemReader;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Création du Job avec utilisation des implémentations de processor et writer personnalisées (MailExpiredLoanWithoutRefreshProcessor et MailWriter)
 * Utilisation de l'implémentation ListItemReader comme ItemReader
 */
@Component
public class ExpiredLoanJob {
    private JobBuilderFactory jobBuilderFactory;
    private StepBuilderFactory stepBuilderFactory;
    private MailExpiredLoanWihtoutRefreshProcessor processor;
    private MailExpiredLoanAlreadyRefreshedProcessor expiredLoanAlreadyRefreshedProcessor;
    private MailWriter mailWriter;

    @Autowired
    public ExpiredLoanJob(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, MailExpiredLoanWihtoutRefreshProcessor processor, MailExpiredLoanAlreadyRefreshedProcessor expiredLoanAlreadyRefreshedProcessor, MailWriter mailWriter) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.processor = processor;
        this.expiredLoanAlreadyRefreshedProcessor = expiredLoanAlreadyRefreshedProcessor;
        this.mailWriter = mailWriter;
    }

    public Job sendMailToExpiredLoans(List<Loan> loans , List<Loan> alreadyRefreshedLoans) {
        Step step = stepBuilderFactory.get("step-send-mail")
                .<Loan, SimpleMailMessage>chunk(100)
                .reader(itemReader(loans))
                .processor(processor)
                .writer(mailWriter)
                .build();
        Step step1 = stepBuilderFactory.get("step-send-mail-expired-period-already-refreshed")
                .<Loan, SimpleMailMessage>chunk(100)
                .reader(itemReader(alreadyRefreshedLoans))
                .processor(expiredLoanAlreadyRefreshedProcessor)
                .writer(mailWriter)
                .build();

        return jobBuilderFactory.get("alert-loan-expired")
                .flow(step)
                .next(step1)
                .end()
                .build();
    }

    public ItemReader<Loan> itemReader(List<Loan> loans) {
        ListItemReader<Loan> loanListItemReader = new ListItemReader<>(loans);
        return loanListItemReader;
    }
}
