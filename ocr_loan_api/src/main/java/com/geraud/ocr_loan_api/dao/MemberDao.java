package com.geraud.ocr_loan_api.dao;

import com.geraud.ocr_loan_api.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberDao extends JpaRepository<Member, Long> {

    Optional<Member> findByEmailAndCardnumber(String email , String cardnumber);
    Optional<Member> findByCardnumber(String cardnumber);
}
