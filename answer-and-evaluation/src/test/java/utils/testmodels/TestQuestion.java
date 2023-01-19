package utils.testmodels;

import language.contentandrepository.model.domain.question.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.IntStream;

public class TestQuestion {

    public static <E extends Question> E generate(Class<E> type) {
        var questionType = QuestionType.fromClass(type);

        return (E) switch(questionType) {
            case CHOOSE_A_WORD -> generateChooseAWord();
            case FILL_A_WORD -> generateFillAWord();
            case ANSWER_A_QUESTION -> generateAnswerAQuestion();
        };
    }

    public static List<Question> generate(Class<? extends Question> type, int size) {
        var list = IntStream.range(0, size).mapToObj(i -> TestQuestion.generate(type)).toList();
        return new ArrayList<>(list);
    }

    public static FillAWord generateFillAWord() {
        var content = getRandomContentWithNulls();
        var correctAnswers = generateRandomWordChoice(content);
        return new FillAWord(
                TestModel.generateId(FillAWord.class),
                content,
                correctAnswers
        );
    }

    public static AnswerAQuestion generateAnswerAQuestion() {
        var rand = new Random();
        var content = new ArrayList<String>();
        var contentSize = rand.nextInt(1,5);
        for (int i = 0; i < contentSize; i++) {
            var builder = new StringBuilder();
            var sentenceSize = rand.nextInt(1, 15);
            for (int i1 = 0; i1 < sentenceSize; i1++) {
                builder.append(TestModel.getRandomWord() + " ");
            }
            content.add(builder.toString());
        }

        return new AnswerAQuestion(
                TestModel.generateId(AnswerAQuestion.class),
                content
        );
    }

    public static ChooseAWord generateChooseAWord() {
        var content = getRandomContentWithNulls();
        var wordChoice = generateRandomWordChoice(content);
        var correctAnswers = generateCorrectAnswers(wordChoice);

        return new ChooseAWord(
                TestModel.generateId(ChooseAWord.class),
                content,
                correctAnswers,
                wordChoice
        );
    }

    public static List<ChooseAWord> generateChooseAWord(int size) {
        var list = IntStream.range(0, size).mapToObj(i -> generateChooseAWord()).toList();
        return new ArrayList<>(list);
    }


    @Test
    public void test() {
        System.out.println(generateChooseAWord());
    }

    private static List<List<String>> generateCorrectAnswers(List<List<String>> wordChoice) {
        var correctAnswers = new ArrayList<List<String>>();
        var rand = new Random();
        for (List<String> strings : wordChoice) {
            var answers = new ArrayList<String>();
            var possibility = new ArrayList<>(strings);
            var size = rand.nextInt(1, strings.size());
            for (int i1 = 0; i1 < size; i1++) {
                var index = rand.nextInt(possibility.size());
                answers.add(possibility.get(index));
                possibility.remove(index);
            }
            correctAnswers.add(answers);
        }
        return correctAnswers;
    }

    private static List<String> getRandomContentWithNulls() {
        var content = new ArrayList<String>();
        var rand = new Random();
        var contentSize = rand.nextInt(2, 10);

        content.add(rand.nextBoolean() ? null : TestModel.getRandomWord());

        for (int i = 1; i < contentSize; i++)
            content.add(content.get(i - 1) == null ? TestModel.getRandomWord() : null);
        return content;
    }

    private static List<List<String>> generateRandomWordChoice(List<String> content) {
        var rand = new Random();
        var emptyPlaces = content.stream().filter(Objects::isNull).count();
        var wordChoice = new ArrayList<List<String>>();
        for (long i = 0; i < emptyPlaces; i++) {
            var size = rand.nextInt(2, 5);
            var choice = new ArrayList<String>();
            for (int i1 = 0; i1 < size; i1++) {
                choice.add(TestModel.getRandomWord());
            }
            wordChoice.add(choice);
        }
        return wordChoice;
    }


}
