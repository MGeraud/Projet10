package com.geraud.ocr_loan_api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND , reason = "Utilisateur non trouvé")
public class NomemberFound extends RuntimeException{

    public NomemberFound() {
    }

    public NomemberFound(String message) {
        super(message);
    }

    public NomemberFound(String message, Throwable cause) {
        super(message, cause);
    }
}
