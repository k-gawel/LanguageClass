package org.tutor.materials.textbook.app;

import com.coxautodev.graphql.tools.SchemaParserDictionary;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.tutor.materials.shared.BeanNameGeneratorIncludingPackage;
import org.tutor.materials.textbook.model.entity.content.ExampleEntity;


@SpringBootApplication
@ComponentScan(nameGenerator = BeanNameGeneratorIncludingPackage.class)
public class StudyMaterialsApi {

    @Bean
    public SchemaParserDictionary schemaParserDictionary() {
        return new
                SchemaParserDictionary()
                .add(ExampleEntity.class);
    }


    public static void main(String[] args) {
        SpringApplication.run(StudyMaterialsApi.class, args);
    }




}