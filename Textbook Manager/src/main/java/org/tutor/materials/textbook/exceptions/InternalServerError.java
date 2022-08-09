package org.tutor.materials.textbook.exceptions;

public class InternalServerError extends AppException {

    public InternalServerError(Throwable throwable) {
        super("Internal server error.", throwable);
    }


}
