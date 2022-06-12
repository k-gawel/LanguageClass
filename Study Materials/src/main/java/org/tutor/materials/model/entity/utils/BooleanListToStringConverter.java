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
public class BooleanListToStringConverter implements AttributeConverter<List<Boolean>, String> {

    @Override
    public String convertToDatabaseColumn(List<Boolean> booleanList) {
        if(CollectionUtils.isEmpty(booleanList))
            return null;
        try {
            return getMapper().writeValueAsString(booleanList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Boolean> convertToEntityAttribute(String s) {
        try {
            return getMapper().readValue(s, new TypeReference<List<Boolean>>() {});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

}
