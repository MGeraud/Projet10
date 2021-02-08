package com.geraud.expiredloanbatch.jobs;

import com.geraud.expiredloanbatch.model.Booking;
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

@Component
public class BookingJob {
    private JobBuilderFactory jobBuilderFactory;
    private StepBuilderFactory stepBuilderFactory;
    private LoanBackTodayProcessor loanBackTodayProcessor;
    private ExpiredBookingPriorityProcessor expiredBookingPriorityProcessor;
    private MailWriter bookingMailWriter;

    @Autowired
    public BookingJob(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, LoanBackTodayProcessor loanBackTodayProcessor, ExpiredBookingPriorityProcessor expiredBookingPriorityProcessor, MailWriter bookingMailWriter) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.loanBackTodayProcessor = loanBackTodayProcessor;
        this.expiredBookingPriorityProcessor = expiredBookingPriorityProcessor;
        this.bookingMailWriter = bookingMailWriter;
    }

    public Job manageBookingsQueue(List<Loan> loans , List<Booking> bookings){
        Step step1 = stepBuilderFactory.get("step-loan-back-today")
                .<Loan, SimpleMailMessage>chunk(100)
                .reader(itemReaderLoan(loans))
                .processor(loanBackTodayProcessor)
                .writer(bookingMailWriter)
                .build();
        Step step2 = stepBuilderFactory.get("step-2days-booking")
                .<Booking , SimpleMailMessage>chunk(100)
                .reader(itemReaderBooking(bookings))
                .processor(expiredBookingPriorityProcessor)
                .writer(bookingMailWriter)
                .build();
        return jobBuilderFactory.get("manage-booking-queue")
                .flow(step1)
                .next(step2)
                .end()
                .build();
    }


    /**
     * Constructeur d'ItemReader de liste de Loan
     * @param loans liste de Loans
     * @return ItemReader de la liste de Loan passée en paramètre
     */
    public ItemReader<Loan> itemReaderLoan(List<Loan> loans) {
        ListItemReader<Loan> loanListItemReader = new ListItemReader<>(loans);
        return loanListItemReader;
    }

    /**
     * Constructeur d'ItemReader de liste de Booking
     * @param bookings liste de réservations
     * @return ItemReader de la liste de Booking passé en paramètre
     */
    public ItemReader<Booking> itemReaderBooking(List<Booking> bookings) {
        ListItemReader<Booking> bookingListItemReader = new ListItemReader<>(bookings);
        return bookingListItemReader;
    }
}
