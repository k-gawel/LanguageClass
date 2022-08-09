package org.tutor.materials.service.events.processors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.tutor.materials.textbook.model.domain.input.TextbookInput;
import org.tutor.materials.textbook.model.domain.type.ID;
import org.tutor.materials.textbook.model.domain.type.content.Textbook;
import org.tutor.materials.textbook.model.domain.type.users.AppUser;
import org.tutor.materials.textbook.service.events.events.TextbookCreationEvent;
import org.tutor.materials.textbook.service.events.processors.EventMethodValidator;
import org.tutor.materials.textbook.service.events.processors.PublishEvent;

import static org.junit.Assert.*;

class EventMethodFailOnPresentTest {

    @Test
    public void whenFirstArgumentIsMissing_thenHasErrors() {
        var method = new Object() {
            void method() {
            }
        }.getClass().getDeclaredMethods()[0];
        assertEquals("method", method.getName());

        var validator = new EventMethodValidator();
        Assertions.assertFalse(validator.validateFirstArgument(method).isEmpty());
    }

    @Test
    public void whenFirstArgumentIsNotIDofAppUser_thenHasErrors() {
        var method = new Object() {
            void method() {
            }
        }.getClass().getDeclaredMethods()[0];
        assertEquals("method", method.getName());

        var validator = new EventMethodValidator();
        Assertions.assertFalse(validator.validateFirstArgument(method).isEmpty());
    }

    @Test
    public void whenFirstArgumentIsDescendantOfAppUser_thenHasNoErrors() {
        var method = new Object() {
            void method(ID<? extends AppUser> e) {
            }
        }.getClass().getDeclaredMethods()[0];
        assertEquals("method", method.getName());

        var validator = new EventMethodValidator();
        assertTrue(validator.validateFirstArgument(method).isEmpty());
    }

    @Test
    public void whenSecondArgumentIsMissing_thenHasErrors() {
        var method = new Object() {
            @PublishEvent(TextbookCreationEvent.class)
            void method(ID<? extends AppUser> e) {
            }
        }.getClass().getDeclaredMethods()[0];
        assertEquals("method", method.getName());

        var validator = new EventMethodValidator();
        assertFalse(validator.validateSecondArgument(method).isEmpty());
    }

    @Test
    public void whenSecondArgumentIsWrongType_thenHasErrors() {
        var method = new Object() {
            @PublishEvent(TextbookCreationEvent.class)
            void method(ID<? extends AppUser> e, Object input) {
            }
        }.getClass().getDeclaredMethods()[0];
        assertEquals("method", method.getName());

        var validator = new EventMethodValidator();
        assertFalse(validator.validateSecondArgument(method).isEmpty());
    }

    @Test
    public void whenSecondArgumentIsTypeOfInputType_thenHasNoErrors() {
        var method = new Object() {
            @PublishEvent(TextbookCreationEvent.class)
            void method(ID<? extends AppUser> e, TextbookInput input) {
            }
        }.getClass().getDeclaredMethods()[0];

        assertEquals("method", method.getName());

        var validator = new EventMethodValidator();
        assertFalse(validator.validateSecondArgument(method).isEmpty());
    }

    @Test
    public void whenReturnTypeIsNotCorrect_thenHasErrors() {
        var method = new Object() {
            @PublishEvent(TextbookCreationEvent.class)
            void method() {
            }
        }.getClass().getDeclaredMethods()[0];
        assertEquals("method", method.getName());

        var validator = new EventMethodValidator();
        assertFalse(validator.validateReturnType(method).isEmpty());
    }

    @Test
    public void whenReturnTypeIsCorrect_thenHasNoErrors() {
            var method = new Object() {
                @PublishEvent(TextbookCreationEvent.class)
                ID<Textbook> method() {
                    return null;
                }
            }.getClass().getDeclaredMethods()[0];
            assertEquals("method", method.getName());

            var validator = new EventMethodValidator();
            assertTrue(validator.validateReturnType(method).isEmpty());
    }


}