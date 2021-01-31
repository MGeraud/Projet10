package com.geraud.ocr_loan_api.services;

import com.geraud.ocr_loan_api.dao.LoanDao;
import com.geraud.ocr_loan_api.dao.MemberDao;
import com.geraud.ocr_loan_api.domain.Loan;
import com.geraud.ocr_loan_api.domain.Member;
import com.geraud.ocr_loan_api.exceptions.LoanNotFoundException;
import com.geraud.ocr_loan_api.exceptions.NomemberFound;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoanServiceImpl implements LoanService{
    private final LoanDao loanDao;
    private final MemberDao memberDao;

    public LoanServiceImpl(LoanDao loanDao, MemberDao memberDao) {
        this.loanDao = loanDao;
        this.memberDao = memberDao;
    }

    @Override
    public Loan patchLoan(Long id, Loan loan) {

        // recherche du prêt à partir de son id, si non trouvé renvoi erreur
        Optional<Loan> foundLoan = loanDao.findById(id);
        if (!foundLoan.isPresent()){
            throw new LoanNotFoundException("Prêt non trouvé avec l'id : " + id);
        }
        //création du prêt à modifier
        Loan loanToModify = foundLoan.get();

        //vérification des 2 champs modifiable (date de retour et renouvellement période prêt) et attribution nouvelle valeur si nécessaire
        //création date retour sera à implémenter avec microservice des employés pour déclarer le retour du prêt
        if (loan.getBookBackDate() != null){
           loanToModify.setBookBackDate(loan.getBookBackDate());
        }
        //augmenter compteur de demande de prolongation de prêt (compteur plutot que booléen car si décide de permettre prolongation de prêt plusieurs fois par la suite
        if (loan.getRefreshEndingCounter() != 0) {
            loanToModify.setRefreshEndingCounter(loanToModify.getRefreshEndingCounter() + 1 );
        }

        loanDao.save(loanToModify);

        return loanToModify;
    }

    @Override
    public Loan createLoan(String cardnumber, Loan loan) {

        //vérification s'il existe un utilisateur ayant ce numero de carte, sinon envoi exception
        Optional<Member> optionalMember = memberDao.findByCardnumber(cardnumber);

        if (!optionalMember.isPresent()){
            throw new NomemberFound("numéro de carte " + cardnumber + " : utilisateur inconnu");
        }
        Member member = optionalMember.get();

        //attribution du prêt à enregistrer à l'utilisateur
        loan.setMember(member);
        loanDao.save(loan);

        return loan;
    }

    @Override
    public List<Loan> listLoans(String email, String cardnumber) {
        //vérification s'il existe un utilisateur ayant ce numero de carte et email, sinon envoi exception
        Optional<Member> optionalMember = memberDao.findByEmailAndCardnumber(email, cardnumber);

        if (!optionalMember.isPresent()){
            throw new NomemberFound("Utilisateur inconnu avec email : " + email + " et numéro de carte : " + cardnumber);
        }
        Member member = optionalMember.get();

        return loanDao.findByMemberID(member.getId());
    }

    /**
     * Recherche si un exemplaire de livre est en cours d'emprunt
     * @param label référence unique de l'exemplaire du livre
     * @return true si disponible (pas d'emprunt en cours non retourné)
     */
    @Override
    public boolean labelAvailable(String label) {
        Optional<Loan> notAvailable = loanDao.findByLabelEqualsAndBookBackDateIsNull(label);
        return notAvailable.isEmpty();
    }

    @Override
    public List<Loan> listLoansByTitle(String title) {
        return loanDao.findByTitleAndBookBackDateIsNullOrderByStartingDate(title);
    }


}
