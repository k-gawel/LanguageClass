package language.service.input;

import language.graphql.shared.Input;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InputTest {

    private record TestInput(
            String variable1,
            Integer variable2
    ) implements Input {

        private Optional<String> validateVariable1() {
            return Optional.of("TEST");
        }

        private Optional<String> validateVariable2() {
            return Optional.empty();
        }

        private Optional<Integer> validateVariable7() {
            return Optional.of(Integer.MAX_VALUE);
        }

    }

    @Test
    public void validateTest() {
        var input = new TestInput("string", 123);
        assertEquals(List.of("TEST"), input.validate());
    }
}