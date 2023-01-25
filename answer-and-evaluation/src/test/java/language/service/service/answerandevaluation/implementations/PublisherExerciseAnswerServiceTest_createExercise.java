package language.service.service.answerandevaluation.implementations;

import language.contentandrepository.model.DomainID;
import language.contentandrepository.model.domain.answerandevaluation.ExerciseAnswer;
import language.contentandrepository.model.domain.answerandevaluation.QuestionAnswer;
import language.contentandrepository.model.domain.question.ChooseAWord;
import language.contentandrepository.model.domain.textbook.Exercise;
import language.contentandrepository.model.domain.user.Student;
import language.contentandrepository.model.domain.user.Teacher;
import language.contentandrepository.repository.answerandevalution.ExerciseAnswerRepository;
import language.contentandrepository.repository.textbook.ExerciseRepository;
import language.contentandrepository.repository.user.TeacherRepository;
import language.service.service.answerandevaluation.inputs.ExerciseAnswerCreateInput;
import language.service.service.answerandevaluation.services.ExerciseAnswerService;
import org.junit.jupiter.api.Test;
import utils.dummyrepositories.TestExerciseAnswerRepositoryFactory;
import utils.dummyrepositories.TestExerciseRepositoryFactory;
import utils.dummyrepositories.TestTeacherRepositoryFactory;
import utils.testmodels.*;

import java.util.List;

import static org.junit.Assert.*;

public class PublisherExerciseAnswerServiceTest_createExercise {

    private final Student student = TestStudent.generate();
    private final Teacher teacher = TestTeacher.generate();
    private final List<ChooseAWord> questions = TestQuestion.generateChooseAWord(5);
    private final Exercise exercise = TestExercise.generate(questions);

    private final TestClock clock = new TestClock();
    private final TeacherRepository teacherRepository = TestTeacherRepositoryFactory.repository(teacher);
    private final ExerciseRepository exerciseRepository = TestExerciseRepositoryFactory.repository(exercise);
    private final ExerciseAnswerRepository exerciseAnswerRepository = TestExerciseAnswerRepositoryFactory.emptyRepository();

    private final ExerciseAnswerService exerciseAnswerService = new PublisherExerciseAnswerService(
            clock, teacherRepository, exerciseRepository, null
    );

    @Test
    public void rejectInputWithQuestionAnswerNotFromExercise() {
        var questionAnswers = exercise.questions().stream()
                .map(i -> TestQuestion.generateChooseAWord())
                .map(TestQuestionAnswer::generate)
                .toList();

        var input = new ExerciseAnswerCreateInput(
                student, exercise, questionAnswers
        );

        assertThrows(IllegalArgumentException.class, () -> exerciseAnswerService.create(input));
    }

    @Test
    public void rejectInputWithQuestionAnswerByAnotherStudent() {
        var questionAnswers = exercise.questions()
                .stream().map(q -> TestQuestionAnswer.generate(q, TestStudent.generate().id()))
                .toList();

        var input = new ExerciseAnswerCreateInput(
                student,
                exercise,
                questionAnswers
        );

        assertThrows(IllegalArgumentException.class, () -> exerciseAnswerService.create(input));
    }



    @Test
    public void createExerciseAnswer() {
        var questionAnswers = exercise.questions().stream()
                .map(q -> TestQuestionAnswer.generate(q, student.id()))
                .toList();

        var input = new ExerciseAnswerCreateInput(
                student,
                exercise,
                questionAnswers
        );

        var expectedId = String.join("_", exercise.id().id(), "answer_by", student.id().id(), "_0");

        var expectedAnswer = new ExerciseAnswer(
                    new DomainID<>(ExerciseAnswer.class, expectedId),
                exercise.id(),
                student.id(),
                questionAnswers.stream().map(QuestionAnswer::id).toList(),
                clock.now()
                );

        var actualAnswer = exerciseAnswerService.create(input);

        assertEquals(expectedAnswer, actualAnswer);
        assertTrue(exerciseAnswerRepository.findById(expectedAnswer.id()).isPresent());
    }

}