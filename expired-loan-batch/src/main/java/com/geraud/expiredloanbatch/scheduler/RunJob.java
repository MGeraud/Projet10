package com.geraud.expiredloanbatch.scheduler;

import com.geraud.expiredloanbatch.jobs.BookingJob;
import com.geraud.expiredloanbatch.jobs.ExpiredLoanJob;

import com.geraud.expiredloanbatch.model.Booking;
import com.geraud.expiredloanbatch.model.Loan;

import com.geraud.expiredloanbatch.services.CallLoanApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;

import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * Classe lancant le batch à période régulière.
 * Utilisation en paramètre de job de jobID basée sur l'heure du system pour pouvoir le relancer régulièrement
 */
@Component
@Slf4j
public class RunJob {

    @Value("${url.base.loan}")
    private String loanUrl;
    @Autowired
    JobLauncher jobLauncher;
    @Autowired
    ExpiredLoanJob expiredLoanJob;
    @Autowired
    BookingJob bookingJob;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    CallLoanApi callLoanApi;

    //Pour tester en  envoyant par minute inverser décommenter la ligne suivante et commenter celle qui déclenche le batch tous les jours à 1h du matin
    //@Scheduled(cron = "1 * * * * *")
    @Scheduled(cron = "* * 1 * * *")
    public void listExpiredLoans() throws Exception {
        //création de l'url pour appeller l'API rest pour la liste des livres non retournés supérieure à 4 semaines sans renouvellement de période
        String url = UriComponentsBuilder.fromHttpUrl(loanUrl)
                .queryParam("refresh" , 0)
                .queryParam("date" , LocalDate.now().minusWeeks(4) )
                .toUriString();
        //Récupération  du tableau de Loan puis transformation en List
        ResponseEntity<Loan[]> responseBeforeRefresh = restTemplate.getForEntity(url , Loan[].class);
        List<Loan> loans = Arrays.asList(responseBeforeRefresh.getBody().clone());

        //création de l'url pour appeller l'API rest pour la liste des livres non retournés supérieure à 8 semaines periode déja renouvellée
        String urlresfred = UriComponentsBuilder.fromHttpUrl(loanUrl )
                .queryParam("refresh" , 1)
                .queryParam("date" , LocalDate.now().minusWeeks(8) )
                .toUriString();
        //Récupération  du tableau de Loan puis transformation en List
        ResponseEntity<Loan[]> responseAfterRefresh = restTemplate.getForEntity(urlresfred , Loan[].class);
        List<Loan> loansAfterRefreshPeriod = Arrays.asList(responseAfterRefresh.getBody().clone());

        log.info("Chargement de la liste des prêt depuis la base de donnée effectué");
        JobExecution execution = jobLauncher.run(expiredLoanJob.sendMailToExpiredLoans(loans, loansAfterRefreshPeriod), new JobParametersBuilder().addString("jobID", String.valueOf(System.currentTimeMillis())).toJobParameters());
    }

    //Pour tester déclenchement à changer en fonction de l'heure actuelle
    //@Scheduled(cron = "10 19 * * * *")
    @Scheduled(cron = "* * 20 * * *")
    public void dailyBookingsManagement() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        List<Loan> loans = callLoanApi.getLoansBackToday();
        List<Booking> bookings = callLoanApi.getBookingsByMailSendDate(LocalDate.now().minusDays(2));
        JobExecution execution = jobLauncher.run(bookingJob.manageBookingsQueue(loans , bookings) , new JobParametersBuilder().addString("jobID", String.valueOf(System.currentTimeMillis())).toJobParameters() );
    }
}
