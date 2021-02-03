package com.geraud.ocr_webapp.exception;

public class NotAllowedBookingException extends Exception{
    public NotAllowedBookingException() {
    }

    public NotAllowedBookingException(String message) {
        super(message);
    }

    public NotAllowedBookingException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotAllowedBookingException(Throwable cause) {
        super(cause);
    }

    public NotAllowedBookingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
