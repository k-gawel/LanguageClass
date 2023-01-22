package language.service.service.eventpublisher;

import language.contentandrepository.model.domain.answerandevaluation.ExerciseAnswer;
import language.contentandrepository.model.domain.answerandevaluation.ExerciseEvaluation;
import language.contentandrepository.model.domain.answerandevaluation.QuestionAnswer;
import language.contentandrepository.model.domain.answerandevaluation.QuestionEvaluation;
import language.contentandrepository.model.domain.question.ChooseAWord;
import language.contentandrepository.model.domain.question.FillAWord;
import language.contentandrepository.model.domain.textbook.Chapter;
import language.contentandrepository.model.domain.textbook.Exercise;
import language.contentandrepository.model.domain.textbook.Textbook;
import language.graphql.answerandevaluation.input.ExerciseAnswerInput;
import language.graphql.answerandevaluation.input.QuestionEvaluationInput;
import language.graphql.question.input.ChooseAWordInput;
import language.graphql.question.input.FillAWordInput;
import language.graphql.textbook.input.ChapterUpdateInput;
import language.graphql.textbook.input.ModifyContentInput;
import language.service.service.answerandevaluation.inputs.*;
import language.service.service.textbook.inputs.*;
import org.hibernate.criterion.Example;

import java.util.List;

public enum DomainEvent {

    TEXTBOOK_CREATED("textbook.created", TextbookCreateInput.class, Textbook.class),
    TEXTBOOK_CONTENT_MODIFIED("textbook.content_modified", ModifyContentInput.class, List.class),
    TEXTBOOK_ACCESS_MODIFIED("textbook.access_modified", TextbookModifyAccessInput.class, Boolean.class),

    CHAPTER_CONTENT_MODIFIED("chapter.content_modified", ChapterContentModifyInput.class, List.class),
    CHAPTER_CREATED("chapter.created", ChapterCreateInput.class, Chapter.class),
    CHAPTER_UPDATED("chapter.updated", ChapterUpdateInput.class, Chapter.class),

    EXAMPLE_CREATED("example.created", ExampleCreateInput.class, Example.class),
    EXAMPLE_MODIFIED("example.modified", ExampleUpdateInput.class, Example.class),

    EXERCISE_CREATED("exercise.created", ExerciseCreateInput.class, Exercise.class),
    EXERCISE_UPDATED("exercise.updated", ExerciseUpdateInput.class, Exercise.class),
    EXERCISE_CONTENT_MODIFIED("exercise.content_modified", ExerciseModifyContentInput.class, List.class),

    QUESTION_CREATED_CHOOSE_A_WORD("question.created", ChooseAWordInput.class, ChooseAWord.class),
    QUESTION_CREATED_FILL_A_WORD("question.created", FillAWordInput.class, FillAWord.class),

    QUESTION_ANSWER_CREATED("question_answer.created", QuestionAnswerCreateInput.class, QuestionAnswer.class),
    QUESTION_ANSWER_UPDATED("question_answer.updated", QuestionAnswerUpdateInput.class, QuestionAnswer.class),

    QUESTION_EVALUATION_CREATED("question_evaluation.created", QuestionEvaluationInput.class, QuestionEvaluation.class),

    EXERCISE_ANSWER_CREATED("exercise_answer.created", ExerciseAnswerCreateInput.class, ExerciseAnswer.class),
    EXERCISE_ANSWER_UPDATED("exercise_answer.updated", ExerciseAnswerInput.class, ExerciseAnswer.class),

    EXERCISE_EVALUATION_CREATED("exercise_evaluation.created", ExerciseEvaluationCreateInput.class, ExerciseEvaluation.class),
    EXERCISE_EVALUATION_UPDATED("exercise_evaluation.updated", ExerciseEvaluationUpdateInput.class, ExerciseEvaluation.class);

    private final String eventName;
    private final Class input;
    private final Class result;

    DomainEvent(String name, Class input, Class result) {
        this.eventName = name;
        this.input = input;
        this.result = result;
    }

    public String eventName() {
        return eventName;
    }

    public Class inputType() {
        return input;
    }

    public Class resultType() {
        return result;
    }

}
