 package language.graphql.shared;

 import graphql.language.Field;

 import java.util.List;

 public class DataFetchingEnvironmentUtils {

    public static List<Field> getSelectedFields(Field field) {
        return field.getSelectionSet()
                .getSelections()
                .stream()
                .filter(s -> s instanceof Field)
                .map(s -> (Field) s)
                .toList();
    }

    public static List<String> getSelectedFieldNames(Field field) {
        return getSelectedFields(field).stream().map(Field::getName).toList();
    }

    public static boolean isOnlyId(Field field) {
        var selectedNames = getSelectedFieldNames(field);
        return selectedNames.size() == 1 && selectedNames.contains("id");
    }

}
