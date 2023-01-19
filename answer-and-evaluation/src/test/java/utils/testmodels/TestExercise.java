package utils.testmodels;

import language.contentandrepository.model.Domain;
import language.contentandrepository.model.DomainID;
import language.contentandrepository.model.domain.question.Question;
import language.contentandrepository.model.domain.question.QuestionType;
import language.contentandrepository.model.domain.textbook.Exercise;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Random;
import java.util.UUID;

public class TestExercise {

    public static Exercise generate(List<? extends Question> questions) {
        if(CollectionUtils.isEmpty(questions))
            throw new IllegalArgumentException("Questions can't be empty");
        var questionType = QuestionType.fromClass(questions.get(0).getClass());
        var allMatch= questions.stream().map(i -> QuestionType.fromClass(i.getClass())).allMatch(i -> i.equals(questionType));
        if(!allMatch)
            throw new IllegalArgumentException("Questions must be of the same type");

        return new Exercise(
                TestModel.generateId(Exercise.class),
                "title_" + UUID.randomUUID().toString(),
                questionType,
                questions.stream().map(Domain::id).map(i -> (DomainID<Question>) i).toList()
        );
    }

    public static Exercise generate() {
        var rand = new Random();
        var type = QuestionType.values()[rand.nextInt(QuestionType.values().length)];
        var size = rand.nextInt(1, 10);

        var questions = TestQuestion.generate(type.questionClass(), size);

        return generate(questions);
    }


}
