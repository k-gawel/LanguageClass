package org.tutor.materials;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.function.Predicate;

@Component
public class BeanNameGeneratorIncludingPackage extends AnnotationBeanNameGenerator {

    public BeanNameGeneratorIncludingPackage() {
    }

    /**
     * @return BeanNameGenerator that names beans with their full name (including package)
     *         to prevent conflicts.
     */
    @NotNull
    @Override
    public String generateBeanName(@NotNull BeanDefinition definition, @NotNull BeanDefinitionRegistry registry) {
        var defaultName = super.generateBeanName(definition, registry);
        var className = definition.getBeanClassName();

        try {
            var bean = registry.getBeanDefinition(defaultName);
            changeCollidingBeanDefinition(defaultName, bean, registry);
            return getNameIncludingPackage(className);
        } catch (NoSuchBeanDefinitionException e) {
            var names = registry.getBeanDefinitionNames();
            var anyBeanWithSameDefaultName = anyBeanSharesDefaultName(names, className);
            if(anyBeanWithSameDefaultName)
                return getNameIncludingPackage(className);
        }

        return defaultName;
    }

    private void changeCollidingBeanDefinition(String defaultName, BeanDefinition definition, BeanDefinitionRegistry registry) {
        var newName = getNameIncludingPackage(definition.getBeanClassName());
        registry.removeBeanDefinition(defaultName);
        registry.registerBeanDefinition(newName, definition);
    }

    private boolean anyBeanSharesDefaultName(String[] names, String className) {
        var simpleClassName = className.substring(className.lastIndexOf(".") + 1);

        var isColliding = getCollidingPredicate(simpleClassName);

        return Arrays.stream(names).filter(isColliding).findAny()
                .map(a -> Boolean.TRUE)
                .orElse(Boolean.FALSE);
    }

    @NotNull
    private Predicate<String> getCollidingPredicate(String simpleClassName) {
        return (String name) -> {
            if(!name.contains(simpleClassName))
                return false;
            for (char c : name.replaceAll(simpleClassName, "").toCharArray()) {
                if(Character.isUpperCase(c))
                    return false;
            }
            return true;
        };
    }


    private String getNameIncludingPackage(String className) {
        var lastDotIndex = className.lastIndexOf(".");
        var penultimateDotIndex = className.substring(0, lastDotIndex).lastIndexOf(".");
        var classNameWithLastPackage = className.substring(penultimateDotIndex);
        var name = classNameWithLastPackage.replaceAll("\\.", "");
        return name;
    }

}
