package org.tutor.materials.textbook.model.entity.shared;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.ArrayList;
import java.util.List;

import static org.tutor.materials.textbook.model.entity.shared.ObjectMapperProvider.getMapper;

@Converter
public class TwoDimensionalListOfStringsConverter implements AttributeConverter<List<List<String>>, String> {

    @Override
    public String convertToDatabaseColumn(List<List<String>> lists) {
        try {
            return getMapper().writeValueAsString(lists);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<List<String>> convertToEntityAttribute(String s) {
        try {
            return getMapper().readValue(s, new TypeReference<List<List<String>>>() {});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

}
