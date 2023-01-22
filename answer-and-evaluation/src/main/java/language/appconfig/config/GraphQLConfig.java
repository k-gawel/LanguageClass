package language.appconfig.config;

import com.coxautodev.graphql.tools.SchemaParserDictionary;
import graphql.schema.*;
import language.contentandrepository.model.domain.answerandevaluation.ExerciseAnswer;
import language.contentandrepository.model.domain.question.AnswerAQuestion;
import language.contentandrepository.model.domain.question.ChooseAWord;
import language.contentandrepository.model.domain.question.FillAWord;
import language.contentandrepository.model.domain.textbook.Example;
import language.contentandrepository.model.domain.user.Student;
import language.contentandrepository.model.domain.user.Teacher;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

    @Bean
    public GraphQLScalarType dateScalarType() {

        var coercing = new Coercing() {
            @Override
            public Object serialize(Object o) throws CoercingSerializeException {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", Locale.ENGLISH);
                return formatter.format((Date)o);
            }

            @Override
            public Object parseValue(Object o) throws CoercingParseValueException {
                return o;
            }

            @Override
            public Object parseLiteral(Object o) throws CoercingParseLiteralException {
                if (o==null){
                    return null;
                }
                return o.toString();
            }

        };

        return new GraphQLScalarType("Date", "DateTime", coercing);

    }



}
