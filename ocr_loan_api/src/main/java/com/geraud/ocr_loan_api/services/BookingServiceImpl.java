package com.geraud.ocr_loan_api.services;

import com.geraud.ocr_loan_api.dao.BookingDao;
import com.geraud.ocr_loan_api.domain.Booking;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService{

    private final BookingDao bookingDao;

    public BookingServiceImpl(BookingDao bookingDao) {
        this.bookingDao = bookingDao;
    }

    @Override
    public List<Booking> listBookingOfMember(String email, String cardmember) {
        return bookingDao.findByMember_EmailAndMember_Cardnumber(email,cardmember);
    }

    @Override
    public List<Booking> listBookingWithTitle(String title) {
        return bookingDao.findByTitleOrderByBookingDate(title);
    }
}
