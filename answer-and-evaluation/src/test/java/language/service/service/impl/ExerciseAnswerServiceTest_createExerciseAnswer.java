package language.service.service.impl;

import utils.dummyrepositories.TestExerciseRepositoryFactory;
import utils.dummyrepositories.TestTeacherRepositoryFactory;
import language.contentandrepository.model.DomainID;
import language.contentandrepository.model.domain.question.ChooseAWord;
import language.contentandrepository.model.domain.question.Question;
import language.contentandrepository.model.domain.question.QuestionType;
import language.contentandrepository.model.domain.textbook.Exercise;
import language.contentandrepository.model.domain.user.Teacher;
import language.service.service.answerandevaluation.implementations.PublisherExerciseAnswerService;
import language.service.service.answerandevaluation.inputs.ExerciseAnswerCreateInput;
import language.service.service.answerandevaluation.services.ExerciseAnswerService;
import org.junit.jupiter.api.function.Executable;
import utils.testmodels.TestClock;
import utils.testmodels.TestQuestion;

import java.util.List;

public class ExerciseAnswerServiceTest_createExerciseAnswer {

    private final Teacher existing_teacher = new Teacher(
            new DomainID<>(Teacher.class, "existing_teacher_1"), "name"
    );
    private final Teacher non_existing_teacher = new Teacher(
            new DomainID<>(Teacher.class, "non_existing_teacher_1"), "name"
    );

    private final List<Question> questions = TestQuestion.generate(ChooseAWord.class, 5);

    private final Exercise existing_exercise = new Exercise(
            new DomainID<>(Exercise.class, "existing_exercise_1"),
            "title",
            QuestionType.CHOOSE_A_WORD,
            questions.stream().map(q -> (DomainID<Question>) q.id()).toList()
    );

    private final ExerciseAnswerService exerciseAnswerService = new PublisherExerciseAnswerService(
            new TestClock(),
            TestTeacherRepositoryFactory.repository(List.of(existing_teacher)),
            TestExerciseRepositoryFactory.repository(List.of(existing_exercise)),
            null
    );

    private Executable create(ExerciseAnswerCreateInput input) {
        return () -> exerciseAnswerService.create(input);
    }

}