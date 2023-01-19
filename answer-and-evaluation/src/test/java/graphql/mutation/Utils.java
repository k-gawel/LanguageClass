package graphql.mutation;

import org.junit.jupiter.api.Test;

public class Utils {

    public static String mutationRoot = Utils.class.getClassLoader().getResource("graphql/mutation/").getFile();

    @Test
    public void test() {
        System.out.println("mutationRoot = " + mutationRoot);
    }

}
