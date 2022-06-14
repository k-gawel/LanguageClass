package org.tutor.materials;

import com.coxautodev.graphql.tools.SchemaParserDictionary;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.example.Example;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.evaluation.ClosedQuestionEvaluation;


@SpringBootApplication
@ComponentScan(nameGenerator = BeanNameGeneratorIncludingPackage.class)
public class StudyMaterialsApi {

    @Bean
    public SchemaParserDictionary schemaParserDictionary() {
        return new
                SchemaParserDictionary()
                .add(Example.class)
                .add(ClosedQuestionEvaluation.class);
    }


    public static void main(String[] args) {
        SpringApplication.run(StudyMaterialsApi.class, args);
    }



}