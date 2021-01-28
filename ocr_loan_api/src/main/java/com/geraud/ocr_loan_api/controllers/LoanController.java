package com.geraud.ocr_loan_api.controllers;

import com.geraud.ocr_loan_api.domain.Loan;
import com.geraud.ocr_loan_api.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

@RestController
public class LoanController {

    @Autowired
    private LoanService loanService;

    /** Modification d'un prêt : par exdemple entrer date de retour */
    @PatchMapping("/loan/{id}")
    public ResponseEntity<Loan> patchLoan(@PathVariable Long id , @RequestBody Loan loan){
        return new ResponseEntity<Loan>(loanService.patchLoan(id, loan) , HttpStatus.OK);
    }

    /** Création d'un prêt à partir du numéro de carte de l'emprunteur */
    @PostMapping("/loan/{cardnumber}")
    public ResponseEntity<Loan> createLoan(@PathVariable String cardnumber , @RequestBody Loan loan){
        return new ResponseEntity<Loan>(loanService.createLoan(cardnumber, loan) , HttpStatus.OK);
    }

    /**
     *  Liste des prêts d'un membre à partir de son email et numero de carte de bibliotheque*/
    @GetMapping("/loan")
    public List<Loan> loansOfMember(@RequestParam("email") String email,
                                    @RequestParam("cardnumber") String cardnumber){
        return loanService.listLoans(email, cardnumber);
    }

    /**
     * recherche de disponibilité d'un livre
     * @param label identification unique de l'exemplaire à rechercher
     * @return true si livre disponible
     */
    @GetMapping("/loan/{label}")
    public boolean available(@PathVariable String label){
        return loanService.labelAvailable(label);
    }

    @GetMapping("/loan/title")
    public List<Loan> loansByTitle(@RequestParam("title") String title) throws UnsupportedEncodingException {
        String titleToUtf = URLDecoder.decode(title , "utf-8");
        return loanService.listLoansByTitle(titleToUtf);
    }
}
