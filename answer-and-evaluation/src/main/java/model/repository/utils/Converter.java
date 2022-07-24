package model.repository.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.List;

public class Converter {

    private static ObjectMapper mapper;

    public static final class FromDatabase {

        public static List<List<String>> nestedStringList(String string) {
            try {
                return mapper().readValue(string, new TypeReference<List<List<String>>>() {
                });
            } catch (JsonProcessingException e) {
                throw new IllegalArgumentException(string, e);
            }
        }

        public static List<String> stringList(String string) {
            try {
                return mapper().readValue(string, new TypeReference<List<String>>() {
                });
            } catch (JsonProcessingException e) {
                throw new IllegalArgumentException(string, e);
            }
        }

        public static List<Boolean> booleanList(String string) {
            try {
                return mapper().readValue(string, new TypeReference<List<Boolean>>() {
                });
            } catch (JsonProcessingException e) {
                throw new IllegalArgumentException(string, e);
            }
        }

    }

    public static final class ToDatabase {

        public static String nestedStringList(List<List<String>> list) {
            try {
                return mapper().writeValueAsString(list);
            } catch (JsonProcessingException e) {
                throw new IllegalArgumentException(list.toString(), e);
            }
        }

        public static String stringList(List<String> list) {
            try {
                return mapper().writeValueAsString(list);
            } catch (JsonProcessingException e) {
                throw new IllegalArgumentException(list.toString(), e);
            }
        }

        public static String booleanList(List<Boolean> list) {
            try {
                return mapper().writeValueAsString(list);
            } catch (JsonProcessingException e) {
                throw new IllegalArgumentException(list.toString(), e);
            }
        }

    }

    private static ObjectMapper mapper() {
        if(mapper == null) {
            mapper = new ObjectMapper();
            mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        }
        return mapper;
    }

}
