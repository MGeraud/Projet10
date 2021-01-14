package com.geraud.ocr_loan_api.controllers;

import com.geraud.ocr_loan_api.domain.Loan;
import com.geraud.ocr_loan_api.services.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class BatchController {
    @Autowired
    BatchService batchService;

    /* Envoi de la liste des prêts échus en fonction d'une date et d'un nombre de prolongation */
    @GetMapping("/batch")
    public ResponseEntity<List<Loan>> batchDtoList(@RequestParam("date") String date,
                                                  @RequestParam("refresh") int refresh){
        return new ResponseEntity<>(batchService.findTitleAndEmail(LocalDate.parse(date), refresh), HttpStatus.OK);
    }
}
