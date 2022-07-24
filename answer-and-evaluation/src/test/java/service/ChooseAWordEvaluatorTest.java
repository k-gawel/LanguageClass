package service;

import model.database.tables.ExerciseEvaluation;
import model.domain.*;
import model.input.QuestionEvaluationInput;
import model.repository.ChooseAWordQuestionRepository;
import org.junit.jupiter.api.Test;

import javax.swing.text.html.Option;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ChooseAWordEvaluatorTest {

    private final QuestionEvaluationCreator creator =
            new QuestionEvaluationCreator(null, null, null, null) {
        @Override
        public QuestionEvaluation create(QuestionEvaluationInput input) {
            return new QuestionEvaluation(
                    new ID<>(QuestionEvaluation.class, "EVALUATION_ID"),
                    input.answer(),
                    input.author(),
                    input.comments(),
                    input.score(),
                    new Date()
            );
        }
    };

    private final Map<ID<ChooseAWordQuestion>, ChooseAWordQuestion> dataSource = new HashMap<>();

    private final ChooseAWordQuestionRepository repository = new ChooseAWordQuestionRepository(null) {

        @Override
        public Optional<ChooseAWordQuestion> findById(ID<ChooseAWordQuestion> id) {
            return Optional.ofNullable(dataSource.get(id));
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
                new Date()
        );

        var evaluation = evaluator.evaluate(answer);

        var expected = new QuestionEvaluation(
                new ID<>(QuestionEvaluation.class, "EVALUATION_ID"),
                answer.id(),
                new ID<>(Teacher.class, "ADMIN"),
                List.of("true", "false"),
                50,
                new Date()
        );

        assertEquals(expected.answer(), evaluation.answer());
        assertEquals(expected.comments(), evaluation.comments());
        assertEquals(expected.score(), evaluation.score());
    }

}