package language.service.service.textbook.services;

import language.contentandrepository.model.domain.textbook.Example;
import language.service.service.textbook.inputs.ExampleCreateInput;
import language.service.service.textbook.inputs.ExampleUpdateInput;

public interface ExampleService {

    Example create(ExampleCreateInput input);
    Example updateExample(ExampleUpdateInput input);


}
