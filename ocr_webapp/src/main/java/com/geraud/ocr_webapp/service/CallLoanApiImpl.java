package com.geraud.ocr_webapp.service;

import com.geraud.ocr_webapp.model.Booking;
import com.geraud.ocr_webapp.model.Loan;
import com.geraud.ocr_webapp.model.Member;
import com.geraud.ocr_webapp.utils.Login;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Voir la javadoc de l'interface
 */
@Slf4j
@Service
public class CallLoanApiImpl implements CallLoanApi{

    @Value("${base.url.loan}")
    private String loanUrl;
    @Value("${base.url.booking}")
    private String bookingUrl;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public Loan[] getLoanbyMember(Login login) {
        //création de l'url à appeler à partir des critères de recherche récupérés (email et carte membre)
        //pour appel des prêts de l'utilisateur
        String url = UriComponentsBuilder.fromHttpUrl(loanUrl)
                .queryParam("email" , login.getEmail())
                .queryParam("cardnumber", login.getCardnumber())
                .toUriString();

            // appel l'API gérant les emprunts puis récupération des livres emprunté sous forme de tableau
            ResponseEntity<Loan[]> response = restTemplate.getForEntity(url, Loan[].class);
            Loan[] myLoans = response.getBody();
            return  myLoans;
    }

    @Override
    public Booking[] getBookingsByMember(Login login) {
        //création de l'url à appeler à partir des critères de recherche récupérés (email et carte membre)
        //pour appel des réservations de l'utilisateur
        String bookingsUrl = UriComponentsBuilder.fromHttpUrl(bookingUrl + "member")
                .queryParam("email" , login.getEmail())
                .queryParam("cardnumber", login.getCardnumber())
                .toUriString();
        // appel l'API gérant les emprunts puis récupération des livres réservés sous forme de tableau
        ResponseEntity<Booking[]> bookings = restTemplate.getForEntity(bookingsUrl, Booking[].class);
        Booking[] myBookings = bookings.getBody();

        return myBookings;
    }

    @Override
    public Loan patchLoan(Long id) {
        //Création d'un loan temporaire qui sera envoyé à l'API gérant les emprunts en ne précisant que son id et l'incrémentation du compteur de prolongation
        Loan loan = new Loan();
        loan.setId(id);
        loan.setRefreshEndingCounter(1);

        //Utilisation de la factory client request d'Apache pour prise en compte du verbe http PATCH avec restTemplate
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        restTemplate.setRequestFactory(requestFactory);

        //création de l'url à appeler à partir de l'Id de l'emprunt à modifier
        String url = UriComponentsBuilder.fromHttpUrl(loanUrl + loan.getId())
                .toUriString();
        //envoi de la requête et récupération de l'emprunt modifié
        Loan patchedLoan = restTemplate.patchForObject(url, loan, Loan.class);
        return patchedLoan;
    }

    @Override
    public Member deleteBooking(Long id) {
        //création de l'url à appeler à partir de l'Id de la réservation à annuler
        String url = UriComponentsBuilder.fromHttpUrl(bookingUrl + id )
                .toUriString();
        //annulation de la réservation et récupération du membre en retour par méthode delete de l'api Loan
        ResponseEntity<Member> member = restTemplate.exchange(url , HttpMethod.DELETE , HttpEntity.EMPTY,Member.class);
        return member.getBody();
    }
}
