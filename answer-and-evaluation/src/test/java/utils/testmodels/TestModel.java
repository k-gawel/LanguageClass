package utils.testmodels;

import language.contentandrepository.model.Domain;
import language.contentandrepository.model.DomainID;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.Random;
import java.util.Scanner;

public class TestModel {

    private static long lastUsedId;

    public static <E extends Domain> DomainID<E> generateId(Class<E> type) {
        var nanoTime = System.nanoTime();
        nanoTime = nanoTime == lastUsedId ? nanoTime + 1 : nanoTime;
        lastUsedId = nanoTime;
        return new DomainID<>(type, type.getSimpleName() + "__" + nanoTime);
    }

    static String getRandomWord()
    {
        File file = null;
        try {
            file = new File(TestModel.class.getClassLoader().getResource("wordlist.txt").toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        String result = null;
        Random rand = new Random();
        int n = 0;
        try {
            for(Scanner sc = new Scanner(file); sc.hasNext(); )
            {
                ++n;
                String line = sc.nextLine();
                if(rand.nextInt(n) == 0)
                    result = line;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    @Test
    public void test() {
        System.out.println(getRandomWord());
    }

}