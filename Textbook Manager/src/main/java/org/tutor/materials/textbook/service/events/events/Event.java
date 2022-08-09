package org.tutor.materials.textbook.service.events.events;

import org.springframework.context.ApplicationEvent;
import org.tutor.materials.textbook.model.domain.type.ID;
import org.tutor.materials.textbook.model.domain.type.users.AppUser;
import org.tutor.materials.textbook.shared.Errors;

import java.time.LocalDateTime;
import java.util.Optional;

public abstract class Event<IN, OUT> extends ApplicationEvent {

    private final ID<? extends AppUser> accessor;
    private final IN input;
    private final OUT result;
    private final Errors errors;
    private final LocalDateTime time;

    protected Event(ID<? extends AppUser> accessor, IN input, OUT result, Errors errors, LocalDateTime time) {
        super(accessor);
        if(result != null && errors != null)
            throw new IllegalArgumentException("Result and errors can't be both non-null.");
        this.accessor = accessor;
        this.input = input;
        this.result = result;
        this.errors = errors;
        this.time = time;
    }

    protected Event(ID<? extends AppUser> accessor, IN input, OUT result, Errors errors) {
        this(accessor, input, result, errors, LocalDateTime.now());
    }

    protected Event(ID<? extends AppUser> accessor, IN input, OUT result) {
        this(accessor, input, result, null);
    }

    protected Event(ID<? extends AppUser> accessor, IN input, Errors errors) {
        this(accessor, input, null, errors);
    }

    public abstract static class Builder<EVENT extends Event<IN , OUT>, IN, OUT> {
        protected final ID<? extends AppUser> accessor;
        protected final IN input;

        public Builder(ID<? extends AppUser> accessor, IN input) {
            this.accessor = accessor;
            this.input = input;
        }

        public abstract EVENT success(OUT result);
        public abstract EVENT error(Errors errors);
    }

    public Optional<OUT> result() {
        return Optional.ofNullable(result);
    }
    public Optional<Errors> errors() {
        return Optional.ofNullable(errors);
    }
    public LocalDateTime time() {
        return time;
    }
    public IN input() {
        return input;
    }
    public ID<? extends AppUser> accessor() {
        return accessor;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{\n" +
                "accessor=" + accessor +
                ",\n input=" + input +
                ",\n result=" + result +
                ",\n errors=" + errors +
                ",\n time=" + time +
                "\n}\n";
    }
}
