package com.geraud.expiredloanbatch.services;

import com.geraud.expiredloanbatch.model.Booking;
import com.geraud.expiredloanbatch.model.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
public class CallLoanApiImpl implements CallLoanApi{
    @Autowired
    RestTemplate restTemplate;

    @Value("${url.base.loan}")
    private String loanApiUrl;

    @Override
    public Booking[] getBookingByTitleWithMailSendDateIsNull(String title) {
        //création de l'url à appeler à partir du titre de l'ouvrage
        //pour appel des réservations en cours de ce titre
        String url = UriComponentsBuilder.fromHttpUrl(loanApiUrl + "bookings/title")
                .queryParam("title" , title)
                .toUriString();
        //appel de l'API gérant les emprunts et réservations
        ResponseEntity<Booking[]> bookingResponseEntity = restTemplate.getForEntity(url, Booking[].class);

        return bookingResponseEntity.getBody();
    }

    @Override
    public void patchBooking(Booking bookingToPatch) {

        //Utilisation de la factory client request d'Apache pour prise en compte du verbe http PATCH avec restTemplate
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        restTemplate.setRequestFactory(requestFactory);

        bookingToPatch.setMailSendDate(LocalDate.now());

        String url = UriComponentsBuilder.fromHttpUrl(loanApiUrl + "booking/" + bookingToPatch.getId())
                .toUriString();
        restTemplate.patchForObject(url, bookingToPatch, Booking.class);
    }

    @Override
    public void deleteBooking(Booking booking) {

        String url = UriComponentsBuilder.fromHttpUrl(loanApiUrl + "booking/" + booking.getId())
                .toUriString();
        restTemplate.delete(url);
    }

    @Override
    public List<Loan> getLoansBackToday() {
        String url = UriComponentsBuilder.fromHttpUrl(loanApiUrl + "loans/backtoday")
                .toUriString();
        //Récupération  du tableau de Loan puis transformation en List
        ResponseEntity<Loan[]> loansBackToday = restTemplate.getForEntity(url , Loan[].class);
        List<Loan> loans = Arrays.asList(loansBackToday.getBody().clone());
        return loans;
    }

    @Override
    public List<Booking> getBookingsByMailSendDate(LocalDate minusDays) {
        String url = UriComponentsBuilder.fromHttpUrl(loanApiUrl + "booking/maildate")
                .queryParam("date" , minusDays)
                .toUriString();
        //Récupération  du tableau de Booking puis transformation en List
        ResponseEntity<Booking[]> bookingsByMailSendDate = restTemplate.getForEntity(url , Booking[].class);
        List<Booking> bookings = Arrays.asList(bookingsByMailSendDate.getBody().clone());
        return bookings;
    }
}

