package language.service.service.textbook.services;

import language.contentandrepository.model.DomainID;
import language.contentandrepository.model.domain.textbook.Chapter;
import language.contentandrepository.model.domain.textbook.Textbook;
import language.service.service.textbook.inputs.TextbookContentModifyInput;
import language.service.service.textbook.inputs.TextbookCreateInput;
import language.service.service.textbook.inputs.TextbookModifyAccessInput;

import java.util.List;

public interface TextbookService {

    Textbook createTextbook(TextbookCreateInput input);
    boolean changeAccess(TextbookModifyAccessInput input);
    List<DomainID<Chapter>> modifyContent(TextbookContentModifyInput input);

}
