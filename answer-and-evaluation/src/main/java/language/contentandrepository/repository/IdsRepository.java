package language.contentandrepository.repository;

import java.util.List;

public interface IdsRepository {

    List<String> findIdsStartingWith(String beginString);

}
