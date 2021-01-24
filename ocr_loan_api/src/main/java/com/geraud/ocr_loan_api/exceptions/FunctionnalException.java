package com.geraud.ocr_loan_api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.PRECONDITION_FAILED , reason = "Titre déjà réservé par utilisateur")
public class FunctionnalException extends Exception{
    public FunctionnalException() {
    }

    public FunctionnalException(String message) {
        super(message);
    }

    public FunctionnalException(String message, Throwable cause) {
        super(message, cause);
    }

    public FunctionnalException(Throwable cause) {
        super(cause);
    }

    public FunctionnalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
