package com.geraud.ocr_webapp.exception;

public class FunctionnalException extends Exception {
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
