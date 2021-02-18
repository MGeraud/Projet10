package com.geraud.ocr_loan_api.services;

import com.geraud.ocr_loan_api.dao.BookingDao;
import com.geraud.ocr_loan_api.domain.Booking;
import com.geraud.ocr_loan_api.domain.Member;
import com.geraud.ocr_loan_api.exceptions.FunctionnalException;
import com.geraud.ocr_loan_api.exceptions.LoanNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService{

    private final BookingDao bookingDao;


    private final MemberService memberService;

    public BookingServiceImpl(BookingDao bookingDao, MemberService memberService) {
        this.bookingDao = bookingDao;
        this.memberService = memberService;
    }

    /**
     * Utilisation de BookingDao pour rechercher la liste des réservations d'un utilisateur
     * @param email mail de l'utilisateur
     * @param cardmember référence de la carte du membre de la bibliothèque
     * @return liste des réservation de l'utilisateur
     */
    @Override
    public List<Booking> listBookingOfMember(String email, String cardmember) {
        return bookingDao.findByMember_EmailAndMember_Cardnumber(email,cardmember);
    }

    /**
     * Utilisation de BookingDao pour rechercher la liste des réservations d'un titre de livre
     * @param title titre du livre
     * @return liste des réservations d'un titre de livre
     */
    @Override
    public List<Booking> listBookingWithTitle(String title) {
        return bookingDao.findByTitleOrderByBookingDate(title);
    }

    /**
     * Création de la réservation d'un titre par un utilisateur
     * @param booking réservation à créer
     * @return
     */
    @Override
    public Booking createBooking(Booking booking) throws FunctionnalException {
        checkIfNotAllreadyBookedByMember(booking);
        Member member = memberService.findByEmailAndCardNumber(booking.getMember().getEmail(),booking.getMember().getCardnumber());
        booking.setMember(member);
        booking.setBookingDate(LocalDate.now());
        bookingDao.save(booking);
        return booking;
    }

    /**
     * Effacer une réservation
     * @param id de la réserveation à effacer
     */
    @Override
    public Member deleteBooking(Long id) {
        Member member = bookingDao.findById(id).get().getMember();
        bookingDao.deleteById(id);
        return member;
    }

    /**
     * annote la date d'envoi de mail
     * @param id de la réservation pour laquelle le mail est envoyé
     * @return la réservation modifiée
     */
    @Override
    public Booking patchBooking(Long id, Booking booking) {
        Optional<Booking> optionalToUpdate = bookingDao.findById(id);
        if (optionalToUpdate.isEmpty()){
            throw new LoanNotFoundException("Réservation non trouvée avec l'id : " + id);
        }
        Booking bookingToUpdate = optionalToUpdate.get();
        bookingToUpdate.setMailSendDate(LocalDate.now());
        bookingDao.save(bookingToUpdate);

        return bookingToUpdate;
    }

    /**
     * vérifier si l'utilisateur  n'a pas déjà réservé ce titre
     * @param booking réservation en attente d'enregistrement
     * @throws FunctionnalException si livre déjà réservé par cet utilisateur
     */
    private void checkIfNotAllreadyBookedByMember(Booking booking) throws FunctionnalException{

        List<Booking> thisTitleBookings = bookingDao.findByTitleOrderByBookingDate(booking.getTitle());
        if (!thisTitleBookings.isEmpty()){
            for (Booking thisTitleBooking : thisTitleBookings) {
                if (thisTitleBooking.getMember().getCardnumber().equals(booking.getMember().getCardnumber())){
                    throw new FunctionnalException("Titre déjà réservé par l'utilisateur");
                }
            }
        }
    }

}
