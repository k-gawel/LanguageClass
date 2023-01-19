package language.service.service.answerandevaluation.services;

import language.contentandrepository.criteria.answerandevaluation.ExerciseAnswerCriteria;
import language.contentandrepository.model.domain.answerandevaluation.ExerciseAnswer;
import language.graphql.answerandevaluation.input.ExerciseAnswerInput;
import language.service.service.answerandevaluation.inputs.ExerciseAnswerCreateInput;

import java.util.List;

public interface ExerciseAnswerService {

    /**
     * @param input required fields: exercise, author, question answers. #id is ignored
     * @return created ExerciseAnswer with list of question answers. If questionAnswerInput and questionAnswerId collides
     *         question answer from input is chosen
     * @throws Exception if input is invalid
     */
    ExerciseAnswer createExerciseAnswer(ExerciseAnswerCreateInput input);

    /**
     * Update question answers within exercise answers if it's not evaluated yet
     * @param input required fields: id, questionAnswers. The rest of them is ignored.
     * @return updated exercise answer
     * @throws UnsupportedActionException with code "already.evaluated" if exerciseAnswer is already evaluated,
     *         WrongInputException with code "null.id" if input has empty id field
     */
    ExerciseAnswer updateExerciseAnswer(ExerciseAnswerInput input);

    List<ExerciseAnswer> find(ExerciseAnswerCriteria criteria);

}
