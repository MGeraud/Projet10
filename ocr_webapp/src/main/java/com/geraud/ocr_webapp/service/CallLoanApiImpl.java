package com.geraud.ocr_webapp.service;

import com.geraud.ocr_webapp.exception.FunctionnalException;
import com.geraud.ocr_webapp.exception.NotAllowedBookingException;
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

import java.time.LocalDate;

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
    @Value("${base.url.member}")
    private String memberUrl;

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

    @Override
    public Booking[] getBookingByTitle(String title) {
        //création de l'url à appeler à partir du titre de l'ouvrage
        //pour appel des réservations de celui -ci
        String bookingsUrl = UriComponentsBuilder.fromHttpUrl(bookingUrl + "title")
                .queryParam("title" , title)
                .toUriString();
        // appel l'API gérant les emprunts puis récupération des livres réservés sous forme de tableau
        ResponseEntity<Booking[]> bookings = restTemplate.getForEntity(bookingsUrl, Booking[].class);
        Booking[] titleBookings = bookings.getBody();
        return titleBookings;
    }

    @Override
    public Loan[] getLoanByTitle(String title) {
        //création de l'url à appeler à partir du titre de l'ouvrage
        //pour appel des prêts en cours de ce titre
        String url = UriComponentsBuilder.fromHttpUrl(loanUrl + "title")
                .queryParam("title" , title)
                .toUriString();

        // appel l'API gérant les emprunts puis récupération des livres emprunté sous forme de tableau
        ResponseEntity<Loan[]> response = restTemplate.getForEntity(url, Loan[].class);
        Loan[] titleLoans = response.getBody();
        return titleLoans;
    }

    @Override
    public void createBooking(String title, Login login) throws NotAllowedBookingException, FunctionnalException {

        this.checkNotBookedOrLoaned(title, login);

        Booking bookingToBeCreated = new Booking();
        try{
            String bookingsUrl = UriComponentsBuilder.fromHttpUrl(memberUrl)
                    .queryParam("email" , login.getEmail())
                    .queryParam("cardnumber", login.getCardnumber())
                    .toUriString();
            // appel l'API pour voir si le membre identifié existe et récupérer son id
            ResponseEntity<Member> member = restTemplate.getForEntity(bookingsUrl, Member.class);
            bookingToBeCreated.setMember(member.getBody());
            bookingToBeCreated.setBookingDate(LocalDate.now());
            bookingToBeCreated.setTitle(title);
        }catch (Exception e){
            throw new FunctionnalException("Erreur lors de la recherche de membre avec ces identifiants : " + login.toString());
        }

        //création de l'url pour créer la réservation
        String url = UriComponentsBuilder.fromHttpUrl(bookingUrl)
                .toUriString();
        //envoi de la requête et récupération de l'emprunt modifié
        Booking createdBooking = restTemplate.postForObject(url, bookingToBeCreated, Booking.class);

    }

    /**
     * Utils pour vérifier qu'un titre ne soit pas déjà réservé ou en cours d'emprunt pour le membre identifié
     * @param title titre de l'ouvrage
     * @param login identifiants du membre
     * @throws NotAllowedBookingException envoi d'une exception custom si titre déjà réservé ou en cours d'emprunt par ce membre
     */
    private void checkNotBookedOrLoaned(String title, Login login) throws NotAllowedBookingException {
        Booking[] myBookings = this.getBookingsByMember(login);
        for (Booking booking: myBookings
        ) {
            if (booking.getTitle().equals(title)){
                throw new NotAllowedBookingException("Ce membre a déjà une réservation de ce titre en cours");
            }
        }

        Loan[] myLoans = this.getLoanbyMember(login);
        for (Loan loan: myLoans
        ) {
            if (loan.getMember().getEmail().equals(login.getEmail()) && loan.getBookBackDate() != null){
                throw new NotAllowedBookingException("Ce membre possede déjà ce titre");
            }
        }
    }
}
