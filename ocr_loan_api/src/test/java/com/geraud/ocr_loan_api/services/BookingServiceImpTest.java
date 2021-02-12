package com.geraud.ocr_loan_api.services;

import com.geraud.ocr_loan_api.dao.BookingDao;
import com.geraud.ocr_loan_api.domain.Booking;
import com.geraud.ocr_loan_api.domain.Member;
import com.geraud.ocr_loan_api.exceptions.FunctionnalException;
import com.geraud.ocr_loan_api.exceptions.LoanNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BookingServiceImpTest {

    private Member member;
    private Booking booking;
    private BookingDao bookingDao;
    private MemberService memberService;
    private BookingServiceImpl bookingService;

    @BeforeEach
    public void setUp(){
        member = new Member();
        member.setId(1L);
        member.setCardnumber("1234");
        member.setEmail("email");
        booking= new Booking();
        booking.setId(1L);
        booking.setMember(member);
        booking.setTitle("title");
        booking.setBookingDate(LocalDate.of(2021,01,01));
        member.setBookingSet(Set.of(booking));
        bookingDao= createBookingDao();
        memberService = createMemberService();
        bookingService = new BookingServiceImpl(bookingDao, memberService);

    }

    @Test
    void createBooking() throws FunctionnalException {
        Booking booking1 = new Booking();
        booking1.setMember(member);
        booking1.setTitle("new title");
        //when
        Booking resultBooking = bookingService.createBooking(booking1);

        assertEquals(LocalDate.now() , resultBooking.getBookingDate());
        assertEquals(booking1.getTitle() , resultBooking.getTitle());

    }

    @Test
    void createBookingThrowFunctionnalExceptionWhenTitleAlreadyBooked() {
        //when
        when(bookingDao.findByTitleOrderByBookingDate(booking.getTitle())).thenReturn(List.of(booking));
        //then
        Assertions.assertThrows(FunctionnalException.class , () -> {
            bookingService.createBooking(booking);
        });
    }

    @Test
    void deleteBooking() {
        //when
        when(bookingDao.findById(anyLong())).thenReturn(Optional.of(booking));
        //given
        Member resultMember = bookingService.deleteBooking(booking.getId());
        //then
        assertEquals(member , resultMember);
    }

    @Test
    void patchBookingThrowException() {
        //when
        when(bookingDao.findById(anyLong())).thenReturn(Optional.empty());
        //then
        Assertions.assertThrows(LoanNotFoundException.class , () -> {
            bookingService.patchBooking(1L , booking);
        });
    }

    @Test
    void patchBooking(){
        //when
        when(bookingDao.findById(anyLong())).thenReturn(Optional.of(booking));
        //given
        Booking resultBooking = bookingService.patchBooking(1L , booking);
        //then
        assertEquals(LocalDate.now(), resultBooking.getMailSendDate());
        assertEquals(booking.getId() , resultBooking.getId());
    }

    private MemberService createMemberService() {
        MemberService mock = mock(MemberService.class);
        when(mock.findByEmailAndCardNumber(anyString(),anyString())).thenReturn(member);
        return mock;
    }

    private BookingDao createBookingDao() {
        BookingDao mock = mock(BookingDao.class);
        when(mock.findByTitleOrderByBookingDate(anyString())).thenReturn(Collections.emptyList());
//        when(bookingDao.findByTitleOrderByBookingDate(booking.getTitle())).thenReturn(List.of(booking));
//        when(bookingDao.findById(1L)).thenReturn(Optional.of(booking));
//        when(bookingDao.findById(2L)).thenReturn(Optional.empty());
        return mock;
    }


}
