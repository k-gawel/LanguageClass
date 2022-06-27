package org.tutor.materials;

import com.coxautodev.graphql.tools.SchemaParserDictionary;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.example.ExampleEntity;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.evaluation.ClosedQuestionEvaluationEntityEntity;


@SpringBootApplication
@ComponentScan(nameGenerator = BeanNameGeneratorIncludingPackage.class)
public class StudyMaterialsApi {

    @Bean
    public SchemaParserDictionary schemaParserDictionary() {
        return new
                SchemaParserDictionary()
                .add(ExampleEntity.class)
                .add(ClosedQuestionEvaluationEntityEntity.class);
    }


    public static void main(String[] args) {
        SpringApplication.run(StudyMaterialsApi.class, args);
    }



}