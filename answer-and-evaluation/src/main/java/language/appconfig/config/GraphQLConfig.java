package language.appconfig.config;

import com.coxautodev.graphql.tools.SchemaParserDictionary;
import language.contentandrepository.model.domain.answerandevaluation.ExerciseAnswer;
import language.contentandrepository.model.domain.question.AnswerAQuestion;
import language.contentandrepository.model.domain.question.ChooseAWord;
import language.contentandrepository.model.domain.question.FillAWord;
import language.contentandrepository.model.domain.textbook.Example;
import language.contentandrepository.model.domain.user.Student;
import language.contentandrepository.model.domain.user.Teacher;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GraphQLConfig {

    private final List<Class<?>> classes = List.of(
            Example.class, ExerciseAnswer.class,
            Teacher.class, Student.class,
            ChooseAWord.class, FillAWord.class, AnswerAQuestion.class
    );

    @Bean
    public SchemaParserDictionary schemaParserDictionary() {

        return new SchemaParserDictionary()
            .add(classes);
    }

}
