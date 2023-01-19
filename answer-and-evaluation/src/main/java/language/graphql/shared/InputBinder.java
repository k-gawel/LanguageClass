package language.graphql.shared;

public interface InputBinder<S, T> {

    T bind(S source);
    Class<S> consumes();
    Class<T> generates();

}
