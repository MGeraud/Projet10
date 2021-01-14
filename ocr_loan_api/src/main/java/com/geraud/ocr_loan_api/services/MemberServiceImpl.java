package com.geraud.ocr_loan_api.services;

import com.geraud.ocr_loan_api.dao.MemberDao;
import com.geraud.ocr_loan_api.domain.Member;
import com.geraud.ocr_loan_api.exceptions.NomemberFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao memberDao;

    @Override
    public Member findByEmailAndCardNumber(String email , String cardnumber) {
        Optional<Member> member = memberDao.findByEmailAndCardnumber(email,cardnumber);
        if (!member.isPresent()){
            throw new NomemberFound("Utilisateur non trouvé");
        }
        return member.get();
    }
}
