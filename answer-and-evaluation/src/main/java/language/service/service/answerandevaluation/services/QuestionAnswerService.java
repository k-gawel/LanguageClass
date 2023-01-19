package language.service.service.answerandevaluation.services;

import language.contentandrepository.model.domain.answerandevaluation.QuestionAnswer;
import language.service.service.answerandevaluation.inputs.QuestionAnswerCreateInput;
import language.service.service.answerandevaluation.inputs.QuestionAnswerUpdateInput;

public interface QuestionAnswerService {


    /**
     * Creates question answer from input and saves it to repository
     * @param input
     * @return created question answer
     * @throws Exception if input is invalid
     */
    QuestionAnswer create(QuestionAnswerCreateInput input);

    QuestionAnswer updateQuestionAnswer(QuestionAnswerUpdateInput input);

}
