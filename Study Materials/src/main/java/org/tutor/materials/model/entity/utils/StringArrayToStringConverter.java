package org.tutor.materials.model.entity.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.util.CollectionUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.ArrayList;
import java.util.List;

import static org.tutor.materials.model.entity.utils.ObjectMapperProvider.getMapper;

@Converter
public class StringArrayToStringConverter implements AttributeConverter<List<String>, String> {

    @Override
    public String convertToDatabaseColumn(List<String> strings) {
        if(CollectionUtils.isEmpty(strings))
            return null;
        try {
            return getMapper().writeValueAsString(strings);
        } catch (JsonProcessingException e) {
            System.err.println("Failed to serialize: " + strings);
            return null;
        }
    }

    @Override
    public List<String> convertToEntityAttribute(String s) {
        try {
            return getMapper().readValue(s, new TypeReference<List<String>>() {});
        } catch (JsonProcessingException e) {
            System.err.println("Failed to deserialize: " + s);
            return new ArrayList<>();
        }
    }

}
