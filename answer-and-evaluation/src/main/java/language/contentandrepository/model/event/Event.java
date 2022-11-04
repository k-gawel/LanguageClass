package language.contentandrepository.model.event;

import language.contentandrepository.model.domain.user.AppUser;
import language.contentandrepository.model.DomainID;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class Event<INPUT, OUTPUT> {

    private final DomainID<AppUser> accessor;
    private final INPUT input;
    private final OUTPUT output;
    private final List<String> errors;
    private final LocalDateTime date;

    protected Event(DomainID<AppUser> accessor, INPUT input, OUTPUT output, List<String> errors, LocalDateTime date) {
        this.accessor = accessor;
        this.input = input;
        this.output = output;
        this.errors = errors;
        this.date = date;
        if(input != null && output != null)
            throw new IllegalArgumentException();
    }

    public Event(DomainID<AppUser> accessor, INPUT input, OUTPUT output) {
        this(accessor, input, output, null, LocalDateTime.now());
    }

    public Event(DomainID<AppUser> accessor, INPUT input, List<String> errors) {
        this(accessor, input, null, errors, LocalDateTime.now());
    }

    public INPUT input() {
        return input;
    }

    public Optional<OUTPUT> output() {
        return Optional.ofNullable(output);
    }

    public Optional<List<String>> errors() {
        return Optional.ofNullable(errors);
    }

    public LocalDateTime date() {
        return date;
    }

    public DomainID<AppUser> accessor() {
        return accessor;
    }


}
