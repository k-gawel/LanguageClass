package org.tutor.materials.textbook.exceptions;

public abstract class AppException extends RuntimeException {

    protected AppException(String message, Throwable couse) {
        super(message, couse);
    }

    protected AppException(String message) {
        super(message);
    }

}
