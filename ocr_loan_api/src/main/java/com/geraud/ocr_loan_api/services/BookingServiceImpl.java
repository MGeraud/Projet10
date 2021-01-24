package com.geraud.ocr_loan_api.services;

import com.geraud.ocr_loan_api.dao.BookingDao;
import com.geraud.ocr_loan_api.domain.Booking;
import com.geraud.ocr_loan_api.domain.Member;
import com.geraud.ocr_loan_api.exceptions.FunctionnalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService{

    private final BookingDao bookingDao;

    @Autowired
    MemberService memberService;

    public BookingServiceImpl(BookingDao bookingDao) {
        this.bookingDao = bookingDao;
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
