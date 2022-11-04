package language.contentandrepository.persistence.entity.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import java.util.Objects;

public class JacksonAttributeConverter<E> implements AttributeConverter<E, String> {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(E e) {
        try {
            return mapper.writeValueAsString(e);
        } catch (JsonProcessingException ex) {
            throw new IllegalArgumentException(Objects.toString(e), ex);
        }
    }

    @Override
    public E convertToEntityAttribute(String s) {
        try {
            return mapper.readValue(s, new TypeReference<E>() {});
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(s, e);
        }
    }
}
