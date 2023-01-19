package language.contentandrepository.repository;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DomainIdGeneratorTest {

    @Test
    public void generateNewId() {

        var generator = new DomainIdGenerator<>(
                new IdsRepository() {
                    @Override
                    public List<String> findIdsStartingWith(String beginString) {
                        return List.of(
                                beginString + "__2",
                                beginString + "__4"
                        );
                    }
                },
                new IdsRepository() {
                    @Override
                    public List<String> findIdsStartingWith(String beginString) {
                        return List.of(
                                beginString + "__0",
                                beginString + "__1",
                                beginString + "__3"
                        );
                    }
                }
        );

        var generatedId = generator.generateNewId("base_id");

        assertEquals("base_id__5", generatedId);
    }

}