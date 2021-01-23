package com.geraud.ocr_loan_api.dao;

import com.geraud.ocr_loan_api.domain.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingDao extends JpaRepository<Booking , Long> {

    List<Booking> findByMember_EmailAndMember_Cardnumber(String email , String cardmember);
    List<Booking> findByTitleOrderByBookingDate(String title);
}
