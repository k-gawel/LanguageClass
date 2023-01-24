package language.appconfig.config;

import com.coxautodev.graphql.tools.ObjectMapperConfigurer;
import com.coxautodev.graphql.tools.ObjectMapperConfigurerContext;
import com.coxautodev.graphql.tools.SchemaParserDictionary;
import com.coxautodev.graphql.tools.SchemaParserOptions;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import graphql.schema.*;
import language.contentandrepository.criteria.DomainIDDeserializer;
import language.contentandrepository.model.DomainID;
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

    @Bean
    public SchemaParserOptions schemaParserOptions() {
        var configurer = new ObjectMapperConfigurer() {
            @Override
            public void configure(ObjectMapper mapper, ObjectMapperConfigurerContext context) {
                var module = new SimpleModule();
                module.addDeserializer(DomainID.class, new DomainIDDeserializer());
                mapper.registerModule(module);
            }
        };

        return SchemaParserOptions.newOptions()
                .objectMapperConfigurer(configurer).build();
    }


}
