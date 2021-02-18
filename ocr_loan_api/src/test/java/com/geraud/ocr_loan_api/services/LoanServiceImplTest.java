package com.geraud.ocr_loan_api.services;

import com.geraud.ocr_loan_api.dao.LoanDao;
import com.geraud.ocr_loan_api.dao.MemberDao;
import com.geraud.ocr_loan_api.domain.Loan;
import com.geraud.ocr_loan_api.domain.Member;
import com.geraud.ocr_loan_api.exceptions.LoanNotFoundException;
import com.geraud.ocr_loan_api.exceptions.NomemberFound;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class LoanServiceImplTest {

    Loan loan;
    Member member;

    @Mock
    LoanDao loanDao;
    @Mock
    MemberDao memberDao;

    @InjectMocks
    LoanServiceImpl loanService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        loan = new Loan();
        loan.setId(1L);
        loan.setTitle("titre");
        loan.setLabel("label");
        loan.setStartingDate(LocalDate.of(2021,01,01));
        loan.setRefreshEndingCounter(0);

        member = new Member();
        member.setCardnumber("1234");
        member.setEmail("email");
    }

    @Test
    void patchLoanWrongId() {
        when(loanDao.findById(anyLong())).thenReturn(Optional.empty());

        Assertions.assertThrows(LoanNotFoundException.class , () -> {
            loanService.patchLoan(1L , loan);
        });
    }

    @Test
    void patchLoanWithBookBackDateNotNull(){
        //given
        loan.setBookBackDate(LocalDate.of(2021,01,02));
        when(loanDao.findById(anyLong())).thenReturn(Optional.of(loan));
        //when
        Loan resultLoan = loanService.patchLoan(1L, loan);
        //then
        assertEquals(loan.getBookBackDate() , resultLoan.getBookBackDate());
        assertEquals(0 , resultLoan.getRefreshEndingCounter());
    }

    @Test
    void patchLoanWithRefreshEndingCounterBiggerThanZero(){
        //given
        loan.setRefreshEndingCounter(1);
        when(loanDao.findById(anyLong())).thenReturn(Optional.of(loan));
        //when
        Loan resultLoan = loanService.patchLoan(1L, loan);
        //then
        assertEquals(2 , resultLoan.getRefreshEndingCounter());
        assertNull(resultLoan.getBookBackDate());
    }


    @Test
    void createLoanWrongCardNumber() {
        //given
        when(memberDao.findByCardnumber(anyString())).thenReturn(Optional.empty());
        //then
        Assertions.assertThrows(NomemberFound.class , () -> {
            loanService.createLoan("111" , loan);
        });
    }

    @Test
    void createLoan(){
        //when
        when(memberDao.findByCardnumber(anyString())).thenReturn(Optional.of(member));
        //given
        Loan resultLoan = loanService.createLoan("1111" , loan);
        //then
        assertEquals(member , resultLoan.getMember());
    }

    @Test
    void listLoans() {
        //when
        when(memberDao.findByEmailAndCardnumber(anyString(),anyString())).thenReturn(Optional.empty());
        //then
        Assertions.assertThrows(NomemberFound.class , () -> {
            loanService.listLoans("email" , "1234");
        });
    }

}