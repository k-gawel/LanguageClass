package service;

import model.domain.*;
import model.domain.answer.QuestionAnswer;
import model.domain.content.ChooseAWordQuestion;
import model.domain.content.Question;
import model.domain.evaluation.QuestionEvaluation;
import model.domain.user.Student;
import model.domain.user.Teacher;
import model.input.QuestionEvaluationInput;
import model.repository.QuestionRepository;
import org.junit.jupiter.api.Test;
import service.evaluator.ChooseAWordEvaluator;

import java.sql.Timestamp;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChooseAWordEvaluatorTest {

    private final QuestionEvaluationCreator creator =
            new QuestionEvaluationCreator(null, null, null) {
        @Override
        public QuestionEvaluation create(QuestionEvaluationInput input) {
            return new QuestionEvaluation(
                    new ID<>(QuestionEvaluation.class, "EVALUATION_ID"),
                    input.answer(),
                    input.author(),
                    input.comments(),
                    input.score(),
                    new Timestamp(new Date().getTime())
            );
        }
    };

    private final Map<ID<ChooseAWordQuestion>, ChooseAWordQuestion> dataSource = new HashMap<>();

    private final QuestionRepository repository = new QuestionRepository(null) {

        @Override
        public <E extends Question> Optional<E> findById(ID<E> id) {
            return (Optional<E>) Optional.ofNullable(dataSource.get(id));
        }

        private final ChooseAWordEvaluator evaluator = new ChooseAWordEvaluator(creator, repository);
    };

    private final ChooseAWordEvaluator evaluator = new ChooseAWordEvaluator(creator, repository);


    @Test
    public void happyPath() {
        var question = new ChooseAWordQuestion(
                new ID<>(ChooseAWordQuestion.class, "QUESTION_ID"),
                List.of(List.of("A"), List.of("B", "C"))
        );
        dataSource.put(question.id(), question);

        var answer = new QuestionAnswer(
                new ID<>(QuestionAnswer.class, "ANSWER_ID"),
                new ID<>(ChooseAWordQuestion.class, "QUESTION_ID"),
                new ID<>(Student.class, "STUDENT_ID"),
                List.of("A", "D"),
                new Timestamp(new Date().getTime())
        );

        var evaluation = evaluator.evaluate(answer);

        var expected = new QuestionEvaluation(
                new ID<>(QuestionEvaluation.class, "EVALUATION_ID"),
                answer.id(),
                new ID<>(Teacher.class, "ADMIN"),
                List.of("true", "false"),
                50,
                new Timestamp(new Date().getTime())
        );

        assertEquals(expected.answer(), evaluation.answer());
        assertEquals(expected.comments(), evaluation.comments());
        assertEquals(expected.score(), evaluation.score());
    }

}