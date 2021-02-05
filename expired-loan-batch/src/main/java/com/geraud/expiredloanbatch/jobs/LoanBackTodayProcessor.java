package com.geraud.expiredloanbatch.jobs;

import com.geraud.expiredloanbatch.model.Loan;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class LoanBackTodayProcessor implements ItemProcessor<Loan, SimpleMailMessage> {

    @Override
    public SimpleMailMessage process(Loan loan) throws Exception {
        return null;
    }
}
