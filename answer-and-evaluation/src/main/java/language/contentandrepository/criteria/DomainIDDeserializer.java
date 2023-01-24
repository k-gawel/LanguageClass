package language.contentandrepository.criteria;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import language.contentandrepository.model.Domain;
import language.contentandrepository.model.DomainID;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class DomainIDDeserializer extends JsonDeserializer<DomainID> implements ContextualDeserializer {

    public DomainIDDeserializer() {
    }

    private DomainIDDeserializer(Class<? extends Domain> domainType) {
        this.domainType = domainType;
    }

    private Class<? extends Domain> domainType;

    @Override
    public DomainID deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        var id = jsonParser.getValueAsString();
        return new DomainID(domainType, id);
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) {
        var domainType =  property.getType().isCollectionLikeType() ?
                (Class<? extends Domain>) property.getType().containedType(0).containedType(0).getRawClass() :
                (Class<? extends Domain>) property.getType().containedType(0).getRawClass();
        return new DomainIDDeserializer(domainType);
    }
}
