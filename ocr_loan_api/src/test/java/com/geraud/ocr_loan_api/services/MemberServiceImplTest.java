package com.geraud.ocr_loan_api.services;

import com.geraud.ocr_loan_api.dao.MemberDao;
import com.geraud.ocr_loan_api.domain.Member;
import com.geraud.ocr_loan_api.exceptions.NomemberFound;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


class MemberServiceImplTest {

    Member member;

    @InjectMocks
    private MemberServiceImpl memberService;

    @Mock
    private MemberDao memberDao;


    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        member = new Member();
        member.setEmail("email@email.com");
        member.setCardnumber("1234");
    }
    @Test
    void findByEmailAndCardNumber() {
        Optional<Member> optionalMember = Optional.of(member);
        when(memberDao.findByEmailAndCardnumber(anyString() , anyString())).thenReturn(optionalMember);

        assertTrue(member.getEmail().equals(memberService.findByEmailAndCardNumber("email" , "1111").getEmail()));
    }

    @Test
    void findByWrongThrowException(){
        when(memberDao.findByEmailAndCardnumber(anyString() , anyString())).thenReturn(Optional.empty());
        Assertions.assertThrows(NomemberFound.class , () -> {
            memberService.findByEmailAndCardNumber("email" , "1111");
        }
        );
    }
}