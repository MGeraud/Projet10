package com.geraud.ocr_loan_api.services;

import com.geraud.ocr_loan_api.domain.Member;

public interface MemberService {

    Member findByEmailAndCardNumber(String email, String cardnumber);
}
