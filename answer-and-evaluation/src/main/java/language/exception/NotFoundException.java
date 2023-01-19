package language.exception;

import language.contentandrepository.model.DomainID;

public class NotFoundException extends RuntimeException {

    public NotFoundException(DomainID id) {
        this(id.id());
    }

    public NotFoundException(String id) {
        super(id + ".not_found");
    }
}
