package com.geraud.ocr_loan_api.controllers;

import com.geraud.ocr_loan_api.domain.Member;
import com.geraud.ocr_loan_api.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    @Autowired
    MemberService memberService;

    @GetMapping("/member")
    public ResponseEntity<Member> getMember(@RequestParam("email") String email,
                                           @RequestParam("cardnumber") String cardnumber){
        return new ResponseEntity<> (memberService.findByEmailAndCardNumber(email, cardnumber) , HttpStatus.OK);
    }
}
