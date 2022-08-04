package service;

import lombok.AllArgsConstructor;
import model.domain.ID;
import model.domain.answer.ExerciseAnswer;
import model.domain.answer.QuestionAnswer;
import model.input.ExerciseAnswerInput;
import model.repository.ExerciseRepository;
import model.repository.QuestionAnswerRepository;
import model.repository.UserRepository;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Clock;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ExerciseAnswerCreator {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final ExerciseRepository  exerciseRepository;
    private final QuestionAnswerRepository questionAnswerRepository;
    private final UserRepository userRepository;
    private final Clock clock;

    public ExerciseAnswer create(ExerciseAnswerInput input) {
        var id = new ID<>(ExerciseAnswer.class, "id");

        return new ExerciseAnswer(
                id,
                input.exercise(),
                input.author(),
                input.questionAnswers(),
                new Timestamp(clock.millis())
        );
    }

    public void save(ExerciseAnswer answer) {
        var exerciseAnswerKey = saveExerciseAnswer(answer);
        attachQuestionAnswers(exerciseAnswerKey, answer.answers());
    }

    private Long saveExerciseAnswer(ExerciseAnswer answer) {
        var exerciseKey = exerciseRepository.findKey(answer.exercise()).orElseThrow();
        var studentKey = userRepository.findKey(answer.author()).orElseThrow();

        var sql = "INSERT INTO exercise_answer (id, exercise, student, created_at)" +
                                       "VALUES (:id, :exerciseKey, :studentKey, :createdAt)";

        var params = new MapSqlParameterSource(
                Map.of(
                        "id", answer.id().id(),
                        "exerciseKey", exerciseKey,
                        "studentKey", studentKey,
                        "createdAt", answer.createdAt()
                )
        );

        var keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(sql, params, keyHolder);

        return (Long) keyHolder.getKeys().get("key");
    }

    private void attachQuestionAnswers(Long exerciseAnswerKey, List<ID<QuestionAnswer>> questionAnswers) {
        var questionKeys =
                questionAnswers.stream().map(questionAnswerRepository::findKey).map(Optional::orElseThrow).toList();

        var values = questionKeys.stream()
                .map(k -> "(" + exerciseAnswerKey + "," + k + ")")
                .collect(Collectors.joining(","));

        var sql = "INSERT INTO exercise_answer_answers VALUES " + values;

        jdbcTemplate.update(sql, Collections.emptyMap());
    }

    private String getBaseId(ExerciseAnswerInput input) {
        return "answer_for_" + input.exercise() + "_by_" + input.author();
    }

}
