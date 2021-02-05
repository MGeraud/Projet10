package com.geraud.expiredloanbatch.jobs;

import com.geraud.expiredloanbatch.model.Booking;
import com.geraud.expiredloanbatch.model.Loan;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class ExpiredBookingPriorityProcessor implements ItemProcessor<Booking, SimpleMailMessage> {
    @Override
    public SimpleMailMessage process(Booking booking) throws Exception {
        return null;
    }
}
